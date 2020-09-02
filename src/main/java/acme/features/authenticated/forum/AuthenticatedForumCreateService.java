
package acme.features.authenticated.forum;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Forum;
import acme.entities.InvestmentRound;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedForumCreateService implements AbstractCreateService<Authenticated, Forum> {

	@Autowired
	AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);

		entity.setCreationDate(now);

		if (!request.isMethod(HttpMethod.GET)) {
			InvestmentRound investmentRound = entity.getInvestmentRound();
			request.getModel().setAttribute("investmentRound", investmentRound);
		}

		request.bind(entity, errors, "creationDate", "owner");

	}

	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "investmentRound");
	}

	@Override
	public Forum instantiate(final Request<Forum> request) {
		assert request != null;

		int authId = request.getPrincipal().getActiveRoleId();
		Authenticated me = this.repository.findAuthenticatedById(authId);

		InvestmentRound investmentRound = this.repository.getInvestmentRoundById(request.getModel().getInteger("InvestmentRoundId"));

		Forum result = new Forum();

		result.setOwner(me);
		result.setInvestmentRound(investmentRound);

		return result;
	}

	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Forum> request, final Forum entity) {
		this.repository.save(entity);
	}

}
