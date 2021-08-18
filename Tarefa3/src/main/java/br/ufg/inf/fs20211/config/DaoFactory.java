package br.ufg.inf.fs20211.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {

	    static EntityManagerFactory emf;
	    static EntityManager em;

	    public static EntityManager getEm() {
	        if (em == null) {
	            emf = Persistence.createEntityManagerFactory("aula-jpa");
	            em = emf.createEntityManager();
	        }
	        return em;
	    }

	    public static void CloseConnection() {
	        if (em.isOpen()) 
	            em.close();
	    }
	}