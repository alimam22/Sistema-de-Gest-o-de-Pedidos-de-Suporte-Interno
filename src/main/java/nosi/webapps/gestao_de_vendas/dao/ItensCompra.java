package nosi.webapps.gestao_de_vendas.dao;

import javax.persistence.Column;
import java.io.Serial;
import java.math.BigDecimal;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import jakarta.validation.constraints.NotNull;

/**
 * @author: Nositeste 2026-03-17
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "itens_compra", schema = "public")
@NamedQuery(name = "ItensCompra.findAll", query = "SELECT t FROM ItensCompra t")
public class ItensCompra extends BaseActiveRecord<ItensCompra> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "ITENS_COMPRA";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotNull
	@Column(name = "compra_id")
	private Integer compraId;
	@NotNull
	@Column(name = "produto_id")
	private Integer produtoId;
	@NotNull
	@Column(name = "quantidade")
	private Integer quantidade;
	@NotNull
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	@NotNull
	@Column(name = "subtotal")
	private BigDecimal subtotal;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getCompraId() {
      return this.compraId;
   }

   public void setCompraId(Integer compraId) {
      this.compraId = compraId;
   }

   public Integer getProdutoId() {
      return this.produtoId;
   }

   public void setProdutoId(Integer produtoId) {
      this.produtoId = produtoId;
   }

   public Integer getQuantidade() {
      return this.quantidade;
   }

   public void setQuantidade(Integer quantidade) {
      this.quantidade = quantidade;
   }

   public BigDecimal getPrecoUnitario() {
      return this.precoUnitario;
   }

   public void setPrecoUnitario(BigDecimal precoUnitario) {
      this.precoUnitario = precoUnitario;
   }

   public BigDecimal getSubtotal() {
      return this.subtotal;
   }

   public void setSubtotal(BigDecimal subtotal) {
      this.subtotal = subtotal;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String COMPRA_ID = "compraId";
      public static final String PRODUTO_ID = "produtoId";
      public static final String QUANTIDADE = "quantidade";
      public static final String PRECO_UNITARIO = "precoUnitario";
      public static final String SUBTOTAL = "subtotal";

	  private Field() {}
	}
}