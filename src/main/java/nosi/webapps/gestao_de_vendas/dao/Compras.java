package nosi.webapps.gestao_de_vendas.dao;

import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;
import javax.persistence.Id;
import javax.persistence.Column;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Entity;
import java.io.Serial;
import jakarta.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * @author: Nositeste 2026-03-17
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "compras", schema = "public")
@NamedQuery(name = "Compras.findAll", query = "SELECT t FROM Compras t")
public class Compras extends BaseActiveRecord<Compras> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "COMPRAS";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "fornecedor_id", foreignKey = @ForeignKey(name = "fornecidor_id"))
	private Fornecedores fornecedorId;
	@NotNull
	@Column(name = "total")
	private BigDecimal total;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Fornecedores getFornecedorId() {
      return this.fornecedorId;
   }

   public void setFornecedorId(Fornecedores fornecedorId) {
      this.fornecedorId = fornecedorId;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   public void setTotal(BigDecimal total) {
      this.total = total;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String FORNECEDOR_ID = "fornecedorId";
      public static final String TOTAL = "total";

	  private Field() {}
	}
}