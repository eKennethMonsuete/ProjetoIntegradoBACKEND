package br.com.projecao.projetogym.api.measures;

import br.com.projecao.projetogym.api.user.User;

public record updateMeasuresDTO(

        Long id,

        float weight,

        float left_biceps,

        float right_biceps,

        float waist,

        float left_quadriceps,

        float right_quadriceps,

        float left_calf,

        float right_calf,

        String date,

        String registrationDate,

        User user


) { }
