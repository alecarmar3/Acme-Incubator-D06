
package acme.features.investor.application;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Application;
import acme.entities.ApplicationStatus;
import acme.entities.InvestmentRound;
import acme.entities.roles.Investor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class InvestorApplicationCreateService implements AbstractCreateService<Investor, Application> {

	@Autowired
	InvestorApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		int id = request.getPrincipal().getAccountId();
		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(request.getModel().getInteger("InvestmentRoundId"));

		Collection<String> roles = this.repository.findUserAccountRoles(id).stream().map(x -> x.getAuthorityName()).collect(Collectors.toList());

		Boolean isFinalMode = investmentRound.getFinalMode();
		Boolean isInvestor = roles.contains("Investor");
		Boolean notBeenApplied = this.repository.exists(request.getModel().getInteger("InvestmentRoundId"), request.getPrincipal().getActiveRoleId()).isEmpty();

		return isFinalMode && isInvestor && notBeenApplied;

	}
	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(now);

		if (!request.isMethod(HttpMethod.GET)) {
			InvestmentRound investmentRound = entity.getInvestmentRound();
			request.getModel().setAttribute("investmentRound", investmentRound);
		}

		request.bind(entity, errors, "creationDate", "status", "investor");
	}
	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "statement", "investmentMoneyOffer", "investmentRound");
	}
	@Override
	public Application instantiate(final Request<Application> request) {

		Application result = new Application();
		Principal principal = request.getPrincipal();

		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(request.getModel().getInteger("InvestmentRoundId"));
		Investor investor = this.repository.findInvestorById(principal.getActiveRoleId());

		result.setStatus(ApplicationStatus.PENDING);
		result.setInvestor(investor);
		result.setInvestmentRound(investmentRound);

		return result;

	}
	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);

		String activitySectorOfThisInvestor = this.repository.findActivitySectorOfInvestor(id);

		Collection<String> tickersInUse = this.repository.findTickersInUse();

		if (!errors.hasErrors("ticker")) {

			Boolean activitySectorLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(activitySectorOfThisInvestor.toUpperCase().subSequence(0, 3));

			Boolean yearDigits = request.getModel().getAttribute("ticker").toString().substring(4, 6).equals(yearInString.substring(2, 4));

			Boolean tickerAvailable = !tickersInUse.contains(request.getModel().getAttribute("ticker"));

			errors.state(request, activitySectorLetters, "ticker", "default.error.ticker-pattern.letters", entity.getTicker());
			errors.state(request, yearDigits, "ticker", "default.error.ticker-pattern.yearDigits", entity.getTicker());
			errors.state(request, tickerAvailable, "ticker", "default.error.already-in-use", entity.getTicker());
		}

		if (!errors.hasErrors("investmentMoneyOffer")) {
			Boolean investmentMoneyOfferEuros = entity.getInvestmentMoneyOffer().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, investmentMoneyOfferEuros, "investmentMoneyOffer", "default.error.wrong-currency", entity.getInvestmentMoneyOffer());
		}

	}
	@Override
	public void create(final Request<Application> request, final Application entity) {
		this.repository.save(entity);
	}

}
