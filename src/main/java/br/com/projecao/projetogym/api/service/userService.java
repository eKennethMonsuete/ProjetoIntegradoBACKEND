package br.com.projecao.projetogym.api.service;


import br.com.projecao.projetogym.api.user.User;
import br.com.projecao.projetogym.api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {

    @Autowired
    private UserRepository repository;

    public List<User> findUsers(){
        var listarTudo = repository.findAll();
        return listarTudo;

    }
}
