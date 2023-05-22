package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;



import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import utils.JPAUtil;

public class Application {

	private static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
	
	public static Logger logger = (Logger) LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
	}

}
