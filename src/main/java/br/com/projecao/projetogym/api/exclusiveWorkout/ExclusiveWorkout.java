package br.com.projecao.projetogym.api.exclusiveWorkout;


import br.com.projecao.projetogym.api.user.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Transactional
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exclusive_workout")
@EqualsAndHashCode(of = "id")
public class ExclusiveWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String nome;
    private String descricao;
    private String objetivo;
    private String workout1;
    private String workout2;
    private String workout3;
    private String registrationDate;
    private String autor;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @PrePersist
    public void prePersist() {
        // Define a data atual no formato "dd/MM/yyyy"
        this.registrationDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public ExclusiveWorkout(ExclusiveWorkoutDTO dto){
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.objetivo = dto.objetivo();
        this.workout1 = dto.workout1();
        this.workout2 = dto.workout2();
        this.workout3 = dto.workout3();
        this.registrationDate = dto.registrationDate();
        this.autor = dto.autor();

    }

    public ExclusiveWorkout(String autor, String registrationDate, String workout3, String workout2, String workout1, String objetivo, String descricao, String nome) {
        this.autor = autor;
        this.registrationDate = registrationDate;
        this.workout3 = workout3;
        this.workout2 = workout2;
        this.workout1 = workout1;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.nome = nome;
    }

    public ExclusiveWorkout() {

    }


}
