package br.com.projecao.projetogym.api.user;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

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

        public User(UserRecordDTO userDTO){
            this.name = userDTO.name();
            this.email = userDTO.email();
            this.password = userDTO.password();

        }

        public void updateUser(UpdateUserDTO data){
            this.name = data.name();
            this.password = data.password();
            this.email = data.email();

        }

        //teste para o git


}
