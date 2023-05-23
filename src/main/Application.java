package main;

import javax.persistence.EntityManagerFactory;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import utils.JPAUtil;

public class Application {

	public static Logger logger = (Logger) LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
		emf.close();
	}

}
