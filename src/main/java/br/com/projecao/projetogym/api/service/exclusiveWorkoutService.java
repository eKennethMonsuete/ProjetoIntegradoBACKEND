package br.com.projecao.projetogym.api.service;

import br.com.projecao.projetogym.api.exclusiveWorkout.ExclusiveWorkout;
import br.com.projecao.projetogym.api.exclusiveWorkout.ExclusiveWorkoutRepository;
import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.projecao.projetogym.api.exclusiveWorkout.exclusiveWorkoutWithUserDTO;
import br.com.projecao.projetogym.api.user.userWorkoutDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class exclusiveWorkoutService {

    @Autowired
    private ExclusiveWorkoutRepository repository;

    @Autowired
    private UserRepository userRepository;


    public List<exclusiveWorkoutWithUserDTO> getAllExclusiveWorkouts() {
        List<ExclusiveWorkout> exclusiveWorkouts = repository.findAll();
        return exclusiveWorkouts.stream()
                .map(this::mapExclusiveWorkoutWithUserDTO)
                .collect(Collectors.toList());

    }
    public exclusiveWorkoutWithUserDTO mapExclusiveWorkoutWithUserDTO(ExclusiveWorkout exclusiveWorkout) {
        User user = exclusiveWorkout.getUser();
        userWorkoutDTO userWorkoutDTO1 = new userWorkoutDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getSurname(),
                user.getWhatsapp(),
                null

        );
        return new exclusiveWorkoutWithUserDTO(
                exclusiveWorkout.getId(),
                exclusiveWorkout.getNome(),
                exclusiveWorkout.getDescricao(),
                exclusiveWorkout.getObjetivo(),
                exclusiveWorkout.getWorkout1(),
                exclusiveWorkout.getWorkout2(),
                exclusiveWorkout.getWorkout3(),
                exclusiveWorkout.getRegistrationDate(),
                exclusiveWorkout.getAutor(),
                userWorkoutDTO1
        );
    }

    public exclusiveWorkoutWithUserDTO findOneExclusiveWorkoutById(Long id) {
        Optional<ExclusiveWorkout> exclusiveWorkoutOptional = repository.findById(id);
        if (exclusiveWorkoutOptional.isPresent()){
            ExclusiveWorkout exclusiveWorkout = exclusiveWorkoutOptional.get();
            return mapExclusiveWorkoutWithUserDTO(exclusiveWorkout);
        }else {
            throw  new RuntimeException("Treino Exclusivo não encontrado "+id);
        }
    }

    public ExclusiveWorkout saveExclusiveWorkout(exclusiveWorkoutWithUserDTO data, Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user != null){
            ExclusiveWorkout exclusiveWorkout = new ExclusiveWorkout();
            exclusiveWorkout.setUser(user);
            exclusiveWorkout.setNome(data.nome());
            exclusiveWorkout.setDescricao(data.descricao());
            exclusiveWorkout.setObjetivo(data.objetivo());
            exclusiveWorkout.setWorkout1(data.workout1());
            exclusiveWorkout.setWorkout2(data.workout2());
            exclusiveWorkout.setWorkout3(data.workout3());
            exclusiveWorkout.setRegistrationDate(data.registrationDate());
            exclusiveWorkout.setAutor(data.autor());
            return repository.save(exclusiveWorkout);
        }else {
            return null;
        }
    }

    public ResponseEntity updateExclusiveWorkout(Long id, exclusiveWorkoutWithUserDTO data ){
        Optional<ExclusiveWorkout> exclusiveWorkoutOptional = repository.findById(id);
        if (exclusiveWorkoutOptional.isPresent()){
            ExclusiveWorkout exclusiveWorkout = exclusiveWorkoutOptional.get();
            exclusiveWorkout.setNome(data.nome());
            exclusiveWorkout.setDescricao(data.descricao());
            exclusiveWorkout.setObjetivo(data.objetivo());
            exclusiveWorkout.setWorkout1(data.workout1());
            exclusiveWorkout.setWorkout2(data.workout2());
            exclusiveWorkout.setWorkout3(data.workout3());
            exclusiveWorkout.setAutor(data.autor());
            return ResponseEntity.ok(exclusiveWorkoutOptional);
        }else {
            throw new EntityNotFoundException();

        }



    }


    public ResponseEntity deleteExclusiveWorkout(Long id){
        repository.deleteById(id);
        return ResponseEntity.ok("Medida deletado");

    }


}
