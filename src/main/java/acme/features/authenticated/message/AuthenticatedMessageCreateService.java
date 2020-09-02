
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.Forum;
import acme.entities.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(now);

		if (!request.isMethod(HttpMethod.GET)) {
			Forum forum = entity.getForum();
			request.getModel().setAttribute("forum", forum);
		}

		request.bind(entity, errors, "creationDate");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("accept", "false");
		} else {
			request.transfer(model, "accept");
		}

		request.unbind(entity, model, "title", "tags", "body", "forum", "owner.userAccount.username", "forum.title");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int authId = principal.getActiveRoleId();
		Authenticated auth = this.repository.findAuthenticatedById(authId);

		int ForumId = request.getModel().getInteger("ForumId");
		Forum forum = this.repository.findOneForumById(ForumId);

		Message result = new Message();

		result.setOwner(auth);
		result.setForum(forum);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		if (!errors.hasErrors("tags") && entity.getTags().length() != 0) {
			boolean isSpam = config.isSpam(entity.getTags());
			errors.state(request, !isSpam, "tags", "default.message.error.spam");
		}

		if (!errors.hasErrors("body")) {
			boolean isSpam = config.isSpam(entity.getBody());
			errors.state(request, !isSpam, "body", "default.message.error.spam");
		}

		if (!errors.hasErrors("title")) {
			boolean isSpam = config.isSpam(entity.getTitle());
			errors.state(request, !isSpam, "title", "default.message.error.spam");
		}

		if (!errors.hasErrors("accept")) {
			Boolean isChecked = request.getModel().getBoolean("accept");
			errors.state(request, isChecked, "accept", "default.error.must-accept");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		this.repository.save(entity);
	}

}
