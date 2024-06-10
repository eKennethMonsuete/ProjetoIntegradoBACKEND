package br.com.projecao.projetogym.api.workout;

import jakarta.validation.constraints.Size;

public record updateWorkoutDTO(
        Long id,


        String workout1,

        String workout2,
        String note,
        String name,
        String workoutName,
        String date,
        String socialMedia

) {
}
