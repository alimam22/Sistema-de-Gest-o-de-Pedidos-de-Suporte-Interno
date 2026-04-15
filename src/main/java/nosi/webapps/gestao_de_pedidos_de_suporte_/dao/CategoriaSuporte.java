package nosi.webapps.gestao_de_pedidos_de_suporte_.dao;

import jakarta.validation.constraints.Size;
import java.io.Serial;
import javax.persistence.NamedQuery;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ForeignKey;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import nosi.base.ActiveRecord.BaseActiveRecord;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;

/**
 * @author: Nositeste 2026-03-31
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "categoria_suporte", schema = "public")
@NamedQuery(name = "CategoriaSuporte.findAll", query = "SELECT t FROM CategoriaSuporte t")
public class CategoriaSuporte extends BaseActiveRecord<CategoriaSuporte> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "CATEGORIA_SUPORTE";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotBlank
	@Size(max = 100)
	@Column(name = "nome")
	private String nome;
	@Size(max = 500)
	@Column(name = "descricao")
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "departamento_id", foreignKey = @ForeignKey(name = "fk_categoria_departamento"))
	private Departamento departamentoId;
	@Column(name = "activo")
	private Short activo;
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public Departamento getDepartamentoId() {
      return this.departamentoId;
   }

   public void setDepartamentoId(Departamento departamentoId) {
      this.departamentoId = departamentoId;
   }

   public Short getActivo() {
      return this.activo;
   }

   public void setActivo(Short activo) {
      this.activo = activo;
   }

   public LocalDateTime getDataCriacao() {
      return this.dataCriacao;
   }

   public void setDataCriacao(LocalDateTime dataCriacao) {
      this.dataCriacao = dataCriacao;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String NOME = "nome";
      public static final String DESCRICAO = "descricao";
      public static final String DEPARTAMENTO_ID = "departamentoId";
      public static final String ACTIVO = "activo";
      public static final String DATA_CRIACAO = "dataCriacao";

	  private Field() {}
	}
}