package br.com.projecao.projetogym.api.controller;

import br.com.projecao.projetogym.api.measures.Measures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.projecao.projetogym.api.service.measuresService;
import br.com.projecao.projetogym.api.measures.registerMeasuresDTO;

import java.util.List;

@RestController
@RequestMapping("/measures")
public class MeasuresController {

    @Autowired
    private measuresService measuresService;

    @GetMapping
    public List<Measures> findMeasures(){
        return this.measuresService.findAllMeasures();
    }

    @PostMapping
    public ResponseEntity saveMeasures(@RequestBody registerMeasuresDTO data){
       // Measures newMeasures = new Measures(data);
        measuresService.saveMeasures(data);
        return ResponseEntity.ok("Medidas Salvas");
    }


}
