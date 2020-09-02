
package acme.features.authenticated.bulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Bulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/bulletin/")
public class AuthenticatedBulletinController extends AbstractController<Authenticated, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBulletinCreateService	createService;

	@Autowired
	private AuthenticatedBulletinListService	listService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
