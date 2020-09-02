
package acme.features.bookkeeper.accountingRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.AccountingRecord;
import acme.entities.InvestmentRound;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class BookkeeperAccountingRecordCreateService implements AbstractCreateService<Bookkeeper, AccountingRecord> {

	@Autowired
	BookkeeperAccountingRecordRepository repository;


	@Override
	public boolean authorise(final Request<AccountingRecord> request) {
		assert request != null;

		return true;

	}
	@Override
	public void bind(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Date now = new Date(System.currentTimeMillis() - 1);
		entity.setCreationDate(now);

		if (!request.isMethod(HttpMethod.GET)) {
			InvestmentRound investmentRound = entity.getInvestmentRound();
			request.getModel().setAttribute("investmentRound", investmentRound);
		}

		request.bind(entity, errors, "creationDate", "finalMode", "bookkeeper");
	}
	@Override
	public void unbind(final Request<AccountingRecord> request, final AccountingRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "investmentRound");
	}
	@Override
	public AccountingRecord instantiate(final Request<AccountingRecord> request) {

		AccountingRecord result = new AccountingRecord();
		Principal principal = request.getPrincipal();

		InvestmentRound investmentRound = this.repository.findInvestmentRoundById(request.getModel().getInteger("InvestmentRoundId"));
		Bookkeeper bookkeeper = this.repository.findBookkeeperById(principal.getActiveRoleId());

		result.setFinalMode(false);
		result.setBookkeeper(bookkeeper);
		result.setInvestmentRound(investmentRound);

		return result;

	}
	@Override
	public void validate(final Request<AccountingRecord> request, final AccountingRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}
	@Override
	public void create(final Request<AccountingRecord> request, final AccountingRecord entity) {
		this.repository.save(entity);
	}

}
