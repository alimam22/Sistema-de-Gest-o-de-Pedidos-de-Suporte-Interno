package nosi.webapps.sistema_de_carros.pages.insserir_acessorio;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import nosi.core.gui.components.IGRPSeparatorList.Pair;
import nosi.core.webapp.SeparatorList;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
public class Insserir_acessorio extends Model{		

	@RParam(rParamName = "p_marca_do_carro")
	private String marca_do_carro;

	@RParam(rParamName = "p_modelo")
	private String modelo;

	@RParam(rParamName = "p_data_registro")
	private String data_registro;

	@RParam(rParamName = "p_id_carro")
	private String id_carro;
	
	@SeparatorList(name = Separatorlist_1.class)
	@Valid
	private List<Separatorlist_1> separatorlist_1 = new ArrayList<>();	
	public void setSeparatorlist_1(List<Separatorlist_1> separatorlist_1){
		this.separatorlist_1 = separatorlist_1;
	}
	public List<Separatorlist_1> getSeparatorlist_1(){
		return this.separatorlist_1;
	}
	@RParam(rParamName = "p_separatorlist_1_id")
	private String[] p_separatorlist_1_id;
	@RParam(rParamName = "p_separatorlist_1_del")
	private String[] p_separatorlist_1_del;
	@RParam(rParamName = "p_separatorlist_1_edit")
	private String[] p_separatorlist_1_edit;
	
	public void setP_separatorlist_1_id(String[] p_separatorlist_1_id){
		this.p_separatorlist_1_id = p_separatorlist_1_id;
	}
	public String[] getP_separatorlist_1_id(){
		return this.p_separatorlist_1_id;
	}
	
	public void setP_separatorlist_1_del(String[] p_separatorlist_1_del){
		this.p_separatorlist_1_del = p_separatorlist_1_del;
	}
	public String[] getP_separatorlist_1_del(){
		return this.p_separatorlist_1_del;
	}
	
	public void setP_separatorlist_1_edit(String[] p_separatorlist_1_edit){
		this.p_separatorlist_1_edit = p_separatorlist_1_edit;
	}
	public String[] getP_separatorlist_1_edit(){
		return this.p_separatorlist_1_edit;
	}
	
	public void setMarca_do_carro(String marca_do_carro){
		this.marca_do_carro = marca_do_carro;
	}
	public String getMarca_do_carro(){
		return this.marca_do_carro;
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
	
	public void setId_carro(String id_carro){
		this.id_carro = id_carro;
	}
	public String getId_carro(){
		return this.id_carro;
	}


	public static class Separatorlist_1{
		private Pair separatorlist_1_id;
private Pair nome_acessorio;
private Pair preco;
		public void setSeparatorlist_1_id(Pair separatorlist_1_id){
			this.separatorlist_1_id = separatorlist_1_id;
		}
		public void setSeparatorlist_1_id(String key){
			this.setSeparatorlist_1_id(key, key);
		}
		public void setSeparatorlist_1_id(String key, String value){
			this.separatorlist_1_id= new Pair(key, value);
		}

		public Pair getSeparatorlist_1_id(){
			return this.separatorlist_1_id;
		}
		public void setNome_acessorio(Pair nome_acessorio){
			this.nome_acessorio = nome_acessorio;
		}
		public void setNome_acessorio(String key){
			this.setNome_acessorio(key, key);
		}
		public void setNome_acessorio(String key, String value){
			this.nome_acessorio= new Pair(key, value);
		}

		public Pair getNome_acessorio(){
			return this.nome_acessorio;
		}

		public void setPreco(Pair preco){
			this.preco = preco;
		}
		public void setPreco(String key){
			this.setPreco(key, key);
		}
		public void setPreco(String key, String value){
			this.preco= new Pair(key, value);
		}

		public Pair getPreco(){
			return this.preco;
		}

	}

	public void loadSeparatorlist_1(BaseQueryInterface query) {
		this.setSeparatorlist_1(this.loadFormList(query,Separatorlist_1.class));
	}

}