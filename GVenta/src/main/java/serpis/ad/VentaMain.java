package serpis.ad;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VentaMain {
	private static EntityManagerFactory entityManagerFactory; 
	
	public static void main(String[]args) {
		entityManagerFactory = 
				Persistence.createEntityManagerFactory("serpis.ad.gventa");
		
		showAll();
		
		entityManagerFactory.close();
		
		
		
	}
	
	private static void showAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager
				.createQuery("from Categoria order by id", Categoria.class)
				.getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void newArticulo() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.getReference(Categoria.class, 1L);
		Articulo articulo = new Articulo();
		articulo.setNombre("nuevo " + new Date());
		articulo.setPrecio(new BigDecimal(6));
		articulo.setCategoria(categoria);
		entityManager.persist(articulo);
		entityManager.getTransaction().commit();
	}

}
