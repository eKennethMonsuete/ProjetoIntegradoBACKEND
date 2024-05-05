package br.com.projecao.projetogym.api.user;


import br.com.projecao.projetogym.api.measures.Measures;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
@EqualsAndHashCode(of = "id")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String whatsapp;

    private String password;

    private UserRole role = UserRole.valueOf("USER") ;



    @OneToMany(mappedBy="user")
    private List<Measures> measures = new ArrayList<>();



//        public User(userDTO data){
//            this.name = data.name();
//            this.surname = data.surname();
//            this.email = data.email();
//            this.whatsapp = data.whatsapp();
//            this.password = data.password();
//        }

    public User(userDTO data, String encrypterPassword, UserRole role) {
        this.name = data.name();
        this.surname = data.surname();
        this.email = data.email();
        this.whatsapp = data.whatsapp();
        this.password = encrypterPassword;
        this.role = role;

    }


}
