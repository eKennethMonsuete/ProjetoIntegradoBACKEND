package br.com.projecao.projetogym.api.controller;


import br.com.projecao.projetogym.api.exclusiveWorkout.ExclusiveWorkout;
import br.com.projecao.projetogym.api.exclusiveWorkout.exclusiveWorkoutWithUserDTO;
import br.com.projecao.projetogym.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import br.com.projecao.projetogym.api.service.exclusiveWorkoutService;

import java.util.List;

@RestController
@RequestMapping("/exclusiveworkout")
public class ExclusiveWorkoutController {

    @Autowired
    private exclusiveWorkoutService exclusiveWorkoutService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<exclusiveWorkoutWithUserDTO> getAllExclusiveWorkouts() {
        return this.exclusiveWorkoutService.getAllExclusiveWorkouts();
    }

    @GetMapping("/{id}")
    public exclusiveWorkoutWithUserDTO findById(@PathVariable Long id) {
        return exclusiveWorkoutService.findOneExclusiveWorkoutById(id);
    }

    @PostMapping("savework/{userId}")
    @Transactional
    public ResponseEntity saveExclusiveWorkout(@RequestBody exclusiveWorkoutWithUserDTO data, @PathVariable Long userId) {
        System.out.println("bateu em POST controles exclusiveWorkout");
        ExclusiveWorkout saveExclusiveWorkout = exclusiveWorkoutService.saveExclusiveWorkout(data, userId);
        if(saveExclusiveWorkout != null) {
            System.out.println(data.toString());
            return ResponseEntity.ok().body("Treino exclusivo Salvo");//caso eu coloque saveExclu volta loop eterno.
        }else {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateExclusiveWorkout(@PathVariable Long id, @RequestBody exclusiveWorkoutWithUserDTO data) {
        exclusiveWorkoutService.updateExclusiveWorkout(id, data);
        return ResponseEntity.ok().body("Treino exclusivo atualizado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteExclusiveWorkout(@PathVariable Long id) {
        System.out.println("chegou algo no delete");
        exclusiveWorkoutService.deleteExclusiveWorkout(id);
        return ResponseEntity.ok().body("Treino exclusivo Deletado Permantentemente");
    }





}
