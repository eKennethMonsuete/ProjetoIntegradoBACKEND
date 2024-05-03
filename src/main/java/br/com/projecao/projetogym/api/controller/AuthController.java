package br.com.projecao.projetogym.api.controller;


import br.com.projecao.projetogym.api.infra.security.TokenService;
import br.com.projecao.projetogym.api.user.AuthDTO;
import br.com.projecao.projetogym.api.service.userService;
import br.com.projecao.projetogym.api.user.LoginResponseDTO;
import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.userDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {


    @Autowired
     private userService userService;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));

    }

    @PostMapping("/register")
        public ResponseEntity register(@RequestBody @Valid userDTO data){
        userService.createUser(data);
        return ResponseEntity.ok(data);
    }
}
