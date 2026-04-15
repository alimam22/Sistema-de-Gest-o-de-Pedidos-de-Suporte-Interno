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
@Table(name = "pedido_historico", schema = "public")
@NamedQuery(name = "PedidoHistorico.findAll", query = "SELECT t FROM PedidoHistorico t")
public class PedidoHistorico extends BaseActiveRecord<PedidoHistorico> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "PEDIDO_HISTORICO";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "fk_historico_pedido"))
	private PedidoSuporte pedidoId;
	@Size(max = 30)
	@Column(name = "estado_anterior")
	private String estadoAnterior;
	@NotBlank
	@Size(max = 30)
	@Column(name = "estado_novo")
	private String estadoNovo;
	@NotNull
	@Column(name = "utilizador_id")
	private Integer utilizadorId;
	@Column(name = "data_transicao")
	private LocalDateTime dataTransicao;
	@Size(max = 500)
	@Column(name = "observacao")
	private String observacao;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public PedidoSuporte getPedidoId() {
      return this.pedidoId;
   }

   public void setPedidoId(PedidoSuporte pedidoId) {
      this.pedidoId = pedidoId;
   }

   public String getEstadoAnterior() {
      return this.estadoAnterior;
   }

   public void setEstadoAnterior(String estadoAnterior) {
      this.estadoAnterior = estadoAnterior;
   }

   public String getEstadoNovo() {
      return this.estadoNovo;
   }

   public void setEstadoNovo(String estadoNovo) {
      this.estadoNovo = estadoNovo;
   }

   public Integer getUtilizadorId() {
      return this.utilizadorId;
   }

   public void setUtilizadorId(Integer utilizadorId) {
      this.utilizadorId = utilizadorId;
   }

   public LocalDateTime getDataTransicao() {
      return this.dataTransicao;
   }

   public void setDataTransicao(LocalDateTime dataTransicao) {
      this.dataTransicao = dataTransicao;
   }

   public String getObservacao() {
      return this.observacao;
   }

   public void setObservacao(String observacao) {
      this.observacao = observacao;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String PEDIDO_ID = "pedidoId";
      public static final String ESTADO_ANTERIOR = "estadoAnterior";
      public static final String ESTADO_NOVO = "estadoNovo";
      public static final String UTILIZADOR_ID = "utilizadorId";
      public static final String DATA_TRANSICAO = "dataTransicao";
      public static final String OBSERVACAO = "observacao";

	  private Field() {}
	}
}