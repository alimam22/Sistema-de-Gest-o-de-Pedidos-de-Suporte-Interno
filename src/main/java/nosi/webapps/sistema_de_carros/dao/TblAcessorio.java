package nosi.webapps.sistema_de_carros.dao;

import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import javax.persistence.CascadeType;
import nosi.base.ActiveRecord.BaseActiveRecord;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlTransient;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serial;
import javax.persistence.NamedQuery;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.List;

/**
 * @author: Nositeste 2026-02-27
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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarroFk")
	private List<TblAcessorio> tblacessorioList;

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

	@XmlTransient
	public List<TblAcessorio> gettblacessorioList() {
		return tblacessorioList;
	}
	public void settblacessorioList(List<TblAcessorio> tblacessorioList) {
		this.tblacessorioList = tblacessorioList;
	}

   public static final class Field {
      public static final String ID_ACESSORIO = "idAcessorio";
      public static final String NOME_ACESSORIO = "nomeAcessorio";
      public static final String PRECO = "preco";
      public static final String ID_CARRO_FK = "idCarroFk";

	  private Field() {}
	}
}