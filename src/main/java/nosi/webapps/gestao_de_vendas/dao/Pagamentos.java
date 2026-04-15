package nosi.webapps.gestao_de_vendas.dao;

import java.io.Serial;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.math.BigDecimal;
import jakarta.validation.constraints.Size;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import javax.persistence.Entity;
import jakarta.validation.constraints.NotNull;

/**
 * @author: Nositeste 2026-03-17
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "pagamentos", schema = "public")
@NamedQuery(name = "Pagamentos.findAll", query = "SELECT t FROM Pagamentos t")
public class Pagamentos extends BaseActiveRecord<Pagamentos> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "PAGAMENTOS";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotNull
	@Column(name = "venda_id")
	private Integer vendaId;
	@NotBlank
	@Size(max = 100)
	@Column(name = "metodo", columnDefinition = "bpchar")
	private String metodo;
	@NotNull
	@Column(name = "valor")
	private BigDecimal valor;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getVendaId() {
      return this.vendaId;
   }

   public void setVendaId(Integer vendaId) {
      this.vendaId = vendaId;
   }

   public String getMetodo() {
      return this.metodo;
   }

   public void setMetodo(String metodo) {
      this.metodo = metodo;
   }

   public BigDecimal getValor() {
      return this.valor;
   }

   public void setValor(BigDecimal valor) {
      this.valor = valor;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String VENDA_ID = "vendaId";
      public static final String METODO = "metodo";
      public static final String VALOR = "valor";

	  private Field() {}
	}
}