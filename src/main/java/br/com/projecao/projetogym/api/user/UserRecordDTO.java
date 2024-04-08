package br.com.projecao.projetogym.api.user;

import br.com.projecao.projetogym.api.measures.Measures;

import java.util.List;

public record UserRecordDTO(String name, String email, String password, List<Measures> measures) {
}
