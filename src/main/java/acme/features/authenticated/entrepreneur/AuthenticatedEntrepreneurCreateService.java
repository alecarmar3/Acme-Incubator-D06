/*
 * AuthenticatedEntrepreneurCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.entrepreneur;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Configuration;
import acme.entities.roles.Entrepreneur;
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
public class AuthenticatedEntrepreneurCreateService implements AbstractCreateService<Authenticated, Entrepreneur> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedEntrepreneurRepository repository;

	// AbstractCreateService<Authenticated, Entrepreneur> ---------------------------


	@Override
	public boolean authorise(final Request<Entrepreneur> request) {
		assert request != null;

		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		Collection<String> roles = this.repository.findUserAccountRoles(userAccountId).stream().map(x -> x.getAuthorityName()).collect(Collectors.toList());

		Boolean isNotEntrepreneur = !roles.contains("Entrepreneur");

		return isNotEntrepreneur;
	}

	@Override
	public void validate(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Configuration config;
		config = this.repository.findManyConfiguration().stream().findFirst().get();

		if (!errors.hasErrors("activitySector")) {
			Boolean isOneOption = config.activitySectorsToList().contains(entity.getActivitySector());
			errors.state(request, isOneOption, "activitySector", "This value must fit one of these options:" + config.activitySectorsToList(), entity.getActivitySector());
		}

	}

	@Override
	public void bind(final Request<Entrepreneur> request, final Entrepreneur entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Entrepreneur> request, final Entrepreneur entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "startUpName", "activitySector", "qualificationRecord", "skillsRecord");
	}

	@Override
	public Entrepreneur instantiate(final Request<Entrepreneur> request) {
		assert request != null;

		Entrepreneur result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Entrepreneur();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void create(final Request<Entrepreneur> request, final Entrepreneur entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Entrepreneur> request, final Response<Entrepreneur> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
