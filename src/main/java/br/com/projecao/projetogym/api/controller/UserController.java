package br.com.projecao.projetogym.api.controller;

import br.com.projecao.projetogym.api.service.userService;

import br.com.projecao.projetogym.api.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private userService userService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<userDTO> findUsers(){
        return this.userService.findUsers();}

    @GetMapping("/{id}")
    public userDTO getOneUser(@PathVariable Long id){
        User user = userService.findOneUser(id);
        return userService.mapUserToDTO(user);
    }

    @PostMapping
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity salvarUser(@RequestBody userDTO data ){
       // User newUser = new User(data);
        userService.createUser(data);
       System.out.println("algo no user 8080");
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
