package br.com.projecao.projetogym.api.user;

import jakarta.validation.constraints.NotBlank;

public record userRegisterDTO(
        @NotBlank
        String name,
        String email,
        String password
) {




}
