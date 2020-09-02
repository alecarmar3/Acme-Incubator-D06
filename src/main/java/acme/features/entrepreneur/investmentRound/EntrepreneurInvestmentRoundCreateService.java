
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EntrepreneurInvestmentRoundCreateService implements AbstractCreateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);

		Principal principal = request.getPrincipal();
		int authId = principal.getActiveRoleId();
		Entrepreneur auth = this.repository.findEntrepreneurById(authId);

		entity.setCreationDate(now);
		entity.setUpdateDate(now);
		entity.setEntrepreneur(auth);
		entity.setFinalMode(false);

		request.bind(entity, errors, "creationDate", "entrepreneur", "finalMode");
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "kindOfRound", "title", "description", "amountOfMoney", "additionalInfo");
	}

	@Override
	public InvestmentRound instantiate(final Request<InvestmentRound> request) {
		InvestmentRound result;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();
		Entrepreneur entrepreneur = this.repository.findEntrepreneurById(id);

		result = new InvestmentRound();
		result.setEntrepreneur(entrepreneur);
		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);

		String activitySectorOfThisEntrepreneur = this.repository.findActivitySectorOfEntrepreneur(id);

		Collection<String> tickersInUse = this.repository.findTickersInUse();

		if (!errors.hasErrors("kindOfRound")) {
			List<String> kindOfRounds = new ArrayList<String>(Arrays.asList("SEED", "ANGEL", "SERIES-A", "SERIES-B", "SERIES-C", "BRIDGE"));
			Boolean isOneOption = kindOfRounds.contains(entity.getKindOfRound());
			errors.state(request, isOneOption, "kindOfRound", "This value must fit one of these options:" + kindOfRounds.toString(), entity.getKindOfRound());
		}

		if (!errors.hasErrors("ticker")) {

			Boolean activitySectorLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(activitySectorOfThisEntrepreneur.toUpperCase().subSequence(0, 3));

			Boolean yearDigits = request.getModel().getAttribute("ticker").toString().substring(4, 6).equals(yearInString.substring(2, 4));

			Boolean tickerAvailable = !tickersInUse.contains(request.getModel().getAttribute("ticker"));

			errors.state(request, activitySectorLetters, "ticker", "default.error.ticker-pattern.letters", entity.getTicker());
			errors.state(request, yearDigits, "ticker", "default.error.ticker-pattern.yearDigits", entity.getTicker());
			errors.state(request, tickerAvailable, "ticker", "default.error.already-in-use", entity.getTicker());
		}

		if (!errors.hasErrors("amountOfMoney")) {
			Boolean amountOfMoneyEuros = entity.getAmountOfMoney().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, amountOfMoneyEuros, "amountOfMoney", "default.error.wrong-currency", entity.getAmountOfMoney());
		}

	}

	@Override
	public void create(final Request<InvestmentRound> request, final InvestmentRound entity) {

		Date now;

		now = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(now);

		this.repository.save(entity);
	}

}
