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
@Table(name = "tbl_solicitacao", schema = "public")
@NamedQuery(name = "TblSolicitacao.findAll", query = "SELECT t FROM TblSolicitacao t")
public class TblSolicitacao extends BaseActiveRecord<TblSolicitacao> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_SOLICITACAO";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitacao", nullable = false, updatable = false)
 	private Integer idSolicitacao;
	@NotNull
	@Column(name = "quantidade")
	private Integer quantidade;
	@NotNull
	@Column(name = "preco_final")
	private Integer precoFinal;
	@NotBlank
	@Size(max = 100)
	@Column(name = "email")
	private String email;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_acessorio_fk", foreignKey = @ForeignKey(name = "acessorio_fk"), referencedColumnName="id_acessorio")
	private TblAcessorio idAcessorioFk;

   public Integer getIdSolicitacao() {
      return this.idSolicitacao;
   }

   public void setIdSolicitacao(Integer idSolicitacao) {
      this.idSolicitacao = idSolicitacao;
   }

   public Integer getQuantidade() {
      return this.quantidade;
   }

   public void setQuantidade(Integer quantidade) {
      this.quantidade = quantidade;
   }

   public Integer getPrecoFinal() {
      return this.precoFinal;
   }

   public void setPrecoFinal(Integer precoFinal) {
      this.precoFinal = precoFinal;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public TblAcessorio getIdAcessorioFk() {
      return this.idAcessorioFk;
   }

   public void setIdAcessorioFk(TblAcessorio idAcessorioFk) {
      this.idAcessorioFk = idAcessorioFk;
   }

   public static final class Field {
      public static final String ID_SOLICITACAO = "idSolicitacao";
      public static final String QUANTIDADE = "quantidade";
      public static final String PRECO_FINAL = "precoFinal";
      public static final String EMAIL = "email";
      public static final String ID_ACESSORIO_FK = "idAcessorioFk";

	  private Field() {}
	}
}