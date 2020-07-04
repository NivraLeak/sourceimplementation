
package ideogo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class CucumberTest {
}

///import cucumber.api.CucumberOptions;
///import cucumber.api.junit.Cucumber;
///import org.junit.runner.RunWith;
///
///@RunWith(Cucumber.class)
///@CucumberOptions(plugin = {"pretty"})
///public class RunCucumberTest{
///}