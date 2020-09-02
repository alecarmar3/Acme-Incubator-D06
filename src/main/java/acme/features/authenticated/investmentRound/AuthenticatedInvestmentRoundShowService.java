
package acme.features.authenticated.investmentRound;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedInvestmentRoundShowService implements AbstractShowService<Authenticated, InvestmentRound> {

	@Autowired
	private AuthenticatedInvestmentRoundRepository repository;


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

		int id = request.getPrincipal().getAccountId();
		int InvestmentRoundId = request.getModel().getInteger("id");

		Collection<String> roles = this.repository.findUserAccountRoles(id).stream().map(x -> x.getAuthorityName()).collect(Collectors.toList());

		Boolean notBeenApplied = this.repository.exists(request.getModel().getInteger("id"), request.getPrincipal().getAccountId()).isEmpty();
		Boolean isInvestor = roles.contains("Investor");
		Boolean isFinalMode = entity.getFinalMode();

		Bookkeeper bookkeeper = this.repository.getBookkeeperByAccountId(id);

		Boolean isBookkeeper = bookkeeper != null;

		Boolean investorCanApply = notBeenApplied && isInvestor && isFinalMode;
		model.setAttribute("investorCanApply", investorCanApply);
		model.setAttribute("InvestmentRoundId", InvestmentRoundId);
		model.setAttribute("isBookkeeper", isBookkeeper);

		request.unbind(entity, model, "ticker", "finalMode", "creationDate", "kindOfRound", "title", "description", "amountOfMoney", "additionalInfo", "entrepreneur.userAccount.username");
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

}
