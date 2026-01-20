package com.indepth.authservice.adapter;

import com.indepth.authservice.adapter.in.request.LoginRequestDTO;
import com.indepth.authservice.adapter.in.response.LoginResponseDTO;
import com.indepth.authservice.adapter.out.entities.Role;
import com.indepth.authservice.adapter.out.entities.UserCredential;
import com.indepth.authservice.domain.ports.in.FindUserByEmailUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/auth")
public class TokenController {

    private final JwtEncoder jwtEncoder;
    private final FindUserByEmailUseCasePort findUserByEmailUseCasePort;
    private final BCryptPasswordEncoder passwordEncoder;

    public TokenController(JwtEncoder jwtEncoder, FindUserByEmailUseCasePort findUserByEmailUseCasePort, BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.findUserByEmailUseCasePort = findUserByEmailUseCasePort;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        var user = findUserByEmailUseCasePort.execute(requestDTO.getEmail()).orElse(new UserCredential());
        if (user == null || !user.isLoginCorrect(requestDTO, passwordEncoder)) {
            throw new BadCredentialsException("Usuário ou Senha inválido.");
        }
        Instant now = Instant.now();
        long expiresIn = 300L;

        String scopes = user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .subject(user.getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        String jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));
    }
}
