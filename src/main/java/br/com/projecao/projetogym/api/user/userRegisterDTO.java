package br.com.projecao.projetogym.api.user;

import br.com.projecao.projetogym.api.measures.Measures;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record userRegisterDTO(
        @NotBlank
        String name,

        @Email
        @NotNull
        @NotBlank
        String email,
        String password,

        List<Measures> measures
) {




}
