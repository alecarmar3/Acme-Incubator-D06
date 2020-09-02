
package acme.features.authenticated.participatesIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Forum;
import acme.entities.ParticipatesIn;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipatesInAddParticipantService implements AbstractCreateService<Authenticated, ParticipatesIn> {

	@Autowired
	AuthenticatedParticipatesInRepository repository;


	@Override
	public boolean authorise(final Request<ParticipatesIn> request) {
		assert request != null;

		boolean isOwner;

		Principal principal = request.getPrincipal();
		int myId = principal.getActiveRoleId();

		int forumId = request.getModel().getInteger("forumId");
		Forum forum = this.repository.findForumById(forumId);

		isOwner = forum.getOwner().getId() == myId;

		return isOwner;
	}

	@Override
	public void bind(final Request<ParticipatesIn> request, final ParticipatesIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ParticipatesIn> request, final ParticipatesIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Authenticated> allAuthenticated;

		int forumId = entity.getForum().getId();

		allAuthenticated = this.repository.findAllAuthenticated(forumId);

		model.setAttribute("allAuthenticated", allAuthenticated);

		request.unbind(entity, model, "forum", "forum.title");
	}

	@Override
	public ParticipatesIn instantiate(final Request<ParticipatesIn> request) {
		assert request != null;

		ParticipatesIn result = new ParticipatesIn();

		int forumId = request.getModel().getInteger("forumId");
		Forum forum = this.repository.findForumById(forumId);

		result.setForum(forum);

		return result;
	}

	@Override
	public void validate(final Request<ParticipatesIn> request, final ParticipatesIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<ParticipatesIn> request, final ParticipatesIn entity) {
		assert request != null;
		assert entity != null;

		int authId = request.getModel().getInteger("participantId");

		Authenticated participant = this.repository.findOneAuthenticatedById(authId);

		entity.setParticipant(participant);

		this.repository.save(entity);
	}

}
