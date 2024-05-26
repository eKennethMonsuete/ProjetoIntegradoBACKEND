package br.com.projecao.projetogym.api.infra.security;

import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        System.out.println("Token recebido: " + token);

        if (token != null) {
            String login = tokenService.validateToken(token);
            System.out.println("Login extraído do token: " + login);

            if (login != null) {
                Optional<User> userOpt = userRepository.findByEmail(login);
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("Usuário autenticado: " + user.getEmail());
                } else {
                    System.out.println("Usuário não encontrado: " + login);
                }
            } else {
                System.out.println("Token validation failed.");
            }
        } else {
            System.out.println("Token não presente no cabeçalho.");
        }
        filterChain.doFilter(request, response);
    }



    private String recoverToken(HttpServletRequest  request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");

    }
}




//@Override
//protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    var token = this.recoverToken(request);
//
//    var login = tokenService.validateToken(token);
//
//    if(login != null){
//        User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("user not found"));
//
//        var authorithies = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//        var authentication = new UsernamePasswordAuthenticationToken(user, null, authorithies);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//    }
//    filterChain.doFilter(request, response);
//}
//
//private String recoverToken(HttpServletRequest  request){
//    var authHeader = request.getHeader("Authorization");
//    if (authHeader == null) return null;
//    return authHeader.replace("Bearer ", "");
//}
//}

//private String recoverToken(HttpServletRequest  request){
//    var authHeader = request.getHeader("Authorization");
//    if (authHeader == null) return null;
//    return authHeader.replace("Bearer ", "");
//}



//esse

//@Override
//protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//    var token = this.recoverToken(request);
//    System.out.println("token aqui deve aparecer " + token);
//
//    var login = tokenService.validateToken(token);
//
//    if(login != null){
//        User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("user not found"));
//
//        var authorithies = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//        var authentication = new UsernamePasswordAuthenticationToken(user, null, authorithies);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//    }
//    filterChain.doFilter(request, response);
//}