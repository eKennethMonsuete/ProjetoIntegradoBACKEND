package br.com.projecao.projetogym.api.service;

import br.com.projecao.projetogym.api.measures.Measures;
import br.com.projecao.projetogym.api.user.userDTO;
import br.com.projecao.projetogym.api.measures.updateMeasuresDTO;
import br.com.projecao.projetogym.api.measures.MeasuresRepository;
import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.UserRepository;
import br.com.projecao.projetogym.api.measures.MeasureWithUserDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class measuresService {

    @Autowired
    private MeasuresRepository repository;

    @Autowired
    private UserRepository userRepository;



    public List<MeasureWithUserDTO> findAllMeasures(){
        List<Measures> measures = repository.findAll();
        return measures.stream()
                .map(this::mapMeasureWithUserToDTO)
                .collect(Collectors.toList());
    }

    public MeasureWithUserDTO mapMeasureWithUserToDTO(Measures measure) {
        User user = measure.getUser();
        userDTO userDTO1 = new userDTO(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getSurname(),
                user.getWhatsapp(),
                null, // Não incluir as medidas do usuário para evitar recursão
                 null );
        return new MeasureWithUserDTO(
                measure.getWeight(),
                measure.getLeft_biceps(),
                measure.getRight_biceps(),
                measure.getWaist(),
                measure.getLeft_quadriceps(),
                measure.getRight_quadriceps(),
                measure.getLeft_calf(),
                measure.getRight_calf(),
                measure.getDate(),
                measure.getRegistrationDate(),
                userDTO1

        );
    }




    public MeasureWithUserDTO findOne(Long id){
        Optional<Measures> measureOpitional = repository.findById(id);
        if(measureOpitional.isPresent()){
            Measures measures = measureOpitional.get();
            return mapMeasureWithUserToDTO(measures);
        }else {
            throw new RuntimeException("Medida não encontrado com o ID: " + id);
        }
    }

    public Measures saveMeasures(updateMeasuresDTO data, Long id){
        User user = userRepository.findById(id).orElse(null);

        if(user != null){
            Measures measures = new Measures();

            measures.setWeight(data.weight());
            measures.setLeft_biceps(data.left_biceps());
            measures.setRight_biceps(data.right_biceps());
            measures.setWaist(data.waist());
            measures.setLeft_quadriceps(data.left_quadriceps());
            measures.setRight_quadriceps(data.right_quadriceps());
            measures.setLeft_calf(data.left_calf());
            measures.setRight_calf(data.right_calf());
            measures.setDate(data.date());
            measures.setRegistrationDate(data.registrationDate());
            measures.setUser(user);

            return repository.save(measures);
        }else {
            return null;
        }





    }

    public ResponseEntity updateMeasures(Long id, updateMeasuresDTO data){
        Optional<Measures> measure = repository.findById(id);
        if(measure.isPresent()){
            Measures measures = measure.get();
            measures.setWeight(data.weight());
            measures.setLeft_biceps(data.left_biceps());
            measures.setRight_biceps(data.right_biceps());
            measures.setWaist(data.waist());
            measures.setLeft_quadriceps(data.left_quadriceps());
            measures.setRight_quadriceps(data.right_quadriceps());
            measures.setLeft_calf(data.left_calf());
            measures.setRight_calf(data.right_calf());
            measures.setDate(data.date());
            return ResponseEntity.ok(measures);
        }
        else {
            throw new EntityNotFoundException();

    }
  }

    public ResponseEntity deleteMeasure(Long id){
        repository.deleteById(id);
        return ResponseEntity.ok("Medida deletado");
    }

}
