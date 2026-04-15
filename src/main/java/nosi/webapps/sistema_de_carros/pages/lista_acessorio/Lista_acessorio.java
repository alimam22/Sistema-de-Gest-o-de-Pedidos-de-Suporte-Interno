package nosi.webapps.sistema_de_carros.pages.lista_acessorio;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import nosi.core.gui.components.IGRPSeparatorList.Pair;
import nosi.core.webapp.SeparatorList;
import java.util.ArrayList;
import java.util.List;
import jakarta.validation.Valid;
import nosi.core.validator.constraints.*;

public class Lista_acessorio extends Model{		

	@RParam(rParamName = "p_procurar_marca")
	private String procurar_marca;

	@NotNull()
	@RParam(rParamName = "p_email_de_contacto")
	private String email_de_contacto;

	@RParam(rParamName = "p_id_solicitacao")
	private String id_solicitacao;
	
	@SeparatorList(name = Formlist_1.class)
	@Valid
	private List<Formlist_1> formlist_1 = new ArrayList<>();	
	public void setFormlist_1(List<Formlist_1> formlist_1){
		this.formlist_1 = formlist_1;
	}
	public List<Formlist_1> getFormlist_1(){
		return this.formlist_1;
	}
	@RParam(rParamName = "p_formlist_1_id")
	private String[] p_formlist_1_id;
	@RParam(rParamName = "p_formlist_1_del")
	private String[] p_formlist_1_del;
	@RParam(rParamName = "p_formlist_1_edit")
	private String[] p_formlist_1_edit;
	
	public void setP_formlist_1_id(String[] p_formlist_1_id){
		this.p_formlist_1_id = p_formlist_1_id;
	}
	public String[] getP_formlist_1_id(){
		return this.p_formlist_1_id;
	}
	
	public void setP_formlist_1_del(String[] p_formlist_1_del){
		this.p_formlist_1_del = p_formlist_1_del;
	}
	public String[] getP_formlist_1_del(){
		return this.p_formlist_1_del;
	}
	
	public void setP_formlist_1_edit(String[] p_formlist_1_edit){
		this.p_formlist_1_edit = p_formlist_1_edit;
	}
	public String[] getP_formlist_1_edit(){
		return this.p_formlist_1_edit;
	}
	
	public void setProcurar_marca(String procurar_marca){
		this.procurar_marca = procurar_marca;
	}
	public String getProcurar_marca(){
		return this.procurar_marca;
	}
	
	public void setEmail_de_contacto(String email_de_contacto){
		this.email_de_contacto = email_de_contacto;
	}
	public String getEmail_de_contacto(){
		return this.email_de_contacto;
	}
	
	public void setId_solicitacao(String id_solicitacao){
		this.id_solicitacao = id_solicitacao;
	}
	public String getId_solicitacao(){
		return this.id_solicitacao;
	}


	public static class Formlist_1{
		private Pair formlist_1_id;
		@PairNotNull()
private Pair acessorio;
private Pair preco;
private Pair quantidade;
		public void setFormlist_1_id(Pair formlist_1_id){
			this.formlist_1_id = formlist_1_id;
		}
		public void setFormlist_1_id(String key){
			this.setFormlist_1_id(key, key);
		}
		public void setFormlist_1_id(String key, String value){
			this.formlist_1_id= new Pair(key, value);
		}

		public Pair getFormlist_1_id(){
			return this.formlist_1_id;
		}
		public void setAcessorio(Pair acessorio){
			this.acessorio = acessorio;
		}
		public void setAcessorio(String key){
			this.setAcessorio(key, key);
		}
		public void setAcessorio(String key, String value){
			this.acessorio= new Pair(key, value);
		}

		public Pair getAcessorio(){
			return this.acessorio;
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

		public void setQuantidade(Pair quantidade){
			this.quantidade = quantidade;
		}
		public void setQuantidade(String key){
			this.setQuantidade(key, key);
		}
		public void setQuantidade(String key, String value){
			this.quantidade= new Pair(key, value);
		}

		public Pair getQuantidade(){
			return this.quantidade;
		}

	}

	public void loadFormlist_1(BaseQueryInterface query) {
		this.setFormlist_1(this.loadFormList(query,Formlist_1.class));
	}

}