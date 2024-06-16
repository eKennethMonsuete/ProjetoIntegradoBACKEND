package br.com.projecao.projetogym.api.service;


import br.com.projecao.projetogym.api.exclusiveWorkout.ExclusiveWorkout;
import br.com.projecao.projetogym.api.exclusiveWorkout.ExclusiveWorkoutDTO;
import br.com.projecao.projetogym.api.infra.security.TokenService;
import br.com.projecao.projetogym.api.measures.Measures;
import br.com.projecao.projetogym.api.measures.MeasuresDTO;
import br.com.projecao.projetogym.api.user.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.com.projecao.projetogym.api.user.userDTO;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class userService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    public List<userDTO> findUsers(){
        List<User> users = repository.findAll();
        return users.stream()
                .map(this::mapUserToDTO)
                .collect(Collectors.toList());

    }

    public ExclusiveWorkoutDTO mapExclusiveWorkoutDTO(ExclusiveWorkout exclusiveWorkout){
        return new ExclusiveWorkoutDTO(
                exclusiveWorkout.getId(),
                exclusiveWorkout.getNome(),
                exclusiveWorkout.getDescricao(),
                exclusiveWorkout.getObjetivo(),
                exclusiveWorkout.getWorkout1(),
                exclusiveWorkout.getWorkout2(),
                exclusiveWorkout.getWorkout3(),
                exclusiveWorkout.getRegistrationDate(),
                exclusiveWorkout.getAutor()
        );
    }

    public MeasuresDTO mapMeasureToDTO(Measures measure) {
        return new MeasuresDTO(
                measure.getId(),
                measure.getWeight(),
                measure.getLeft_biceps(),
                measure.getRight_biceps(),
                measure.getWaist(),
                measure.getLeft_quadriceps(),
                measure.getRight_quadriceps(),
                measure.getLeft_calf(),
                measure.getRight_calf(),
                measure.getDate(),
                measure.getRegistrationDate()

        );
    }


    public userDTO mapUserToDTO(User user) {
        List<MeasuresDTO> measureDTOs = user.getMeasures().stream()
                .map(this::mapMeasureToDTO)
                .collect(Collectors.toList());

        List<ExclusiveWorkoutDTO> exclusiveWorkoutDTOs = user.getExclusiveWorkouts().stream()
                .map(this::mapExclusiveWorkoutDTO)
                .collect(Collectors.toList());


        return new userDTO(user.getName(), user.getEmail(), user.getPassword(), user.getSurname(), user.getWhatsapp(), measureDTOs, exclusiveWorkoutDTOs );
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
            return ResponseEntity.ok(new LoginResponseDTO(newUser.getName(), token, newUser.getId())) ;
        }
        return ResponseEntity.badRequest().build();

    }

    public ResponseEntity updateUser(Long id, UpdateUserDTO data){
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            user.setPassword(passwordEncoder.encode(data.password()));
            user.setEmail(data.email());
            user.setSurname(data.surname());
            user.setWhatsapp(data.whatsapp());
            return ResponseEntity.ok(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public ResponseEntity deleteUser(Long id){
        repository.deleteById(id);
        return ResponseEntity.ok("user deletado");
    }

//    public Optional<User> findByEmailService(AuthDTO data){
//       return repository.findByEmail(data.email().toString());
//    }

}
