package br.com.projecao.projetogym.api.workout;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private String name;
    private String workoutName;
    private String date;
    private String socialMedia;


    public Workout(workoutDTO data){
        this.workout1 = data.workout1();
        this.workout2 = data.workout2();
        this.note = data.note();
        this.date = data.date();
        this.name = data.name();
        this.workoutName = data.workoutName();
        this.socialMedia = data.socialMedia();

    }

    public Workout(updateWorkoutDTO data){
        this.workout1 = data.workout1();
        this.workout2 = data.workout2();
        this.note = data.note();
        this.date = data.date();
        this.name = data.name();
        this.workoutName = data.workoutName();
        this.socialMedia = data.socialMedia();

    }

    @PrePersist
    public void prePersist() {
        // Define a data atual no formato "dd/MM/yyyy"
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


}
