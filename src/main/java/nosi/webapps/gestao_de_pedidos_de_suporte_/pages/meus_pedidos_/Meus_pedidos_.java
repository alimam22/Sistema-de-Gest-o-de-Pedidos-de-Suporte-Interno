package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.meus_pedidos_;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Meus_pedidos_ extends Model{		

	@RParam(rParamName = "p_id")
	private Integer p_id;

	@RParam(rParamName = "p_solicitante")
	private String solicitante;

	@RParam(rParamName = "p_estado_atual")
	private String estado_atual;

	@RParam(rParamName = "p_numero_de_pedido")
	private String numero_de_pedido;

	@RParam(rParamName = "p_assunto")
	private String assunto;

	@RParam(rParamName = "p_detalhe_de_pedido")
	private String detalhe_de_pedido;

	@RParam(rParamName = "p_parecerobervacao_tecnica")
	private String parecerobervacao_tecnica;

	@RParam(rParamName = "p_estado_combo")
	private String estado_combo;

	public void setEstado_combo(String estado_combo) {
		this.estado_combo = estado_combo;
	}
	public String getEstado_combo() {
		return this.estado_combo;
	}
	
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getP_id() {
		return this.p_id;
	}

	public void setSolicitante(String solicitante){
		this.solicitante = solicitante;
	}
	public String getSolicitante(){
		return this.solicitante;
	}
	
	public void setEstado_atual(String estado_atual){
		this.estado_atual = estado_atual;
	}
	public String getEstado_atual(){
		return this.estado_atual;
	}
	
	public void setNumero_de_pedido(String numero_de_pedido){
		this.numero_de_pedido = numero_de_pedido;
	}
	public String getNumero_de_pedido(){
		return this.numero_de_pedido;
	}
	
	public void setAssunto(String assunto){
		this.assunto = assunto;
	}
	public String getAssunto(){
		return this.assunto;
	}
	
	public void setDetalhe_de_pedido(String detalhe_de_pedido){
		this.detalhe_de_pedido = detalhe_de_pedido;
	}
	public String getDetalhe_de_pedido(){
		return this.detalhe_de_pedido;
	}
	
	public void setParecerobervacao_tecnica(String parecerobervacao_tecnica){
		this.parecerobervacao_tecnica = parecerobervacao_tecnica;
	}
	public String getParecerobervacao_tecnica(){
		return this.parecerobervacao_tecnica;
	}



}