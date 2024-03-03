package br.com.projecao.projetogym.api.user;

public record UpdateUserDTO(Long id, String name, String email, String password) {
}
