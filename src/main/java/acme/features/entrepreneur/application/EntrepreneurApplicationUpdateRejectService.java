
package acme.features.entrepreneur.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Application;
import acme.entities.ApplicationStatus;
import acme.entities.InvestmentRound;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EntrepreneurApplicationUpdateRejectService implements AbstractUpdateService<Entrepreneur, Application> {

	@Autowired
	EntrepreneurApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = principal.getActiveRoleId();

		Application app;
		int appId = request.getModel().getInteger("id");
		app = this.repository.findOneById(appId);

		InvestmentRound investmentRound = app.getInvestmentRound();
		boolean result = id == investmentRound.getEntrepreneur().getId() && app.getStatus().equals(ApplicationStatus.PENDING);

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "updateDate");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "justification");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;

		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("justification")) {
			boolean isFilled = entity.getJustification().length() != 0;
			errors.state(request, isFilled, "justification", "default.error.justification-needed");
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setUpdateDate(now);
		entity.setStatus(ApplicationStatus.REJECTED);

		this.repository.save(entity);
	}

}
