package kielvien.co.id.hotelreserve.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import kielvien.co.id.hotelreserve.models.GeneralResponse;
import kielvien.co.id.hotelreserve.models.LoginRequest;
import kielvien.co.id.hotelreserve.models.LoginResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	ObjectMapper objectMapper;

	@LocalServerPort
	private int randomServerPort;
	private String endpoint;
	private LoginRequest requestLogin;

	@BeforeEach
	void initLoginTest() {
		endpoint = "http://localhost:" + randomServerPort + "/api/v1/login";
		
		requestLogin = new LoginRequest();
		requestLogin.setUsername("Admin");
		requestLogin.setPassword("AdminPassword");
	}
	
	@Nested
	class loginIntegrationClassTest{		
		@Test
		void loginSuccess() {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			ParameterizedTypeReference<GeneralResponse<LoginResponse>> respType = new ParameterizedTypeReference<GeneralResponse<LoginResponse>>() {
			};

			ResponseEntity<GeneralResponse<LoginResponse>> resp = restTemplate.exchange(endpoint, HttpMethod.POST,
					new HttpEntity<>(requestLogin), respType);

			assertEquals(HttpStatus.OK, resp.getStatusCode());
			assertEquals("00", resp.getBody().getResponseCode());
			assertEquals("Success", resp.getBody().getDesc());
		}
		
		@Test
		void loginFailPassword() {
			requestLogin.setPassword("PasswordWrong");
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			ParameterizedTypeReference<GeneralResponse<LoginResponse>> respType = new ParameterizedTypeReference<GeneralResponse<LoginResponse>>() {
			};

			ResponseEntity<GeneralResponse<LoginResponse>> resp = restTemplate.exchange(endpoint, HttpMethod.POST,
					new HttpEntity<>(requestLogin), respType);

			assertEquals(HttpStatus.OK, resp.getStatusCode());
			assertEquals("01", resp.getBody().getResponseCode());
			assertEquals("Error Authorized", resp.getBody().getDesc());
		}
		
		@Test
		void loginFailUsername() {
			requestLogin.setUsername("UsernameWrong");
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			ParameterizedTypeReference<GeneralResponse<LoginResponse>> respType = new ParameterizedTypeReference<GeneralResponse<LoginResponse>>() {
			};

			ResponseEntity<GeneralResponse<LoginResponse>> resp = restTemplate.exchange(endpoint, HttpMethod.POST,
					new HttpEntity<>(requestLogin), respType);

			assertEquals(HttpStatus.OK, resp.getStatusCode());
			assertEquals("01", resp.getBody().getResponseCode());
			assertEquals("Error Authorized", resp.getBody().getDesc());
		}
	}
}
