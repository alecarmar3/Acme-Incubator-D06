/*
 * AuthenticatedBookkeeperCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.bookkeeperRequest;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.BookkeeperRequest;
import acme.entities.BookkeeperRequestStatus;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBookkeeperRequestCreateService implements AbstractCreateService<Authenticated, BookkeeperRequest> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBookkeeperRequestRepository repository;

	// AbstractCreateService<Authenticated, BookkeeperRequest> ---------------------------


	@Override
	public boolean authorise(final Request<BookkeeperRequest> request) {
		assert request != null;

		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		Collection<String> roles = this.repository.findUserAccountRoles(userAccountId).stream().map(x -> x.getAuthorityName()).collect(Collectors.toList());

		Boolean isNotBookkeeper = !roles.contains("Bookkeeper");
		Boolean hasNotBeenRequested = this.repository.findTheBookkeeperRequestByUserAccount(userAccountId) == null;

		return hasNotBeenRequested && isNotBookkeeper;

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

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<BookkeeperRequest> request, final BookkeeperRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmName", "responsibilityStatement", "status", "approved");
	}

	@Override
	public BookkeeperRequest instantiate(final Request<BookkeeperRequest> request) {
		assert request != null;

		BookkeeperRequest result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		UserAccount userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new BookkeeperRequest();
		result.setApproved(false);
		result.setStatus(BookkeeperRequestStatus.PENDING);
		result.setAuthenticated(userAccount);

		return result;
	}

	@Override
	public void create(final Request<BookkeeperRequest> request, final BookkeeperRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<BookkeeperRequest> request, final Response<BookkeeperRequest> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
