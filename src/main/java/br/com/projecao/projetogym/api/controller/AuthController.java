package br.com.projecao.projetogym.api.controller;


import br.com.projecao.projetogym.api.infra.security.TokenService;
import br.com.projecao.projetogym.api.user.*;
import br.com.projecao.projetogym.api.service.userService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {



     private final userService userService;
     private final TokenService tokenService;
     private final PasswordEncoder passwordEncoder;

     private final UserRepository repository;




    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO body){

        System.out.println("aqui chegou alguma coisa auth/login");

        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User email not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getName(), token, user.getId()));
        }
        return ResponseEntity.badRequest().build();

    }

        @PostMapping("/register")
        public ResponseEntity register(@RequestBody @Valid userDTO data){
        System.out.println("algo aqui no /auth/register");
            System.out.println(data);
        userService.createUser(data);
        return ResponseEntity.ok("deu certo, ainda bem");
    }
}
