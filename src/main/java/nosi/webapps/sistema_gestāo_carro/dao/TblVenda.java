package nosi.webapps.sistema_gestāo_carro.dao;

import jakarta.validation.constraints.NotBlank;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.NamedQuery;
import jakarta.validation.constraints.Size;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.ForeignKey;
import java.io.Serial;

/**
 * @author: Nositeste 2026-02-26
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "tbl_venda", schema = "public")
@NamedQuery(name = "TblVenda.findAll", query = "SELECT t FROM TblVenda t")
public class TblVenda extends BaseActiveRecord<TblVenda> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_VENDA";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venda", nullable = false, updatable = false)
 	private Integer idVenda;
	@NotBlank
	@Size(max = 100)
	@Column(name = "vendedor")
	private String vendedor;
	@NotBlank
	@Size(max = 100)
	@Column(name = "comprador")
	private String comprador;
	@NotNull
	@Column(name = "data_venda")
	private LocalDate dataVenda;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_carro_fk", foreignKey = @ForeignKey(name = "carro_fkey"), referencedColumnName="id_carro")
	private TblCarro idCarroFk;

   public Integer getIdVenda() {
      return this.idVenda;
   }

   public void setIdVenda(Integer idVenda) {
      this.idVenda = idVenda;
   }

   public String getVendedor() {
      return this.vendedor;
   }

   public void setVendedor(String vendedor) {
      this.vendedor = vendedor;
   }

   public String getComprador() {
      return this.comprador;
   }

   public void setComprador(String comprador) {
      this.comprador = comprador;
   }

   public LocalDate getDataVenda() {
      return this.dataVenda;
   }

   public void setDataVenda(LocalDate dataVenda) {
      this.dataVenda = dataVenda;
   }

   public TblCarro getIdCarroFk() {
      return this.idCarroFk;
   }

   public void setIdCarroFk(TblCarro idCarroFk) {
      this.idCarroFk = idCarroFk;
   }

   public static final class Field {
      public static final String ID_VENDA = "idVenda";
      public static final String VENDEDOR = "vendedor";
      public static final String COMPRADOR = "comprador";
      public static final String DATA_VENDA = "dataVenda";
      public static final String ID_CARRO_FK = "idCarroFk";

	  private Field() {}
	}
}