package nosi.webapps.sistema_gestāo_carro.dao;

import jakarta.validation.constraints.NotBlank;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.NamedQuery;
import java.io.Serial;

/**
 * @author: Nositeste 2026-02-26
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "tbl_marca", schema = "public")
@NamedQuery(name = "TblMarca.findAll", query = "SELECT t FROM TblMarca t")
public class TblMarca extends BaseActiveRecord<TblMarca> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_MARCA";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_marca", nullable = false, updatable = false)
 	private Integer idMarca;
	@NotBlank
	@Size(max = 50)
	@Column(name = "nome_marca")
	private String nomeMarca;

   public Integer getIdMarca() {
      return this.idMarca;
   }

   public void setIdMarca(Integer idMarca) {
      this.idMarca = idMarca;
   }

   public String getNomeMarca() {
      return this.nomeMarca;
   }

   public void setNomeMarca(String nomeMarca) {
      this.nomeMarca = nomeMarca;
   }

   public static final class Field {
      public static final String ID_MARCA = "idMarca";
      public static final String NOME_MARCA = "nomeMarca";

	  private Field() {}
	}
}