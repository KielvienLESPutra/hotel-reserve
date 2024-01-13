package kielvien.co.id.hotelreserve.services;

import org.springframework.stereotype.Service;

import kielvien.co.id.hotelreserve.models.GeneralResponse;
import kielvien.co.id.hotelreserve.models.LoginRequest;
import kielvien.co.id.hotelreserve.models.LoginResponse;

@Service
public class AuthService {
	public GeneralResponse<LoginResponse> authLogin(LoginRequest req) {
		LoginResponse message = new LoginResponse();
		GeneralResponse<LoginResponse> resp = new GeneralResponse<LoginResponse>();
		if (req.getUsername().equals("Admin") && req.getPassword().equals("AdminPassword")) {
			message.setMessage("Success Login");

			resp.setResponseCode("00");
			resp.setDesc("Success");
			resp.setData(message);
			return resp;
		}

		message.setMessage("Error username and password not match");
		resp.setResponseCode("01");
		resp.setDesc("Error Authorized");
		return resp;
	}
}
