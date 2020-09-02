
package acme.features.authenticated.participatesIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ParticipatesIn;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipatesInShowService implements AbstractShowService<Authenticated, ParticipatesIn> {

	@Autowired
	AuthenticatedParticipatesInRepository repository;


	@Override
	public boolean authorise(final Request<ParticipatesIn> request) {
		assert request != null;

		boolean isOwner;
		boolean isParticipant;

		Principal principal = request.getPrincipal();

		ParticipatesIn participatesIn = this.repository.findOneById(request.getModel().getInteger("id"));

		Collection<ParticipatesIn> participants = this.repository.findManyByForumId(participatesIn.getForum().getId());

		isOwner = participatesIn.getForum().getOwner().getId() == principal.getActiveRoleId();
		isParticipant = participants.stream().anyMatch(p -> p.getParticipant().getId() == principal.getActiveRoleId());

		return isOwner || isParticipant;
	}

	@Override
	public void unbind(final Request<ParticipatesIn> request, final ParticipatesIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("isOwner", request.getPrincipal().getActiveRoleId() == entity.getForum().getOwner().getId());

		request.unbind(entity, model, "forum.title", "participant", "participant.userAccount.username", "participant.userAccount.identity.fullName");
	}

	@Override
	public ParticipatesIn findOne(final Request<ParticipatesIn> request) {
		assert request != null;

		ParticipatesIn result;

		result = this.repository.findOneById(request.getModel().getInteger("id"));

		return result;
	}

}
