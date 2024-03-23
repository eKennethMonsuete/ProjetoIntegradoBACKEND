package br.com.projecao.projetogym.api.controller;

import br.com.projecao.projetogym.api.service.userService;

import br.com.projecao.projetogym.api.user.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private userService userService;


    @GetMapping("aaa")
    public ResponseEntity testeOk(){
        var TesteTudo = repository.findAll();
        return ResponseEntity.ok(TesteTudo);

    }

    @GetMapping
    public List findUsers(){
        return this.userService.findUsers();


    }

    @PostMapping
    @Transactional
    public ResponseEntity salvarUser(@RequestBody userRegisterDTO data ){
        User newUser = new User(data);
        repository.save(newUser);
        System.out.println(data);
        return ResponseEntity.ok("User saved");

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO data) {
        Optional<User> optionalUser = repository.findById(data.id());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            user.setPassword(data.password());
            user.setEmail(data.email());
            return ResponseEntity.ok(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping()
    public ResponseEntity deleteUser(@RequestBody UpdateUserDTO data){
            repository.deleteById(data.id());
            return ResponseEntity.noContent().build();

            //talvez seja melhor usar o @PathVariable

        }










    }
