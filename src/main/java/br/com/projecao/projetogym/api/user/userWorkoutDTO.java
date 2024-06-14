package br.com.projecao.projetogym.api.user;

import br.com.projecao.projetogym.api.exclusiveWorkout.ExclusiveWorkoutDTO;
import br.com.projecao.projetogym.api.measures.MeasuresDTO;

import java.util.List;

public record userWorkoutDTO(String name, String email, String password, String surname, String whatsapp, List<ExclusiveWorkoutDTO> exclusiveWorkoutDTOS) {
}
