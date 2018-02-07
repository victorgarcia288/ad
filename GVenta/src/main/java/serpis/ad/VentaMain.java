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
		
//		showCategoria();
//		
//	    showArticulos();
//		
//		showCliente();
		
//		modify(23L);
		
//		remove (2L);
		
//		newCategoria();
		
		//showAll();
		
		//newArticulo();
		
		newPedido();
		
		entityManagerFactory.close();	
		
		entityManagerFactory.close();
		
	}
	
//	private static void showAll() {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		entityManager.getTransaction().begin();
//		List<Categoria> categorias = entityManager
//				.createQuery("from Categoria order by id", Categoria.class)
//				.getResultList();
//		for (Categoria categoria : categorias)
//			System.out.println(categoria);
//		entityManager.getTransaction().commit();
//	}
	

	private static <TEntity> void showAll(Class<TEntity> entityType) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		String queryString = String.format("from %s order by id", entityType.getSimpleName());
		List<TEntity>  entities = entityManager
				.createQuery(queryString, entityType)
				.getResultList();
		for (TEntity entity : entities)
			System.out.println(entity);
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
	
	private static void newPedido() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Pedido pedido = new Pedido();
		Cliente cliente = entityManager.getReference(Cliente.class, 1L);
		pedido.setCliente(cliente);
		PedidoLinea pedidoLinea1 = new PedidoLinea();
//		//OjO las dos sentencias siguientes mantienen las sincronizada la osociación
//		pedido.getPedidoLineas().add(pedidoLinea1);
//		pedidoLinea1.setPedido(pedido);
		pedido.add(pedidoLinea1);
		Articulo articulo = entityManager.getReference(Articulo.class, 1L);
		pedidoLinea1.setArticulo(articulo);
		
		for(PedidoLinea pedidoLinea : pedido.getPedidoLineas())
			System.out.println(pedidoLinea);
		
	}

}
