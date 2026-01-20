package com.indepth.authservice.adapter;


import com.indepth.authservice.adapter.in.request.UserCredentialRequestDTO;
import com.indepth.authservice.adapter.in.response.UserCredentialResponseDTO;
import com.indepth.authservice.domain.ports.in.SaveUserUseCasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SaveUserUseCasePort saveUserUseCasePort;

    @PostMapping("/signup")
    public ResponseEntity<UserCredentialResponseDTO> registerUser(@RequestBody UserCredentialRequestDTO userRequestDTO) {
        UserCredentialResponseDTO user = saveUserUseCasePort.execute(userRequestDTO);
        return ResponseEntity.ok(user);
    }

}
