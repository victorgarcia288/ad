package serpis.ad;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PruebaHibernate {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[]args) {
		
		entityManagerFactory = 
				Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
		
		//showAll();
		
		//modify(2L);
		
		//remove(14L);
		
		//newCategoria();
		
		showAll();
		
		
		entityManagerFactory.close();
	}
	
	private static void newCategoria() {
		System.out.println("creando categoria nueva");
		Categoria categoria = new Categoria();
		categoria.setNombre("nuevo"+new Date());
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		System.out.println("creando "+ categoria);
		entityManager.persist(categoria);
		System.out.println("creada "+ categoria);
		entityManager.getTransaction().commit();
		
	}
	
	private static void showAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria>categorias =
				entityManager.createQuery("from Categoria order by id", Categoria.class).getResultList();
		for(Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
			
	}
	
	private static void modify(long id) {
		System.out.println("modificando categoria"+ id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.find(Categoria.class, id);
		
		categoria.setNombre("modificado"+new Date());
		
		entityManager.getTransaction().commit();
		
		
	}
	
	private static void remove(long id) {
		System.out.println("eliminando categoria"+ id);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		//Categoria categoria = entityManager.find(Categoria.class, id);
		Categoria categoria = entityManager.getReference(Categoria.class, id);
		entityManager.remove(categoria);
		entityManager.getTransaction().commit();
	}
			

}
