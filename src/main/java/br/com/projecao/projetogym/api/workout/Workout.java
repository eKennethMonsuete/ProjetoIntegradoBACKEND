package br.com.projecao.projetogym.api.workout;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "workout")
@EqualsAndHashCode(of = "id")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workout1;
    private String workout2;
    private String note;

    public Workout(workoutDTO data){
        this.workout1 = data.workout1();
        this.workout2 = data.workout2();
        this.note = data.note();

    }

    public Workout(updateWorkoutDTO data){
        this.workout1 = data.workout1();
        this.workout2 = data.workout2();
        this.note = data.note();

    }


}
