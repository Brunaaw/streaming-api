package com.example.streamingapi;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenServices tokenServices;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, TokenServices tokenServices) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenServices = tokenServices;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenServices.getToken((UserEntity) auth.getPrincipal());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if (userService.existsByUsername(registerDTO.getLogin()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        UserEntity newUser = new UserEntity();
        BeanUtils.copyProperties(registerDTO, newUser);
        newUser.setPassword(encryptedPassword);
        userService.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
