package nosi.webapps.sistema_de_carros.pages.auxiliar_report;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Auxiliar_report extends Model{		

	@RParam(rParamName = "p_comprador")
	private String comprador;

	@RParam(rParamName = "p_data")
	private String data;

	@RParam(rParamName = "p_vendedor")
	private String vendedor;

	@RParam(rParamName = "p_marca")
	private String marca;
	
	public void setComprador(String comprador){
		this.comprador = comprador;
	}
	public String getComprador(){
		return this.comprador;
	}
	
	public void setData(String data){
		this.data = data;
	}
	public String getData(){
		return this.data;
	}
	
	public void setVendedor(String vendedor){
		this.vendedor = vendedor;
	}
	public String getVendedor(){
		return this.vendedor;
	}
	
	public void setMarca(String marca){
		this.marca = marca;
	}
	public String getMarca(){
		return this.marca;
	}



}