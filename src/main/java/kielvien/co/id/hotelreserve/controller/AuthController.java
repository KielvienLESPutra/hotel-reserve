package kielvien.co.id.hotelreserve.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kielvien.co.id.hotelreserve.models.GeneralResponse;
import kielvien.co.id.hotelreserve.models.LoginRequest;
import kielvien.co.id.hotelreserve.models.LoginResponse;
import kielvien.co.id.hotelreserve.services.AuthService;

@RestController
public class AuthController {
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping(path = "/api/v1/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GeneralResponse<LoginResponse>> login(@RequestBody LoginRequest req) {
		return ResponseEntity.ok(authService.authLogin(req));
	}
}
