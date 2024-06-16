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
        System.out.println("bateu algo aqui user controler get by id");
        User user = userService.findOneUser(id);
        return userService.mapUserToDTO(user);
    }


    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    public void salvarUser(@RequestBody userDTO data){}

    @PutMapping("/{id}")
    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO data) {
        System.out.println("bateu aqui no put user/id");

        userService.updateUser(id, data);
       return ResponseEntity.ok("deu certo");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
            return  ResponseEntity.ok().build() ;

            //talvez seja melhor usar o @PathVariable @PathVariable Long id

        }










    }
