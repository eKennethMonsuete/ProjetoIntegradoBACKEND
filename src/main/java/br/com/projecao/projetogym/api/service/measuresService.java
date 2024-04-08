package br.com.projecao.projetogym.api.service;

import br.com.projecao.projetogym.api.measures.Measures;
import br.com.projecao.projetogym.api.measures.registerMeasuresDTO;
import br.com.projecao.projetogym.api.measures.MeasuresRepository;
import br.com.projecao.projetogym.api.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class measuresService {

    @Autowired
    private MeasuresRepository repository;

    public List<Measures> findAllMeasures(){
        var listMeasures = repository.findAll();
        return listMeasures;
    }

    public void saveMeasures(registerMeasuresDTO data){
        Measures measures = new Measures(data);
        repository.save(measures);



    }
}
