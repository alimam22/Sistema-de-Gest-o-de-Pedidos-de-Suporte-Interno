package nosi.webapps.sistema_de_carros.pages.lista_acessorio;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Lista_acessorioView extends View {

	public Field procurar_marca;
	public Field email_de_contacto;
	public Field id_solicitacao;
	public Field acessorio;
	public Field preco;
	public Field quantidade;
	public IGRPForm form_1;
	public IGRPFormList formlist_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPToolsBar toolsbar_2;
	public IGRPButton btn_inserir_acessorio;
	public IGRPButton btn_solicitar;

	public Lista_acessorioView(){

		this.setPageTitle("Lista Acessorio");
			
		form_1 = new IGRPForm("form_1","");

		formlist_1 = new IGRPFormList("formlist_1","");

		procurar_marca = new ListField(model,"procurar_marca");
		procurar_marca.setLabel(gt("Procurar Marca"));
		procurar_marca.propertie().add("name","p_procurar_marca").add("type","select").add("multiple","false").add("tags","false").add("load_service_data","false").add("domain","").add("maxlength","250").add("required","false").add("disabled","false").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");
		
		email_de_contacto = new EmailField(model,"email_de_contacto");
		email_de_contacto.setLabel(gt("Email de Contacto"));
		email_de_contacto.propertie().add("name","p_email_de_contacto").add("type","email").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false");
		
		id_solicitacao = new HiddenField(model,"id_solicitacao");
		id_solicitacao.setLabel(gt(""));
		id_solicitacao.propertie().add("name","p_id_solicitacao").add("type","hidden").add("maxlength","250").add("java-type","").add("tooltip","false").add("disable_copy_paste","false").add("tag","id_solicitacao");
		
		acessorio = new PlainTextField(model,"acessorio");
		acessorio.setLabel(gt("Acessório"));
		acessorio.propertie().add("name","p_acessorio").add("type","plaintext").add("disable_output_escaping","false").add("html_class","").add("maxlength","250").add("desc","true");
		
		preco = new NumberField(model,"preco");
		preco.setLabel(gt("Preço"));
		preco.propertie().add("name","p_preco").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","250").add("required","false").add("readonly","true").add("disabled","false").add("placeholder",gt("")).add("java-type","").add("total_col","false").add("total_row","false").add("totalrow","false").add("desc","true");
		
		quantidade = new NumberField(model,"quantidade");
		quantidade.setLabel(gt("Quantidade"));
		quantidade.propertie().add("name","p_quantidade").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("java-type","").add("total_col","false").add("total_row","false").add("totalrow","false").add("desc","true");
		

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");
		toolsbar_2 = new IGRPToolsBar("toolsbar_2");

		btn_inserir_acessorio = new IGRPButton("Inserir Acessorio","sistema_de_carros","Lista_acessorio","inserir_acessorio","mpsubmit|refresh","info|fa-plus-square","","");
		btn_inserir_acessorio.propertie.add("type","specific").add("rel","inserir_acessorio").add("flg_transaction","true").add("refresh_components","");

		btn_solicitar = new IGRPButton("Solicitar","sistema_de_carros","Lista_acessorio","solicitar","submit","purple|fa-bus","","");
		btn_solicitar.propertie.add("type","specific").add("rel","solicitar").add("flg_transaction","true").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		

		form_1.addField(procurar_marca);
		form_1.addField(email_de_contacto);
		form_1.addField(id_solicitacao);

		formlist_1.addField(acessorio);
		formlist_1.addField(preco);
		formlist_1.addField(quantidade);


		toolsbar_1.addButton(btn_inserir_acessorio);
		toolsbar_2.addButton(btn_solicitar);
		this.addToPage(form_1);
		this.addToPage(formlist_1);
		this.addToPage(toolsbar_1);
		this.addToPage(toolsbar_2);
	}
		
	@Override
	public void setModel(Model model) {
		
		procurar_marca.setValue(model);
		email_de_contacto.setValue(model);
		id_solicitacao.setValue(model);
		acessorio.setValue(model);
		preco.setValue(model);
		quantidade.setValue(model);	

		formlist_1.loadModel(((Lista_acessorio) model).getFormlist_1());
		}
}