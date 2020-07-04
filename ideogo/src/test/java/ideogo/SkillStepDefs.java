package ideogo;

import com.acme.ideogo.controller.SkillController;
import com.acme.ideogo.model.Skill;
import com.acme.ideogo.repository.SkillRepository;
import com.acme.ideogo.resource.SaveSkillResource;
import com.acme.ideogo.service.SkillService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.Assert.assertEquals;

public class SkillStepDefs {

    private SkillService _skillService;
    private Pageable pg;
    private long id;

    @Given("^I have selected tag (\\d+)$")
    public void iHaveRegistered() {

    }

    @When("^I press skills$")
    public void iPressSkills() {
        Page <Skill> skills = _skillService.getAllSkillsByTagId(id, pg);

    }

    @Then("^I get the whole list of skills to choose$")
    public void iGetTheWholeListOfSkillsToChoose() {

    }

    private SaveSkillResource newskill = new SaveSkillResource();
    private SkillController skillcontroller = new SkillController();

    @Given("^I have added skills$")
    public void iHaveAddedSkills() {
        //newskill.setDegreesRequirement("Degrees");
    }

    @When("^I press Add$")
    public void iPressAdd() {
        skillcontroller.createComment((long)9, newskill);
    }

    @Then("^I will create specific skills$")
    public void iWillCreateSpecificSkills() {
        //assertEquals(newskill.getDegreesRequirement(), "Degrees");
    }
}
