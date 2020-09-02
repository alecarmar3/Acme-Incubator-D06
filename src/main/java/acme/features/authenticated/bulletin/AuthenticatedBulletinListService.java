
package acme.features.authenticated.bulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Bulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedBulletinListService implements AbstractListService<Authenticated, Bulletin> {
	// Internal State --------------------------------------------------

	@Autowired
	AuthenticatedBulletinRepository repository;


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Bulletin> findMany(final Request<Bulletin> request) {
		assert request != null;

		Collection<Bulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "title", "text", "moment", "category");
	}

}
