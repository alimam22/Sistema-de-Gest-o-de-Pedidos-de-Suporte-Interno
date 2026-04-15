package nosi.webapps.sistema_de_carros.pages.registro_de_carros_;

import nosi.core.gui.components.IGRPLink;
import nosi.core.webapp.Report;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.uploadfile.UploadFile;

import nosi.core.validator.constraints.*;

public class Registro_de_carros_ extends Model{		

	@RParam(rParamName = "p_foto")
	private String foto;
	@RParam(rParamName = "p_foto_uuid")
	private String foto_uuid;

	@RParam(rParamName = "p_marca")
	private String marca;

	@RParam(rParamName = "p_modelo")
	private String modelo;

	@NotNull()
	@RParam(rParamName = "p_data_registro")
	private String data_registro;

	@NotNull()
	@RParam(rParamName = "p_preco")
	private Integer preco;

	@RParam(rParamName = "p_manual_de_utilizacao")
	private UploadFile manual_de_utilizacao;

	@RParam(rParamName = "p_mostrar_documento")
	private IGRPLink mostrar_documento;
	@RParam(rParamName = "p_mostrar_documento_desc")
	private String mostrar_documento_desc;

	@RParam(rParamName = "p_id_carro")
	private String id_carro;
	
	public void setFoto(String foto){
		this.foto = foto;
	}
	public String getFoto(){
		return this.foto;
	}
	public void setFoto_uuid(String foto_uuid){
		this.foto_uuid = foto_uuid;
	}
	public String getFoto_uuid(){
		return this.foto_uuid;
	}
	
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
	
	public void setData_registro(String data_registro){
		this.data_registro = data_registro;
	}
	public String getData_registro(){
		return this.data_registro;
	}
	
	public void setPreco(Integer preco){
		this.preco = preco;
	}
	public Integer getPreco(){
		return this.preco;
	}
	
	public void setManual_de_utilizacao(UploadFile manual_de_utilizacao){
		this.manual_de_utilizacao = manual_de_utilizacao;
	}
	public UploadFile getManual_de_utilizacao(){
		return this.manual_de_utilizacao;
	}
	
	public IGRPLink setMostrar_documento(String app,String page,String action){
		this.mostrar_documento = new IGRPLink(app,page,action);
		return this.mostrar_documento;
	}
	public IGRPLink getMostrar_documento(){
		return this.mostrar_documento;
	}
	public void setMostrar_documento_desc(String mostrar_documento_desc){
		this.mostrar_documento_desc = mostrar_documento_desc;
	}
	public String getMostrar_documento_desc(){
		return this.mostrar_documento_desc;
	}
	public IGRPLink setMostrar_documento(String link){
		this.mostrar_documento = new IGRPLink(link);
		return this.mostrar_documento;
	}
	public IGRPLink setMostrar_documento(Report link){
		this.mostrar_documento = new IGRPLink(link);
		return this.mostrar_documento;
	}
	
	public void setId_carro(String id_carro){
		this.id_carro = id_carro;
	}
	public String getId_carro(){
		return this.id_carro;
	}



}