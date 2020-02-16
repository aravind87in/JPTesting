package steps;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)				
@CucumberOptions(features="classpath:features")
//@CucumberOptions(tags = "@getusers and @getposts")
public class RunnerIT {
	

}
