package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.paginapedidos;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Paginapedidos extends Model {

	@RParam(rParamName = "p_id")
	private Integer p_id;

	@RParam(rParamName = "p_panels_1_button_1")
	private String button_1;

	@RParam(rParamName = "p_assunto")
	private String assunto;

	@RParam(rParamName = "p_departamento")
	private String departamento;

	@RParam(rParamName = "p_categoria")
	private String categoria;

	@RParam(rParamName = "p_propriedade")
	private String propriedade;

	@RParam(rParamName = "p_estado")
	private String estado;

	// NOVO CAMPO ADICIONADO PARA RESOLVER O ERRO
	@RParam(rParamName = "p_atribuir_tecnico")
	private Integer tecnico_atribuido;

	@RParam(rParamName = "p_detalhe_de_pedido")
	private String detalhe_de_pedido;

	@RParam(rParamName = "p_numero_de_pedido")
	private String numero_de_pedido;

	// --- GETTERS E SETTERS ---

	public void setP_id(Integer p_id) { this.p_id = p_id; }
	public Integer getP_id() { return this.p_id; }

	public void setButton_1(String button_1) { this.button_1 = button_1; }
	public String getButton_1() { return this.button_1; }

	public void setAssunto(String assunto) { this.assunto = assunto; }
	public String getAssunto() { return this.assunto; }

	public void setDepartamento(String departamento) { this.departamento = departamento; }
	public String getDepartamento() { return this.departamento; }

	public void setCategoria(String categoria) { this.categoria = categoria; }
	public String getCategoria() { return this.categoria; }

	public void setPropriedade(String propriedade) { this.propriedade = propriedade; }
	public String getPropriedade() { return this.propriedade; }

	public void setEstado(String estado) { this.estado = estado; }
	public String getEstado() { return this.estado; }

	// MÉTODOS QUE ESTAVAM FALTANDO:
	public void setTecnico_atribuido(Integer tecnico_atribuido) {
		this.tecnico_atribuido = tecnico_atribuido;
	}
	public Integer getTecnico_atribuido() {
		return this.tecnico_atribuido;
	}

	public void setDetalhe_de_pedido(String detalhe_de_pedido) { this.detalhe_de_pedido = detalhe_de_pedido; }
	public String getDetalhe_de_pedido() { return this.detalhe_de_pedido; }

	public void setNumero_de_pedido(String numero_de_pedido) { this.numero_de_pedido = numero_de_pedido; }
	public String getNumero_de_pedido() { return this.numero_de_pedido; }
}