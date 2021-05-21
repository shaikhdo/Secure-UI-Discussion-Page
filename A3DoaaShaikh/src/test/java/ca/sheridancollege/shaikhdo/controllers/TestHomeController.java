package ca.sheridancollege.shaikhdo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import ca.sheridancollege.shaikhdo.beans.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc

public class TestHomeController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testLoadingUnsecuredIndex() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"));

	}

	@Test
	public void testLoadingSecuredIndex() throws Exception {
		this.mockMvc.perform(get("/secure")).andExpect(status().isOk()).andExpect(view().name("secure/index"));
	}
	
	@Test
	public void testLoadingRegistration() throws Exception {
		this.mockMvc.perform(get("/register")).andExpect(status().isOk()).andExpect(view().name("register"));
	}
	
	@Test
	public void testLoadingLogin() throws Exception {
		this.mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("login"));
	}
	
	@Test
	public void testLoadingUIDiscussion() throws Exception {
		this.mockMvc.perform(get("/secure/ui-discussion")).andExpect(status().isOk()).andExpect(view().name("secure/ui-discussion"));
	}
	
	@Test
	public void testLoadingNoPerm() throws Exception {
		this.mockMvc.perform(get("/error/permission-denied")).andExpect(status().isOk()).andExpect(view().name("error/permission-denied"));
	}
	
	@Test
	public void testLoadingInsertPost() throws Exception {
		this.mockMvc.perform(post("/insertPost").flashAttr("userComment", new User()))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	

}
