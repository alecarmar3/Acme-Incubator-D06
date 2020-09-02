
package acme.features.authenticated.participatesIn;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.ParticipatesIn;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/participates-in/")
public class AuthenticatedParticipatesInController extends AbstractController<Authenticated, ParticipatesIn> {

	@Autowired
	private AuthenticatedParticipatesInListParticipantsService	listParticipantsService;

	@Autowired
	private AuthenticatedParticipatesInShowService				showService;

	@Autowired
	private AuthenticatedParticipatesInAddParticipantService	addParticipantService;

	@Autowired
	private AuthenticatedParticipatesInRemoveParticipantService	removeParticipantService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_PARTICIPANTS, BasicCommand.LIST, this.listParticipantsService);
		super.addCustomCommand(CustomCommand.ADD_PARTICIPANT, BasicCommand.CREATE, this.addParticipantService);
		super.addCustomCommand(CustomCommand.REMOVE_PARTICIPANT, BasicCommand.DELETE, this.removeParticipantService);

	}
}
