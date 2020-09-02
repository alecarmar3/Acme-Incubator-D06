
package acme.features.authenticated.forum;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Forum;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedForumListMineService implements AbstractListService<Authenticated, Forum> {

	@Autowired
	AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationDate", "investmentRound.title");
	}

	@Override
	public Collection<Forum> findMany(final Request<Forum> request) {
		assert request != null;

		Collection<Forum> result = new ArrayList<Forum>();

		Collection<Forum> participants = new ArrayList<Forum>();

		Principal principal = request.getPrincipal();
		int id = principal.getAccountId();

		participants = this.repository.findManyByParticipantId(principal.getActiveRoleId());

		this.repository.findEntrepreneurInvestmentRounds(id).stream().forEach(x -> result.addAll(this.repository.findForumsOfInvestmentRound(x.getId())));
		this.repository.findBookkeeperInvestmentRounds(id).stream().forEach(x -> result.addAll(this.repository.findForumsOfInvestmentRound(x.getId())));
		this.repository.findInvestorInvestmentRounds(id).stream().forEach(x -> result.addAll(this.repository.findForumsOfInvestmentRound(x.getId())));

		result.addAll(participants);

		return result;
	}

}
