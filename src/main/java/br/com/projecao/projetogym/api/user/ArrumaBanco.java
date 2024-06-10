package br.com.projecao.projetogym.api.user;

import br.com.projecao.projetogym.api.measures.Measures;
import br.com.projecao.projetogym.api.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ArrumaBanco {

    public static void main(String[] args) {




        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gymbro");

        EntityManager em = emf.createEntityManager();

       User user = new User();



       // Measures m2 = new Measures();

     //   user.setName("Coocoo");
       // user.setEmail("adasasd@asda");
       // user.setPassword("121345");


        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();


    }








}
