
package acme.features.authenticated.participatesIn;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Forum;
import acme.entities.ParticipatesIn;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipatesInListParticipantsService implements AbstractListService<Authenticated, ParticipatesIn> {

	@Autowired
	AuthenticatedParticipatesInRepository repository;


	@Override
	public boolean authorise(final Request<ParticipatesIn> request) {
		assert request != null;

		boolean isParticipant;
		boolean isOwner;

		Principal principal = request.getPrincipal();
		int myId = principal.getActiveRoleId();

		int forumId = request.getModel().getInteger("forumId");
		Forum forum = this.repository.findForumById(forumId);
		Collection<ParticipatesIn> participation = this.repository.findManyByForumId(forumId);

		isParticipant = participation.stream().anyMatch(p -> p.getParticipant().getId() == myId);
		isOwner = forum.getOwner().getId() == myId;

		return isOwner || isParticipant;
	}

	@Override
	public void unbind(final Request<ParticipatesIn> request, final ParticipatesIn entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "participant", "participant.userAccount.username", "participant.userAccount.identity.fullName", "forum");
	}

	@Override
	public Collection<ParticipatesIn> findMany(final Request<ParticipatesIn> request) {
		assert request != null;

		Collection<ParticipatesIn> result;

		int forumId = request.getModel().getInteger("forumId");

		result = this.repository.findManyByForumId(forumId);

		return result;
	}

}
