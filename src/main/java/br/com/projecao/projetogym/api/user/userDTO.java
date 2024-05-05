package br.com.projecao.projetogym.api.user;

import br.com.projecao.projetogym.api.measures.Measures;

import java.util.List;

public record userDTO(String name, String email, String password, String surname, String whatsapp, List<Measures> measures) {
}
