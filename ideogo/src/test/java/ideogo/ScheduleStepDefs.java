package ideogo;

import com.acme.ideogo.controller.ProjectController;
import com.acme.ideogo.controller.ProjectScheduleController;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.model.ProjectSchedule;
import com.acme.ideogo.resource.ProjectResource;
import com.acme.ideogo.resource.ProjectScheduleResource;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class ScheduleStepDefs {
    private long _projectId;
    private ProjectScheduleController _projectScheduleController;
    private ProjectSchedule Sched;
    private Project pr;

    private ProjectScheduleResource schedule;

    @Given("^I have  selected my project (\\d+)$")
    public void iHaveRegistered(long id) {
        pr.setId(id);

        Sched.setName("Schedule");
        Sched.setId((long) 1);
        Sched.setDescription("D");
        Sched.setProject(pr);

        _projectId = _projectId;
    }

    @When("^I press see schedule$")
    public void iPressSeeSchedule() {
        schedule =  _projectScheduleController.getProjectSchedulesByProjectId(_projectId, Sched.getId());
    }

    @Then("^I get the  specific schedule of my project$")
    public void iGetTheSpecificScheduleOfMyProject() {
        assertEquals(schedule.getId(), Sched.getId());
    }
}
