package serpis.ad;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	public void setId(long id) {
		this.id = id;
	}
	
	private Cliente cliente;
	private Calendar fecha = Calendar.getInstance();
	private BigDecimal importe;
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<PedidoLinea> pedidoLineas = new ArrayList<>();
	
//	public List<PedidoLinea> getPedidoLineas() {
//		return pedidoLineas;
//	}
	
	public PedidoLinea[] getPedidoLineas() {
		return pedidoLineas.toArray(new PedidoLinea[0]);
	}
		
	public BigDecimal getImporte() {
		importe = BigDecimal.ZERO;
		for(PedidoLinea pedidoLinea : pedidoLineas)
			importe.add(pedidoLinea.getImporte());
		return importe;
	}
	
	public void add(PedidoLinea pedidoLinea) {
		pedidoLineas.add(pedidoLinea);
		pedidoLinea.setPedido(this);
	}
	
	public void remove(PedidoLinea pedidoLinea) {
		pedidoLineas.remove(pedidoLinea);
		pedidoLinea.setPedido(null);
	}
	
	@PrePersist
	private void prePersist() {
		getImporte();
	}
	
	
	@Override
	public String toString() {
		
		return String.format("[%s] %s %s %s", id, cliente.getNombre(), fecha.getTime(), importe);
	}

}
