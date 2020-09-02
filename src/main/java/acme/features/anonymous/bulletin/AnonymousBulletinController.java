
package acme.features.anonymous.bulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Bulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/bulletin/")
public class AnonymousBulletinController extends AbstractController<Anonymous, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousBulletinCreateService	createService;

	@Autowired
	private AnonymousBulletinListService	listService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
	}

}
