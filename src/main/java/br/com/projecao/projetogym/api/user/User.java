package br.com.projecao.projetogym.api.user;


import br.com.projecao.projetogym.api.measures.Measures;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy="user")
    private List<Measures> measures = new ArrayList<>();






        public User(userRegisterDTO data){
            this.name = data.name();
            this.email = data.email();
            this.password = data.password();

        }






}
