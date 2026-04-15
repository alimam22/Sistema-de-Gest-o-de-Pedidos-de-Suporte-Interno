package nosi.webapps.gestao_de_pedidos_de_suporte_.dao;

import java.io.Serial;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import jakarta.validation.constraints.Size;
import javax.persistence.NamedQuery;
import javax.persistence.GenerationType;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author: Nositeste 2026-03-31
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "pedido_suporte", schema = "public")
@NamedQuery(name = "PedidoSuporte.findAll", query = "SELECT t FROM PedidoSuporte t")
public class PedidoSuporte extends BaseActiveRecord<PedidoSuporte> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "PEDIDO_SUPORTE";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotBlank
	@Size(max = 20)
	@Column(name = "numero_pedido")
	private String numeroPedido;
	@NotBlank
	@Size(max = 200)
	@Column(name = "titulo")
	private String titulo;
	@NotBlank
	@Column(name = "descricao", columnDefinition = "text")
	private String descricao;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "categoria_id", foreignKey = @ForeignKey(name = "fk_pedido_categoria"))
	private CategoriaSuporte categoriaId;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "departamento_id", foreignKey = @ForeignKey(name = "fk_pedido_depto"))
	private Departamento departamentoId;
	@NotBlank
	@Size(max = 20)
	@Column(name = "prioridade")
	private String prioridade;
	@NotBlank
	@Size(max = 30)
	@Column(name = "estado")
	private String estado;
	@NotNull
	@Column(name = "solicitante_id")
	private Integer solicitanteId;
	@Column(name = "tecnico_id")
	private Integer tecnicoId;
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	@Column(name = "data_atribuicao")
	private LocalDateTime dataAtribuicao;
	@Column(name = "data_resolucao")
	private LocalDateTime dataResolucao;
	@Size(max = 500)
	@Column(name = "observacao_resolucao")
	private String observacaoResolucao;
	@Column(name = "data_ultima_actualizacao")
	private LocalDateTime dataUltimaActualizacao;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNumeroPedido() {
      return this.numeroPedido;
   }

   public void setNumeroPedido(String numeroPedido) {
      this.numeroPedido = numeroPedido;
   }

   public String getTitulo() {
      return this.titulo;
   }

   public void setTitulo(String titulo) {
      this.titulo = titulo;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public CategoriaSuporte getCategoriaId() {
      return this.categoriaId;
   }

   public void setCategoriaId(CategoriaSuporte categoriaId) {
      this.categoriaId = categoriaId;
   }

   public Departamento getDepartamentoId() {
      return this.departamentoId;
   }

   public void setDepartamentoId(Departamento departamentoId) {
      this.departamentoId = departamentoId;
   }

   public String getPrioridade() {
      return this.prioridade;
   }

   public void setPrioridade(String prioridade) {
      this.prioridade = prioridade;
   }

   public String getEstado() {
      return this.estado;
   }

   public void setEstado(String estado) {
      this.estado = estado;
   }

   public Integer getSolicitanteId() {
      return this.solicitanteId;
   }

   public void setSolicitanteId(Integer solicitanteId) {
      this.solicitanteId = solicitanteId;
   }

   public Integer getTecnicoId() {
      return this.tecnicoId;
   }

   public void setTecnicoId(Integer tecnicoId) {
      this.tecnicoId = tecnicoId;
   }

   public LocalDateTime getDataCriacao() {
      return this.dataCriacao;
   }

   public void setDataCriacao(LocalDateTime dataCriacao) {
      this.dataCriacao = dataCriacao;
   }

   public LocalDateTime getDataAtribuicao() {
      return this.dataAtribuicao;
   }

   public void setDataAtribuicao(LocalDateTime dataAtribuicao) {
      this.dataAtribuicao = dataAtribuicao;
   }

   public LocalDateTime getDataResolucao() {
      return this.dataResolucao;
   }

   public void setDataResolucao(LocalDateTime dataResolucao) {
      this.dataResolucao = dataResolucao;
   }

   public String getObservacaoResolucao() {
      return this.observacaoResolucao;
   }

   public void setObservacaoResolucao(String observacaoResolucao) {
      this.observacaoResolucao = observacaoResolucao;
   }

   public LocalDateTime getDataUltimaActualizacao() {
      return this.dataUltimaActualizacao;
   }

   public void setDataUltimaActualizacao(LocalDateTime dataUltimaActualizacao) {
      this.dataUltimaActualizacao = dataUltimaActualizacao;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String NUMERO_PEDIDO = "numeroPedido";
      public static final String TITULO = "titulo";
      public static final String DESCRICAO = "descricao";
      public static final String CATEGORIA_ID = "categoriaId";
      public static final String DEPARTAMENTO_ID = "departamentoId";
      public static final String PRIORIDADE = "prioridade";
      public static final String ESTADO = "estado";
      public static final String SOLICITANTE_ID = "solicitanteId";
      public static final String TECNICO_ID = "tecnicoId";
      public static final String DATA_CRIACAO = "dataCriacao";
      public static final String DATA_ATRIBUICAO = "dataAtribuicao";
      public static final String DATA_RESOLUCAO = "dataResolucao";
      public static final String OBSERVACAO_RESOLUCAO = "observacaoResolucao";
      public static final String DATA_ULTIMA_ACTUALIZACAO = "dataUltimaActualizacao";

	  private Field() {}
	}
}