package nosi.webapps.sistema_de_carros.pages.vender_carro;

import nosi.core.gui.components.IGRPLink;
import nosi.core.webapp.Report;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Vender_carro extends Model{		

	@RParam(rParamName = "p_marca")
	private String marca;

	@RParam(rParamName = "p_modelo")
	private String modelo;

	@RParam(rParamName = "p_preco")
	private Integer preco;

	@RParam(rParamName = "p_manual")
	private IGRPLink manual;
	@RParam(rParamName = "p_manual_desc")
	private String manual_desc;

	@RParam(rParamName = "p_view_1_img")
	private String view_1_img;

	@RParam(rParamName = "p_comprador")
	private String comprador;

	@RParam(rParamName = "p_id_carro")
	private String id_carro;
	
	public void setMarca(String marca){
		this.marca = marca;
	}
	public String getMarca(){
		return this.marca;
	}
	
	public void setModelo(String modelo){
		this.modelo = modelo;
	}
	public String getModelo(){
		return this.modelo;
	}
	
	public void setPreco(Integer preco){
		this.preco = preco;
	}
	public Integer getPreco(){
		return this.preco;
	}
	
	public IGRPLink setManual(String app,String page,String action){
		this.manual = new IGRPLink(app,page,action);
		return this.manual;
	}
	public IGRPLink getManual(){
		return this.manual;
	}
	public void setManual_desc(String manual_desc){
		this.manual_desc = manual_desc;
	}
	public String getManual_desc(){
		return this.manual_desc;
	}
	public IGRPLink setManual(String link){
		this.manual = new IGRPLink(link);
		return this.manual;
	}
	public IGRPLink setManual(Report link){
		this.manual = new IGRPLink(link);
		return this.manual;
	}
	
	public void setView_1_img(String view_1_img){
		this.view_1_img = view_1_img;
	}
	public String getView_1_img(){
		return this.view_1_img;
	}
	
	public void setComprador(String comprador){
		this.comprador = comprador;
	}
	public String getComprador(){
		return this.comprador;
	}
	
	public void setId_carro(String id_carro){
		this.id_carro = id_carro;
	}
	public String getId_carro(){
		return this.id_carro;
	}



}