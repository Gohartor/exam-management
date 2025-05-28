package org.example;

import jakarta.persistence.EntityManager;
import org.example.context.ApplicationContext;
import org.example.entity.enumeration.PersonStatus;
import org.example.entity.person.Admin;
import org.example.entity.person.Person;

public class Other {
    public static void main(String[] args) {


        EntityManager em = ApplicationContext.getInstance().getEntityManager();

        em.getTransaction().begin();




        em.getTransaction().commit();
    }
}
