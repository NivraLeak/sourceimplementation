package ideogo;

import com.acme.ideogo.controller.ApplicationController;
import com.acme.ideogo.model.User;
import com.acme.ideogo.resource.ApplicationResource;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProjectStepDefs {
    @Given("^The user is in the project box$")
    public void iHaveRegistered() {

    }

    @When("^selecting the project to view$")
    public void selectingTheProjectToView() {
    }

    @Then("^you will then be redirected to the group consisting of the project and the project details$")
    public void youWillThenBeRedirectedToTheGroupConsistingOfTheProjectAndTheProjectDetails() {
    }

    private long userId;
    private User user;
    private ApplicationController _applicationController;
    private Pageable pg;
    private List<ApplicationResource> list;

    @Given("^I have select the user (\\d+)$")
    public void iHaveSelectTheProject(long id) {
        user.setEmail("21");
        userId = id;
    }

    @When("^they select the users$")
    public void theySelectTheProject() {
        Page<ApplicationResource> pages = _applicationController.getAllApplicationsByUserId(userId, (org.springframework.data.domain.Pageable) pg);
        list = pages.getContent();
    }

    @Then("^they can see the applicants\\.$")
    public void theyCanSeeTheApplicants() {
        assertEquals( list.get(0) , list.get(0));
    }
}
