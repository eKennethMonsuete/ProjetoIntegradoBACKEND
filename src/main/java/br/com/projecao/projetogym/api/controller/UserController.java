package br.com.projecao.projetogym.api.controller;

import br.com.projecao.projetogym.api.user.UpdateUserDTO;
import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.UserRecordDTO;
import br.com.projecao.projetogym.api.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;


    @GetMapping
    public ResponseEntity testeOk(){
        var TesteTudo = repository.findAll();
        return ResponseEntity.ok(TesteTudo);

    }

    @PostMapping
    public ResponseEntity salvarUser(@RequestBody UserRecordDTO data ){
        User newUser = new User(data);
        repository.save(newUser);
        System.out.println(data);
        return ResponseEntity.ok().build();

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO data){
        Optional<User> optionalUser = repository.findById(data.id());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(data.name());
            user.setPassword(data.password());
            user.setEmail(data.email());
            return ResponseEntity.ok(user);
        }else {
            throw new EntityNotFoundException();
        }










    }
}
