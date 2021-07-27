package com.nsoft.ratingappbackend;

import com.nsoft.ratingappbackend.appuser.AppUserService;
import com.nsoft.ratingappbackend.auth.payload.RoleResponse;
import com.nsoft.ratingappbackend.auth.payload.TokenRequest;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@AllArgsConstructor
public class TestController {

	private final AppUserService appUserService;


	@GetMapping("test")
	public ResponseEntity<RoleResponse> payloadTest(@RequestBody TokenRequest request)
		throws IOException {
		try {
			RoleResponse response = appUserService.singIn(request);
			if (response.getRole() != null) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}
		} catch (IOException e) {
			RoleResponse response = new RoleResponse();
			response.setStatus("401 Unauthorized");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}
	}

}
