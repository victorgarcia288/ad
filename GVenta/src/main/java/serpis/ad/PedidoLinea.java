package serpis.ad;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class PedidoLinea {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="articulo")
	private Articulo articulo;
	
	private BigDecimal precio;
	private BigDecimal unidades;
	private BigDecimal importe;
	
	public void setUnidades(BigDecimal unidades) {
		this.unidades = unidades;
		
	}
	
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
		
	}
	
	@PrePersist
	private void prePersist() {
		importe = unidades.multiply(precio);
	}
	
	public BigDecimal getImporte() {
		return unidades.multiply(precio);
	}
	
//	package
    void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
		precio = articulo.getPrecio();
		unidades = new BigDecimal(1);
		importe = unidades.multiply(precio);
		
	}
	
	@Override
    public String toString(){
   
    	return String.format("[%s] (%s)", id, articulo.getNombre(), pedido);
    	
	}	

}
