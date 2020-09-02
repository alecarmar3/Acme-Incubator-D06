
package acme.features.entrepreneur.investmentRound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurInvestmentRoundUpdateService implements AbstractUpdateService<Entrepreneur, InvestmentRound> {

	@Autowired
	EntrepreneurInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound requested = this.repository.findOneById(request.getModel().getInteger("id"));

		Boolean isMine = requested.getEntrepreneur().getId() == request.getPrincipal().getActiveRoleId();

		Boolean isNotFinalMode = this.repository.findFinalMode(request.getModel().getInteger("id")).equals(false);

		return isNotFinalMode && isMine;
	}

	@Override
	public void bind(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(now);

		request.bind(entity, errors, "updateDate");
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "kindOfRound", "title", "description", "amountOfMoney", "finalMode", "additionalInfo");
	}

	@Override
	public InvestmentRound findOne(final Request<InvestmentRound> request) {
		assert request != null;

		InvestmentRound result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<InvestmentRound> request, final InvestmentRound entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		String ticker = entity.getTicker();

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);

		String activitySectorOfThisEntrepreneur = this.repository.findActivitySectorOfEntrepreneur(id);

		InvestmentRound investmentRoundWithTicker = this.repository.findInvestmentRoundByTicker(ticker);

		if (!errors.hasErrors("ticker")) {

			Boolean activitySectorLetters = request.getModel().getAttribute("ticker").toString().toUpperCase().substring(0, 3).equals(activitySectorOfThisEntrepreneur.toUpperCase().subSequence(0, 3));

			Boolean yearDigits = request.getModel().getAttribute("ticker").toString().substring(4, 6).equals(yearInString.substring(2, 4));

			errors.state(request, activitySectorLetters, "ticker", "default.error.ticker-pattern.letters", entity.getTicker());
			errors.state(request, yearDigits, "ticker", "default.error.ticker-pattern.yearDigits", entity.getTicker());
			if (investmentRoundWithTicker != null) {
				Boolean investmentRoundWithTickerIsThis = investmentRoundWithTicker.equals(this.repository.findOneById(entity.getId()));
				errors.state(request, investmentRoundWithTickerIsThis, "ticker", "default.error.already-in-use", entity.getTicker());
			}
		}

		if (!errors.hasErrors("kindOfRound")) {
			List<String> kindOfRounds = new ArrayList<String>(Arrays.asList("SEED", "ANGEL", "SERIES-A", "SERIES-B", "SERIES-C", "BRIDGE"));
			Boolean isOneOption = kindOfRounds.contains(entity.getKindOfRound());
			errors.state(request, isOneOption, "kindOfRound", "This value must fit one of these options:" + kindOfRounds.toString(), entity.getKindOfRound());
		}

		if (!errors.hasErrors("amountOfMoney")) {
			Boolean amountOfMoneyEuros = entity.getAmountOfMoney().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, amountOfMoneyEuros, "amountOfMoney", "default.error.wrong-currency", entity.getAmountOfMoney());
		}

	}

	@Override
	public void update(final Request<InvestmentRound> request, final InvestmentRound entity) {
		assert request != null;
		assert entity != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(now);

		this.repository.save(entity);
	}

}
