package br.com.projecao.projetogym.api.controller;

import br.com.projecao.projetogym.api.workout.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.com.projecao.projetogym.api.service.workoutService;
import br.com.projecao.projetogym.api.workout.workoutDTO;
import br.com.projecao.projetogym.api.workout.updateWorkoutDTO;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private workoutService workourService;

    @GetMapping
    public List<Workout> findWorkout(){return workourService.findAllWorkout();}

    @GetMapping("/{id}")
    public Workout getOneWorkout(@PathVariable Long id){ return workourService.findOne(id);}

    @PostMapping
    @Transactional
    public ResponseEntity saveWorkout(@RequestHeader("Authorization") String token,@RequestBody workoutDTO data){
        System.out.println("chegou aqui em /workout save");
        workourService.saveWorkout(data);
        return ResponseEntity.ok("Treino Salvo");

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateWorkout(@RequestBody updateWorkoutDTO data){
        workourService.updateWorkout(data.id(), data);
        return ResponseEntity.ok("Treino Atualizado");

    }


}

