package br.com.projecao.projetogym.api.controller;

import br.com.projecao.projetogym.api.measures.MeasureWithUserDTO;
import br.com.projecao.projetogym.api.measures.Measures;
import br.com.projecao.projetogym.api.user.UpdateUserDTO;
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
    public List<MeasureWithUserDTO> findMeasures(){
        return this.measuresService.findAllMeasures();
    }

    @GetMapping("/{id}")
    public MeasureWithUserDTO getOneMeasure(@PathVariable Long id){
        return measuresService.findOne(id);
    }


    @PostMapping("save/{userId}")
    @Transactional
    public ResponseEntity saveMeasures(@RequestBody updateMeasuresDTO data, @PathVariable Long userId){

        Measures saveMeasures = measuresService.saveMeasures(data, userId);
        if (saveMeasures != null){
            return ResponseEntity.ok("Medidas Salvas");
        }else {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

    }

    //@RequestHeader("Authorization") String token,

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateMeasure(@PathVariable Long id, @RequestBody updateMeasuresDTO data){
        measuresService.updateMeasures(id, data);
        return ResponseEntity.ok("deu certo");
    }

    public void salvarMedidasComUser(@RequestBody updateMeasuresDTO data){
        Measures newMeasures = new Measures(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        System.out.println("delete measure + algum id ");
        measuresService.deleteMeasure(id);
        return  ResponseEntity.ok().build() ;

        //talvez seja melhor usar o @PathVariable @PathVariable Long id

    }




}
