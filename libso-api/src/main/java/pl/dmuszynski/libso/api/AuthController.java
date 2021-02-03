package pl.dmuszynski.libso.api;

import pl.dmuszynski.libso.payload.dto.request.SignInRequestDTO;
import pl.dmuszynski.libso.payload.dto.request.SignUpRequestDTO;
import pl.dmuszynski.libso.payload.dto.response.JwtResponseDTO;
import pl.dmuszynski.libso.service.RegistrationService;
import pl.dmuszynski.libso.service.LoginService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "libso")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final RegistrationService registrationService;
    private final LoginService loginService;

    @PostMapping(value = "/sign-up")
    public ResponseEntity<HttpStatus> signUp(@RequestBody SignUpRequestDTO signUpDetails) {
        this.registrationService.signUp(signUpDetails.getEmail(), signUpDetails.getUsername(), signUpDetails.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<JwtResponseDTO> signIn(@RequestBody SignInRequestDTO signIpDetails) {
        final JwtResponseDTO userJwtResponse = this.loginService.signIn(signIpDetails.getUsername(), signIpDetails.getPassword());
        return new ResponseEntity<>(userJwtResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/token")
    public ResponseEntity<String> activateUser(@RequestParam String value) {
        final String activationMessage = this.registrationService.activateAccountByUserToken(value);
        return new ResponseEntity<>(activationMessage, HttpStatus.OK);
    }
}
