package shouty;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class stepdef {
	@Given("^(\\w+) is (\\d+) meters away from (\\w+)$")
	public void tom_is_meters_away_from_Jerry(String person1,int arg1,String person2) 
			throws Throwable {
	    System.out.println(arg1);
	    System.out.println(person1 +"-"+ person2);
	    throw new PendingException();
	}

	@When("^Jerry shouts with 'Free Movie Tickets'$")
	public void jerry_shouts_with_Free_Movie_Tickets() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^Tom should hear his message$")
	public void tom_should_hear_his_message() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


}
