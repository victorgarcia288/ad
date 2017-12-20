package serpis.ad;

import java.math.BigDecimal;

public class Articulo {
	private long id;
	private String nombre;
	private BigDecimal precio;
	private long categoria;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}
	
	public long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	
	public long getCategoria() {
		return categoria;
	}
	
	
	

}
