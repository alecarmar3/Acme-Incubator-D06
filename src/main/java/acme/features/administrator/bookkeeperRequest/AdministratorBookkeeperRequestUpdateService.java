/*
 * AuthenticatedBookkeeperUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.bookkeeperRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.BookkeeperRequest;
import acme.entities.BookkeeperRequestStatus;
import acme.entities.roles.Bookkeeper;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBookkeeperRequestUpdateService implements AbstractUpdateService<Administrator, BookkeeperRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBookkeeperRequestRepository repository;


	// AbstractUpdateService<Authenticated, BookkeeperRequest> interface -----------------

	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		Boolean isNotAccepted = !request.getModel().getAttribute("status").equals(BookkeeperRequestStatus.ACCEPTED);

		return isNotAccepted;
	}

	@Override
	public void validate(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void bind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "approved", "status");
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibilityStatement", "authenticated");
	}

	@Override
	public BookkeeperRequest findOne(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void update(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		Bookkeeper bookkeeper = new Bookkeeper();
		bookkeeper.setFirmName(entity.getFirmName());
		bookkeeper.setResponsibilityStatement(entity.getResponsibilityStatement());
		bookkeeper.setUserAccount(entity.getAuthenticated());

		entity.setApproved(true);
		entity.setStatus(BookkeeperRequestStatus.ACCEPTED);

		this.repository.save(bookkeeper);
		this.repository.save(entity);
	}

}
