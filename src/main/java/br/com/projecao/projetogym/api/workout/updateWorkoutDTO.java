package br.com.projecao.projetogym.api.workout;

public record updateWorkoutDTO(
        Long id,
        String workout1,
        String workout2,
        String note
) {
}
