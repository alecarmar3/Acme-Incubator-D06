
package acme.features.authenticated.forum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Forum;
import acme.entities.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedForumDeleteService implements AbstractDeleteService<Authenticated, Forum> {

	@Autowired
	AuthenticatedForumRepository repository;


	@Override
	public boolean authorise(final Request<Forum> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		int forumId = request.getModel().getInteger("id");

		Boolean isMyForum = this.repository.findOneById(forumId).getOwner().getId() == id;

		return isMyForum;
	}

	@Override
	public void bind(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}
	@Override
	public void unbind(final Request<Forum> request, final Forum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationDate", "title");
	}
	@Override
	public Forum findOne(final Request<Forum> request) {
		assert request != null;
		Forum result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;

	}
	@Override
	public void validate(final Request<Forum> request, final Forum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Forum> request, final Forum entity) {
		assert request != null;
		assert entity != null;

		Collection<Message> messages = this.repository.findManyMessagesByForumId(entity.getId());

		if (!messages.isEmpty()) {

			for (Message message : messages) {
				this.repository.delete(message);
			}

		}

		this.repository.delete(entity);
	}

}
