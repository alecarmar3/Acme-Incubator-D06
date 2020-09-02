
package acme.features.authenticated.participatesIn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ParticipatesIn;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipatesInRemoveParticipantService implements AbstractDeleteService<Authenticated, ParticipatesIn> {

	@Autowired
	AuthenticatedParticipatesInRepository repository;


	@Override
	public boolean authorise(final Request<ParticipatesIn> request) {
		assert request != null;

		boolean isOwner;

		ParticipatesIn participatesIn = this.repository.findOneById(request.getModel().getInteger("id"));
		Principal principal = request.getPrincipal();

		isOwner = participatesIn.getForum().getOwner().getId() == principal.getActiveRoleId();

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

		request.unbind(entity, model, "participant", "forum");
	}

	@Override
	public ParticipatesIn findOne(final Request<ParticipatesIn> request) {
		assert request != null;

		ParticipatesIn result;

		result = this.repository.findOneById(request.getModel().getInteger("id"));

		return result;
	}

	@Override
	public void validate(final Request<ParticipatesIn> request, final ParticipatesIn entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<ParticipatesIn> request, final ParticipatesIn entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
