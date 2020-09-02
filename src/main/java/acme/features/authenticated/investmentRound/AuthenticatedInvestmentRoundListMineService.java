
package acme.features.authenticated.investmentRound;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.InvestmentRound;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedInvestmentRoundListMineService implements AbstractListService<Authenticated, InvestmentRound> {

	@Autowired
	AuthenticatedInvestmentRoundRepository repository;


	@Override
	public boolean authorise(final Request<InvestmentRound> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<InvestmentRound> request, final InvestmentRound entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "finalMode", "kindOfRound", "title", "amountOfMoney");
	}

	@Override
	public Collection<InvestmentRound> findMany(final Request<InvestmentRound> request) {
		assert request != null;

		Collection<InvestmentRound> result, bookkeeper, investor, entrepreneur;

		Principal principal = request.getPrincipal();
		int id = principal.getAccountId();

		bookkeeper = this.repository.findBookkeeperInvestmentRounds(id);

		investor = this.repository.findInvestorInvestmentRounds(id);

		entrepreneur = this.repository.findEntrepreneurInvestmentRounds(id);

		result = bookkeeper;
		result.addAll(investor);
		result.addAll(entrepreneur);

		return result;
	}
}
