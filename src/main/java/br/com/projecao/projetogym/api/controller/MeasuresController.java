package br.com.projecao.projetogym.api.controller;

import br.com.projecao.projetogym.api.measures.Measures;
import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import br.com.projecao.projetogym.api.service.measuresService;
import br.com.projecao.projetogym.api.measures.updateMeasuresDTO;

import java.util.List;

@RestController
@RequestMapping("/measures")
public class MeasuresController {

    @Autowired
    private measuresService measuresService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Measures> findMeasures(){
        return this.measuresService.findAllMeasures();
    }

    @GetMapping("/{id}")
    public Measures getOneMeasure(@PathVariable Long id){
        return measuresService.findOne(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity saveMeasures(@RequestBody updateMeasuresDTO data){
       // Measures newMeasures = new Measures(data);
        measuresService.saveMeasures(data);
        return ResponseEntity.ok("Medidas Salvas");
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateMeasure(@RequestBody updateMeasuresDTO data){
        measuresService.updateMeasures(data.id(), data);
        return ResponseEntity.ok("deu certo");
    }

    public void salvarMedidasComUser(@RequestBody updateMeasuresDTO data){
        Measures newMeasures = new Measures(data);
    }






}
