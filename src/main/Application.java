package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import lombok.extern.slf4j.Slf4j;
import utils.JPAUtil;
@Slf4j
public class Application {
	private static EntityManagerFactory emf =  JPAUtil.getEntityManagerFactory();   
	
	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		
		
		
		em.close();
		emf.close();
	}
}
