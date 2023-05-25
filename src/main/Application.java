package main;

import javax.persistence.EntityManagerFactory;

import dao.AbbonamentoDAO;
import dao.BigliettoDAO;
import dao.ParcoMezziDAO;
import dao.PuntiVenditaDAO;
import dao.TesseraDAO;
import dao.TitoliDiViaggioDAO;
import dao.UtenteDAO;
import lombok.extern.slf4j.Slf4j;
import utils.JPAUtil;

@Slf4j
public class Application {

	static EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

	static UtenteDAO ud = new UtenteDAO();
	static TesseraDAO td = new TesseraDAO();
	static ParcoMezziDAO pmd = new ParcoMezziDAO(emf);
	static PuntiVenditaDAO pvd = new PuntiVenditaDAO(emf);
	static TitoliDiViaggioDAO tvd = new TitoliDiViaggioDAO(emf);
	static BigliettoDAO bd = new BigliettoDAO(emf);
	static AbbonamentoDAO ad = new AbbonamentoDAO(emf);

	public static void main(String[] args) {

		pmd.getTrattaPiuUtilizzata();

	}

}
