
package acme.features.administrator.dashboard;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNotices", "totalTechnologyRecords", "totalToolRecords", "minimumMoneyIntervalActiveInquires", "maximumMoneyIntervalActiveInquires", "averageMoneyIntervalActiveInquires", "deviationMoneyIntervalActiveInquires",
			"minimumMoneyIntervalActiveOvertures", "maximumMoneyIntervalActiveOvertures", "averageMoneyIntervalActiveOvertures", "deviationMoneyIntervalActiveOvertures");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		Dashboard result = new Dashboard();
		Calendar start, end, dateLoop;

		result.setTotalNotices(this.repository.countNotices());
		result.setTotalTechnologyRecords(this.repository.countTechnologyRecords());
		result.setTotalToolRecords(this.repository.countToolRecords());
		result.setMinimumMoneyIntervalActiveInquires(this.repository.minInquiryMoneyInterval());
		result.setMaximumMoneyIntervalActiveInquires(this.repository.maxInquiryMoneyInterval());
		result.setAverageMoneyIntervalActiveInquires(this.repository.averageInquiryMoneyInterval());
		result.setDeviationMoneyIntervalActiveInquires(this.repository.deviationInquiryMoneyInterval());
		result.setMinimumMoneyIntervalActiveOvertures(this.repository.minOvertureMoneyInterval());
		result.setMaximumMoneyIntervalActiveOvertures(this.repository.maxOvertureMoneyInterval());
		result.setAverageMoneyIntervalActiveOvertures(this.repository.averageOvertureMoneyInterval());
		result.setDeviationMoneyIntervalActiveOvertures(this.repository.deviationOvertureMoneyInterval());

		//result.setTotalTechnologiesByTechnologySector(this.repository.getTotalTechnologiesByTechnologySector());
		//result.setTotalTechnologiesByScienceSector(this.repository.getTotalTechnologiesByScienceSector());
		//result.setTotalTechnologiesByArtsSector(this.repository.getTotalTechnologiesByArtsSector());
		//result.setTotalTechnologiesByBusinessSector(this.repository.getTotalTechnologiesByBusinessSector());
		//result.setTotalTechnologiesByHealthSector(this.repository.getTotalTechnologiesByHealthSector());
		//		result.setTotalToolsByActivitySector(this.repository.getTotalToolsByActivitySector());
		//		result.setOpenSourceTechnologiesVSclosedSourceTechnologies(this.repository);
		//		result.setOpenSourceToolsVSclosedSourceTools(this.repository);

		return result;
	}
}
