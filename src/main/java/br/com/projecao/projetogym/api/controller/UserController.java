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
    private userService userService;

    @GetMapping
    public List findUsers(){
        return this.userService.findUsers();}

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable Long id){
        return userService.findOneUser(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity salvarUser(@RequestBody userDTO data ){
       // User newUser = new User(data);
        userService.createUser(data);
       // System.out.println(data.measures());
        return ResponseEntity.ok(data);

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateUser(@RequestBody UpdateUserDTO data) {

        userService.updateUser(data.id(), data);
       return ResponseEntity.ok("deu certo");

    }

    @DeleteMapping()
    public ResponseEntity deleteUser(@RequestBody UpdateUserDTO id){
        userService.deleteUser(id);
            return  ResponseEntity.ok().build() ;

            //talvez seja melhor usar o @PathVariable @PathVariable Long id

        }










    }
