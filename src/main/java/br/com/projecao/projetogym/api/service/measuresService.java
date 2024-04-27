package br.com.projecao.projetogym.api.service;

import br.com.projecao.projetogym.api.measures.Measures;
import br.com.projecao.projetogym.api.measures.updateMeasuresDTO;
import br.com.projecao.projetogym.api.measures.MeasuresRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class measuresService {

    @Autowired
    private MeasuresRepository repository;

    public List<Measures> findAllMeasures(){
        var listMeasures = repository.findAll();
        return listMeasures;
    }

    public Measures findOne(Long id){
        Optional<Measures> measure = repository.findById(id);
        if(measure.isPresent()){
            return measure.get();
        }else {
            throw new RuntimeException("Medida não encontrado com o ID: " + id);
        }
    }

    public void saveMeasures(updateMeasuresDTO data){
        Measures measures = new Measures(data);
        repository.save(measures);

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
            return ResponseEntity.ok(measures);
        }
        else {
            throw new EntityNotFoundException();

    }
  }

}
