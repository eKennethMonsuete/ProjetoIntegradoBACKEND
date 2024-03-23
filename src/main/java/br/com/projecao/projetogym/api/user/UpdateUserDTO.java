package br.com.projecao.projetogym.api.user;


import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.NotFound;

public record UpdateUserDTO(
        @NotNull
        Long id,
        String name,
        String email,
        String password) {
}
