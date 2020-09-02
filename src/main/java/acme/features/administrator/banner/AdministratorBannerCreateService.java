
package acme.features.administrator.banner;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Banner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBannerCreateService implements AbstractCreateService<Administrator, Banner> {

	@Autowired
	AdministratorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetUrl", "creditCardNumber", "holderName", "brand", "expirationDate", "cvv");
	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		Banner result;

		result = new Banner();

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("expirationDate")) {

			String[] shortExpDate;
			Integer year, month;
			shortExpDate = entity.getExpirationDate().split("/");
			month = Integer.parseInt(shortExpDate[0]);
			year = Integer.parseInt(shortExpDate[1]);

			LocalDate now;
			now = LocalDate.now();
			boolean validYear = year >= now.getYear() - 2000;
			boolean validMonth = month >= now.getMonthValue();
			boolean monthInRange = month >= 1 && month <= 12;

			errors.state(request, validYear, "expirationDate", "administrator.banner.error.timed-out-credit-card-year");
			if (year == now.getYear() - 2000) {
				errors.state(request, validMonth, "expirationDate", "administrator.banner.error.timed-out-credit-card-month");
			}
			errors.state(request, monthInRange, "expirationDate", "administrator.banner.error.inexistent-month");

		}

	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		this.repository.save(entity);
	}

}
