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
@Table(name = "tbl_carro", schema = "public")
@NamedQuery(name = "TblCarro.findAll", query = "SELECT t FROM TblCarro t")
public class TblCarro extends BaseActiveRecord<TblCarro> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_CARRO";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_carro", nullable = false, updatable = false)
 	private Integer idCarro;
	@NotNull
	@Column(name = "data_registro")
	private LocalDate dataRegistro;
	@NotBlank
	@Size(max = 50)
	@Column(name = "id_manual")
	private String idManual;
	@NotNull
	@Column(name = "preco")
	private Integer preco;
	@NotNull
	@Column(name = "vendido")
	private Boolean vendido;
	@NotBlank
	@Size(max = 50)
	@Column(name = "id_foto")
	private String idFoto;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_modelo_fk", foreignKey = @ForeignKey(name = "modelo_fk"), referencedColumnName="id_modelo")
	private TblModelo idModeloFk;

   public Integer getIdCarro() {
      return this.idCarro;
   }

   public void setIdCarro(Integer idCarro) {
      this.idCarro = idCarro;
   }

   public LocalDate getDataRegistro() {
      return this.dataRegistro;
   }

   public void setDataRegistro(LocalDate dataRegistro) {
      this.dataRegistro = dataRegistro;
   }

   public String getIdManual() {
      return this.idManual;
   }

   public void setIdManual(String idManual) {
      this.idManual = idManual;
   }

   public Integer getPreco() {
      return this.preco;
   }

   public void setPreco(Integer preco) {
      this.preco = preco;
   }

   public Boolean getVendido() {
      return this.vendido;
   }

   public void setVendido(Boolean vendido) {
      this.vendido = vendido;
   }

   public String getIdFoto() {
      return this.idFoto;
   }

   public void setIdFoto(String idFoto) {
      this.idFoto = idFoto;
   }

   public TblModelo getIdModeloFk() {
      return this.idModeloFk;
   }

   public void setIdModeloFk(TblModelo idModeloFk) {
      this.idModeloFk = idModeloFk;
   }

   public static final class Field {
      public static final String ID_CARRO = "idCarro";
      public static final String DATA_REGISTRO = "dataRegistro";
      public static final String ID_MANUAL = "idManual";
      public static final String PRECO = "preco";
      public static final String VENDIDO = "vendido";
      public static final String ID_FOTO = "idFoto";
      public static final String ID_MODELO_FK = "idModeloFk";

	  private Field() {}
	}
}