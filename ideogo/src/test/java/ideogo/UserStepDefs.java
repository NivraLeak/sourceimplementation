package ideogo;

import com.acme.ideogo.controller.UserController;
import com.acme.ideogo.model.User;
import com.acme.ideogo.resource.SaveUserResource;
import com.acme.ideogo.resource.UserResource;
import com.acme.ideogo.service.UserService;
import com.acme.ideogo.service.UserServiceImpl;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;

import org.junit.Assert;

import java.util.List;

public class UserStepDefs {

    private long _idProject;

    private UserService _userService = new UserServiceImpl();
    private UserResource result = new UserResource();
    private UserController _userController = new UserController();

    public UserStepDefs() {
    }


    @Given("^I have selected the user id (\\d+)$")
    public void I_have_selected_the_user_id(long idProject) throws Throwable {
        _idProject = idProject;
    }

    @When("^I press search for user$")
    public void I_press_search_for_user() throws Throwable {

        result = _userController.getUserById(_idProject);
    }

    @Then("^I get the user$")
    public void I_get_the_user() throws Throwable {

        assertEquals( _idProject, (long) result.getId());
    }


    private SaveUserResource newuser = new SaveUserResource();



    @Given("^I have registered$")
    public void iHaveRegistered() {
        newuser.setEmail("a@b.com");
        newuser.setPassword("1234");
       //newuser.setSex("M");
       //newuser.setOccupation("Worker");
       //newuser.setExperience("Null");
    }

    @When("^I press register$")
    public void iPressRegister() {
        result = _userController.createUser(newuser);
    }

    @Then("^my account will have been created$")
    public void myAccountWillHaveBeenCreated() {

        assertEquals(result.getEmail(), newuser.getEmail());
    }
}
