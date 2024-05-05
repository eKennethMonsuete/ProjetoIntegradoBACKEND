package br.com.projecao.projetogym.api.service;


import br.com.projecao.projetogym.api.infra.security.TokenService;
import br.com.projecao.projetogym.api.user.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    public List<User> findUsers(){
        var listarTudo = repository.findAll();
        return listarTudo;

    }

    public User findOneUser(Long id){
      Optional<User> user = repository.findById(id);
      if (user.isPresent()){
          return user.get();
      }
      else {
          throw new RuntimeException("Usuário não encontrado com o ID: " + id);
      }
    }

    public ResponseEntity createUser(userDTO data){
        Optional<User> user = this.repository.findByEmail(data.email());
        if(user.isEmpty()){
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(data.password()));
            newUser.setName(data.name());
            newUser.setWhatsapp(data.whatsapp());
            newUser.setSurname(data.surname());

            newUser.setEmail(data.email());

            repository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponseDTO(newUser.getName(), token)) ;
        }
        return ResponseEntity.badRequest().build();

    }

    public ResponseEntity updateUser(Long id, UpdateUserDTO data){
        Optional<User> optionalUser = repository.findById(data.id());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            user.setPassword(data.password());
            user.setEmail(data.email());
            user.setSurname(data.surname());
            user.setWhatsapp(data.whatsapp());
            return ResponseEntity.ok(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public ResponseEntity deleteUser(UpdateUserDTO data){
        repository.deleteById(data.id());
        return ResponseEntity.ok("user deletado");
    }

//    public Optional<User> findByEmailService(AuthDTO data){
//       return repository.findByEmail(data.email().toString());
//    }

}
