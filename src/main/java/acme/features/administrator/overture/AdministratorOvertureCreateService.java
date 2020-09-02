
package acme.features.administrator.overture;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Overture;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorOvertureCreateService implements AbstractCreateService<Administrator, Overture> {

	@Autowired
	AdministratorOvertureRepository repository;


	@Override
	public boolean authorise(final Request<Overture> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(moment);

		request.bind(entity, errors, "creationDate");
	}

	@Override
	public void unbind(final Request<Overture> request, final Overture entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "body", "moneyMin", "moneyMax", "email");
	}

	@Override
	public Overture instantiate(final Request<Overture> request) {
		Overture result;

		result = new Overture();

		return result;
	}

	@Override
	public void validate(final Request<Overture> request, final Overture entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("deadline")) {
			Boolean isFuture = entity.getDeadline().after(new Date());
			errors.state(request, isFuture, "deadline", "administrator.overture.error.past-deadline", entity.getDeadline());
		}

		if (!errors.hasErrors("moneyMin")) {
			Boolean moneyMinEUR = entity.getMoneyMin().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, moneyMinEUR, "moneyMin", "administrator.overture.error.wrong-currency", entity.getMoneyMin());
		}

		if (!errors.hasErrors("moneyMax")) {
			Boolean moneyMaxEUR = entity.getMoneyMax().getCurrency().matches("€|EUROS|Euros|euros|EUR|Eur|eur");
			errors.state(request, moneyMaxEUR, "moneyMax", "administrator.overture.error.wrong-currency", entity.getMoneyMax());
		}

		if (!errors.hasErrors("moneyMin") && !errors.hasErrors("moneyMax")) {
			Boolean minAndMax = entity.getMoneyMin().getAmount().compareTo(entity.getMoneyMax().getAmount()) < 0;
			errors.state(request, minAndMax, "moneyMin", "administrator.overture.error.moneyMin-is-not-minimum");
		}

	}

	@Override
	public void create(final Request<Overture> request, final Overture entity) {

		Date creationDate;

		creationDate = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(creationDate);

		this.repository.save(entity);
	}

}
