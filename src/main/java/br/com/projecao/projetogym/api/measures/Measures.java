package br.com.projecao.projetogym.api.measures;

import br.com.projecao.projetogym.api.user.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "measures")
@EqualsAndHashCode(of = "id")
public class Measures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float weight;

    private float left_biceps;

    private float right_biceps;

    private float waist;

    private float left_quadriceps;

    private float right_quadriceps;

    private float left_calf;

    private float right_calf;

    private String date;

    private String registrationDate;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Measures(updateMeasuresDTO data) {
        this.weight = data.weight();
        this.left_biceps = data.left_biceps();;
        this.right_biceps = data.right_biceps(); ;
        this.waist = data.waist(); ;
        this.left_quadriceps = data.left_quadriceps(); ;
        this.right_quadriceps = data.right_quadriceps(); ;
        this.left_calf = data.left_calf();
        this.right_calf = data.right_calf();
        this.user = data.user();
        this.date = data.date();
        this.registrationDate = data.registrationDate();
    }

    public Measures(float weight, float left_biceps, float right_biceps, float waist, float left_quadriceps, float right_quadriceps, float left_calf, float right_calf, String date, String registrationDate  ) {
        this.weight = weight;
        this.left_biceps = left_biceps;
        this.right_biceps = right_biceps;
        this.waist = waist;
        this.left_quadriceps = left_quadriceps;
        this.right_quadriceps = right_quadriceps;
        this.left_calf = left_calf;
        this.right_calf = right_calf;
        this.date = date;
        this.registrationDate = registrationDate;
    }

    @PrePersist
    public void prePersist() {
        // Define a data atual no formato "dd/MM/yyyy"
        this.registrationDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


}
