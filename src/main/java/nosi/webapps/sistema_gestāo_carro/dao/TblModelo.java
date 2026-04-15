package nosi.webapps.sistema_gestāo_carro.dao;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
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
@Table(name = "tbl_modelo", schema = "public")
@NamedQuery(name = "TblModelo.findAll", query = "SELECT t FROM TblModelo t")
public class TblModelo extends BaseActiveRecord<TblModelo> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_MODELO";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modelo", nullable = false, updatable = false)
 	private Integer idModelo;
	@Size(max = 50)
	@Column(name = "nome_modelo")
	private String nomeModelo;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_marca_fk", foreignKey = @ForeignKey(name = "marca_fk"), referencedColumnName="id_marca")
	private TblMarca idMarcaFk;

   public Integer getIdModelo() {
      return this.idModelo;
   }

   public void setIdModelo(Integer idModelo) {
      this.idModelo = idModelo;
   }

   public String getNomeModelo() {
      return this.nomeModelo;
   }

   public void setNomeModelo(String nomeModelo) {
      this.nomeModelo = nomeModelo;
   }

   public TblMarca getIdMarcaFk() {
      return this.idMarcaFk;
   }

   public void setIdMarcaFk(TblMarca idMarcaFk) {
      this.idMarcaFk = idMarcaFk;
   }

   public static final class Field {
      public static final String ID_MODELO = "idModelo";
      public static final String NOME_MODELO = "nomeModelo";
      public static final String ID_MARCA_FK = "idMarcaFk";

	  private Field() {}
	}
}