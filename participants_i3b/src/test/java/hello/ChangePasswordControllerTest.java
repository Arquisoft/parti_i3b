package hello;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import repository.DBService;

import java.net.URL;
import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by guille on 20/02/2017.
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("repository")
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class ChangePasswordControllerTest {
    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DBService db;

    @Before
    public void setUp() throws Exception {
	this.base = new URL("http://localhost:" + port + "/");
	template = new TestRestTemplate();
	mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetChangePasswordPage() throws Exception {
	template.getForEntity(base.toString(), String.class);
	mockMvc.perform(get("/changep")).andExpect(status().isOk())
		.andExpect(content().string(containsString("passwform")))
		.andExpect(content().string(containsString("Go back to")));
    }

    @Test
    public void testPostChangePasswordSuccess() throws Exception {
	// If this test fails, try clearing the database
	UserInfo user = new UserInfo("pass", "name", "surname",
		"test1@mail.com", new Date());
	UserInfo aux = db.getParticipant("test1@mail.com", "newpass");
	if (aux != null)
	    db.deleteUser(aux);
	db.insertUser(user);

	mockMvc.perform(post("/changep").param("email", "test1@mail.com")
		.param("old", "pass").param("password", "newpass")
		.param("password2", "newpass"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("result",
			equalTo("The password has been changed")))
		.andExpect(
			model().attribute("bg", equalTo("background: #0F0;")))
		.andExpect(content().string(containsString("passwform")))
		.andExpect(content().string(containsString("result")))
		.andExpect(content().string(containsString("Go back to")));
	UserInfo retrieved = db.getParticipant("test1@mail.com", "newpass");
	assertNotNull(retrieved);
	assertTrue(retrieved.getEmail().equals("test1@mail.com"));
	assertFalse(retrieved.getPassword().equals("pass"));
	assertTrue(retrieved.getPassword().equals("newpass"));
    }

    @Test
    public void testPostChangePasswordFail1() throws Exception {
	UserInfo user = new UserInfo("pass", "name", "surname",
		"test2@mail.com", new Date());
	db.insertUser(user);

	mockMvc.perform(post("/changep").param("email", "test2@mail.com")
		.param("old", "pass").param("password", "newpass1")
		.param("password2", "newpass"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("result",
			equalTo("The passwords don't match")))
		.andExpect(
			model().attribute("bg", equalTo("background: #F00;")))
		.andExpect(content().string(containsString("passwform")))
		.andExpect(content().string(containsString("result")))
		.andExpect(content().string(containsString("Go back to")));
	UserInfo retrieved = db.getParticipant("test2@mail.com", "pass");
	assertNotNull(retrieved);
	assertTrue(retrieved.getEmail().equals("test2@mail.com"));
	assertTrue(retrieved.getPassword().equals("pass"));
	assertFalse(retrieved.getPassword().equals("newpass"));
	assertFalse(retrieved.getPassword().equals("newpass2"));

    }

    @Test
    public void testPostChangePasswordFail2() throws Exception {
	UserInfo user = new UserInfo("pass", "name", "surname",
		"test3@mail.com", new Date());
	db.insertUser(user);

	mockMvc.perform(post("/changep").param("email", "test3@mail.com")
		.param("old", "wrongpass").param("password", "new")
		.param("password2", "new"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("result",
			equalTo("The password is incorrect")))
		.andExpect(
			model().attribute("bg", equalTo("background: #F00;")))
		.andExpect(content().string(containsString("passwform")))
		.andExpect(content().string(containsString("result")))
		.andExpect(content().string(containsString("Go back to")));
	UserInfo retrieved = db.getParticipant("test3@mail.com", "pass");
	assertNotNull(retrieved);
	assertTrue(retrieved.getEmail().equals("test3@mail.com"));
	assertTrue(retrieved.getPassword().equals("pass"));
	assertFalse(retrieved.getPassword().equals("new"));

    }
}
