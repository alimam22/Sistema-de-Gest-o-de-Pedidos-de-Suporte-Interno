package nosi.webapps.sistema_gestāo_carro.dao;

import jakarta.validation.constraints.NotBlank;
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
@Table(name = "tbl_acessorio", schema = "public")
@NamedQuery(name = "TblAcessorio.findAll", query = "SELECT t FROM TblAcessorio t")
public class TblAcessorio extends BaseActiveRecord<TblAcessorio> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_ACESSORIO";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_acessorio", nullable = false, updatable = false)
 	private Integer idAcessorio;
	@NotBlank
	@Size(max = 100)
	@Column(name = "nome_acessorio")
	private String nomeAcessorio;
	@NotNull
	@Column(name = "preco")
	private Integer preco;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_carro_fk", foreignKey = @ForeignKey(name = "carro_fk"), referencedColumnName="id_carro")
	private TblCarro idCarroFk;

   public Integer getIdAcessorio() {
      return this.idAcessorio;
   }

   public void setIdAcessorio(Integer idAcessorio) {
      this.idAcessorio = idAcessorio;
   }

   public String getNomeAcessorio() {
      return this.nomeAcessorio;
   }

   public void setNomeAcessorio(String nomeAcessorio) {
      this.nomeAcessorio = nomeAcessorio;
   }

   public Integer getPreco() {
      return this.preco;
   }

   public void setPreco(Integer preco) {
      this.preco = preco;
   }

   public TblCarro getIdCarroFk() {
      return this.idCarroFk;
   }

   public void setIdCarroFk(TblCarro idCarroFk) {
      this.idCarroFk = idCarroFk;
   }

   public static final class Field {
      public static final String ID_ACESSORIO = "idAcessorio";
      public static final String NOME_ACESSORIO = "nomeAcessorio";
      public static final String PRECO = "preco";
      public static final String ID_CARRO_FK = "idCarroFk";

	  private Field() {}
	}
}