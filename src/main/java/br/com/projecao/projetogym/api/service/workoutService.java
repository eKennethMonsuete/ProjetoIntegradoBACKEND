package br.com.projecao.projetogym.api.service;


import br.com.projecao.projetogym.api.user.UpdateUserDTO;
import br.com.projecao.projetogym.api.workout.updateWorkoutDTO;
import br.com.projecao.projetogym.api.workout.workoutDTO;
import br.com.projecao.projetogym.api.workout.Workout;
import br.com.projecao.projetogym.api.workout.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class workoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public List<Workout> findAllWorkout(){
        var listMeasures = workoutRepository.findAll();
        return listMeasures;
    }

    public Workout findOne(Long id){
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
        if(workoutOptional.isPresent()){
            return workoutOptional.get();
        }else {
            throw new RuntimeException("Treino não encontrado com o ID: " + id);
        }
    }

    public void saveWorkout( workoutDTO data ){
        Workout workout = new Workout(data);
        workoutRepository.save(workout);

    }

    public ResponseEntity updateWorkout(Long id, updateWorkoutDTO data){
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
        if(workoutOptional.isPresent()){
            Workout workout = workoutOptional.get();
            workout.setWorkout1(data.workout1());
            workout.setWorkout2(data.workout2());
            workout.setNote(data.note());
            workout.setName(data.name());
            workout.setWorkoutName(data.workoutName());
            workout.setSocialMedia(data.socialMedia());
            return ResponseEntity.ok("treino atualizado");
        } else {
            throw new EntityNotFoundException();

        }

    }

    public ResponseEntity deleteWorkout(Long id){
        workoutRepository.deleteById(id);
        return ResponseEntity.ok("Treino deletado");
    }




}
