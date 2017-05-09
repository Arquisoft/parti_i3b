package asw.steps;

import asw.Application;
import asw.kafka.mockproducer.MessageProducer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ContextConfiguration
@SpringBootTest(classes = Application.class)
public class LogsSteps {

	@Autowired
	protected WebApplicationContext context;

	@Autowired
	private MessageProducer mp;

	protected MockMvc mvc;
	protected MvcResult result;
	protected ResultActions rs;

	@Given("^the user navigates to /$")
	public void userNavigatesTo() throws Throwable {
		Assert.notNull(context);
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		result = mvc.perform(get("/")).andReturn();
	}

	@Given("^clicks \"([^\"]*)\" link$")
	public void clicksLink(String str) throws Throwable {
		Assert.notNull(context);
		this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
		rs = mvc.perform(get("/" + str));
		System.out.println("TEST1");
		rs.andDo(print());
	}

	@Given("^a message is produced with topic \"([^\"]*)\"$")
	public void messageIsProducedWithTopic(String topic) throws Throwable {
		mp.send(topic);
		mp.send(topic);
	}

	@When("^the user waits (\\d+) seconds$")
	public void userWaitsSeconds(int seconds) throws Throwable {
        System.out.println("TEST2");
        rs.andDo(new WaitHandler(seconds));
        rs.andDo(print());
        System.out.println("TEST3");
	}

	@Then("^there is a log of \"([^\"]*)\" on the webpage$")
	public void logWeb(String str) throws Throwable {
		result = rs.andReturn();
        System.out.println(result.getResponse().getContentAsString().equals(""));
        System.out.println(result.getResponse().getContentAsString());
		assertTrue(result.getResponse().getContentAsString().contains(str));
	}

	@Then("^there is not a log of \"([^\"]*)\" on the webpage$")
	public void noLogWeb(String str) throws Throwable {
		assertThat(result.getResponse().getContentAsString(),
				not(containsString(str)));
	}

	class WaitHandler implements ResultHandler {
	    private int seconds;
	    public WaitHandler(int seconds) {
	        this.seconds = seconds;
        }
		public void handle (MvcResult result) {
			try {
				Thread.sleep(seconds * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}