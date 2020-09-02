
package acme.features.administrator.technologyRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.TechnologyRecord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorTechnologyRecordCreateService implements AbstractCreateService<Administrator, TechnologyRecord> {

	@Autowired
	AdministratorTechnologyRecordRepository repository;


	@Override
	public boolean authorise(final Request<TechnologyRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "activitySector", "inventorName", "description", "website", "email", "sourceType", "stars");
	}

	@Override
	public TechnologyRecord instantiate(final Request<TechnologyRecord> request) {
		TechnologyRecord result;

		result = new TechnologyRecord();

		return result;
	}

	@Override
	public void validate(final Request<TechnologyRecord> request, final TechnologyRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		if (!errors.hasErrors("sourceType")) {
			Boolean isOneOption = entity.getSourceType().equals("OpenSource") || entity.getSourceType().equals("ClosedSource");
			errors.state(request, isOneOption, "sourceType", "administrator.technology-record.error.not-option", entity.getSourceType());
		}

		if (!errors.hasErrors("activitySector")) {
			Boolean isOneOption = config.activitySectorsToList().contains(entity.getActivitySector());
			errors.state(request, isOneOption, "activitySector", "This value must fit one of these options:" + config.activitySectorsToList(), entity.getActivitySector());
		}

	}

	@Override
	public void create(final Request<TechnologyRecord> request, final TechnologyRecord entity) {
		this.repository.save(entity);
	}

}
