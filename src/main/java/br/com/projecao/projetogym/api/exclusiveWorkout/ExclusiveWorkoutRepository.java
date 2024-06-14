package br.com.projecao.projetogym.api.exclusiveWorkout;

import br.com.projecao.projetogym.api.measures.Measures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExclusiveWorkoutRepository extends JpaRepository<ExclusiveWorkout, Long> {
}
