package asw.controller;

import asw.model.Proposal;
import asw.repository.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by guille on 3/30/17.
 */

@Controller
public class DashboardController {
    private final DBService service;

    @Autowired
    DashboardController(DBService service) {
	this.service = service;
	createMockDatabaseContent();
    }

    private void createMockDatabaseContent() {
	Proposal test = new Proposal();
	test.setTitle("Test");
	service.insertProposal(test);
	Proposal test2 = new Proposal();
	test2.setTitle("Test2");
	service.insertProposal(test2);
	Proposal test3 = new Proposal();
	test3.setTitle("Test create");
	service.insertProposal(test3);
    }

    @RequestMapping(path = "/councilstaff")
    public String dashboardCouncilstaffController(Model model) {
	model.addAttribute("proposals", service.getAllProposal());
	return "councilstaff";
    }

    @RequestMapping(path = "/councilmen")
    public String dashboardCouncilmenController(Model model) {
	model.addAttribute("proposals", service.getAllProposal());
	return "councilmen";
    }

    @RequestMapping(path = "/otherAuthorities")
    public String dashboardOtherAuthoritiesController(Model model) {
	model.addAttribute("proposals", service.getAllProposal());
	return "otherAuthorities";
    }

    @RequestMapping(path = "/proposals/{propID}")
    public String viewProposalInfoController(Model model,
	    @PathVariable(value = "propID") String id) {
	model.addAttribute("proposal", service.getProposal(id));
	return "proposal";
    }
}
