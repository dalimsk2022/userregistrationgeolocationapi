package com.ibm.microoservices.registrationservice.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ibm.microoservices.controller.UserRegistration;

@SpringBootTest
@WebMvcTest(value = UserRegistration.class)
public class UserRegistrationTest {
	String exampleUserJson = "{\"uuid\":\"8cf40204-99eb-45ae-a417-4b9de3bb1200\",\"message\":\"User registered successfully. Username is Roy and City name is Oakville\"}";
	
	@Test
	public void retrieveDetailsForUser1() throws Exception {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<String> response = testRestTemplate.
		  getForEntity("http://localhost:9002/users/registration?username=Roy&password=Passwo1.&ipaddress=24.51.255.255", String.class);
//		assertThat(this.restTemplete.getForObject("http://localhost:9002/users/getText",
//				String.class)).contains("hello");
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertThat(response.getBody()).contains(exampleUserJson);
	}
}
