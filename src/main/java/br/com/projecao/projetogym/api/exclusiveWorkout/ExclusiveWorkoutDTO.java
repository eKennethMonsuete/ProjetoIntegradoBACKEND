package br.com.projecao.projetogym.api.exclusiveWorkout;

public record ExclusiveWorkoutDTO(

         int id,
         String nome,
         String descricao,
         String objetivo,
         String workout1,
         String workout2,
         String workout3,
         String registrationDate,
         String autor
) {
}
