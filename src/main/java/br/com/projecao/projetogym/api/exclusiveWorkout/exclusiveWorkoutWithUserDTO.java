package br.com.projecao.projetogym.api.exclusiveWorkout;

import  br.com.projecao.projetogym.api.user.userWorkoutDTO;

public record exclusiveWorkoutWithUserDTO(

        int id,
        String nome,
        String descricao,
        String objetivo,
        String workout1,
        String workout2,
        String workout3,
        String registrationDate,
        String autor,
        userWorkoutDTO user
) {
}
