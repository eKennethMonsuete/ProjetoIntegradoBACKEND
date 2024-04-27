package br.com.projecao.projetogym.api.service;


import br.com.projecao.projetogym.api.user.UpdateUserDTO;
import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import br.com.projecao.projetogym.api.user.userDTO;

import java.util.List;
import java.util.Optional;

@Service
public class userService {

    @Autowired
    private UserRepository repository;

    public List<User> findUsers(){
        var listarTudo = repository.findAll();
        return listarTudo;

    }

    public User findOneUser(Long id){
      Optional<User> user = repository.findById(id);
      if (user.isPresent()){
          return user.get();
      }
      else {
          throw new RuntimeException("Usuário não encontrado com o ID: " + id);
      }
    }

    public ResponseEntity createUser(userDTO data){
        User newUser = new User(data);
        repository.save(newUser);
        return ResponseEntity.ok(newUser) ;

    }

    public ResponseEntity updateUser(Long id, UpdateUserDTO data){
        Optional<User> optionalUser = repository.findById(data.id());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            user.setPassword(data.password());
            user.setEmail(data.email());
            user.setSurname(data.surname());
            user.setWhatsapp(data.whatsapp());
            return ResponseEntity.ok(user);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public ResponseEntity deleteUser(UpdateUserDTO data){
        repository.deleteById(data.id());
        return ResponseEntity.ok("user deletado");
    }


}
