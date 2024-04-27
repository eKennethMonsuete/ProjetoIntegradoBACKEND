package br.com.projecao.projetogym.api.workout;


import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long>  {
}
