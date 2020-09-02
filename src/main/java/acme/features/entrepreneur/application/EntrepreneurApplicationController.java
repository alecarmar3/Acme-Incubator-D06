
package acme.features.entrepreneur.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.Application;
import acme.entities.roles.Entrepreneur;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/entrepreneur/application/")
public class EntrepreneurApplicationController extends AbstractController<Entrepreneur, Application> {

	@Autowired
	private EntrepreneurApplicationListMadeToMineService	listMadeToMineService;

	@Autowired
	private EntrepreneurApplicationShowService				showService;

	@Autowired
	private EntrepreneurApplicationUpdateAcceptService		updateAcceptService;

	@Autowired
	private EntrepreneurApplicationUpdateRejectService		updateRejectService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MADE_TO_MINE, BasicCommand.LIST, this.listMadeToMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.UPDATE_ACCEPT, BasicCommand.UPDATE, this.updateAcceptService);
		super.addCustomCommand(CustomCommand.UPDATE_REJECT, BasicCommand.UPDATE, this.updateRejectService);
	}
}
