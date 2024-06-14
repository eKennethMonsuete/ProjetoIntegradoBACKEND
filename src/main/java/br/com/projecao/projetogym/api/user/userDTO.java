package br.com.projecao.projetogym.api.user;

import br.com.projecao.projetogym.api.measures.MeasuresDTO;
import br.com.projecao.projetogym.api.exclusiveWorkout.ExclusiveWorkoutDTO;

import java.util.List;

public record userDTO(String name, String email, String password, String surname, String whatsapp, List<MeasuresDTO> measures,
                      List<ExclusiveWorkoutDTO> exclusiveWorkoutDTOs) {


}
