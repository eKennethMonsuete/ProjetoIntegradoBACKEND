package br.com.projecao.projetogym.api.measures;

import br.com.projecao.projetogym.api.user.userDTO1;

public record MeasuresDTO(
                            Long id,

                            float weight,

                          float left_biceps,

                          float right_biceps,

                          float waist,

                          float left_quadriceps,

                          float right_quadriceps,

                          float left_calf,

                          float right_calf

                         ) {
}
