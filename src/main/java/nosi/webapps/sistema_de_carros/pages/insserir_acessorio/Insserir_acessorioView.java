package nosi.webapps.sistema_de_carros.pages.insserir_acessorio;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Insserir_acessorioView extends View {

	public Field marca_do_carro;
	public Field modelo;
	public Field data_registro;
	public Field id_carro;
	public Field nome_acessorio;
	public Field preco;
	public IGRPForm form_1;
	public IGRPSeparatorList separatorlist_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_salvar;

	public Insserir_acessorioView(){

		this.setPageTitle("Insserir Acessorio");
			
		form_1 = new IGRPForm("form_1","");

		separatorlist_1 = new IGRPSeparatorList("separatorlist_1","");

		marca_do_carro = new LookupField(model,"marca_do_carro");
		marca_do_carro.setLabel(gt("Marca do Carro"));
		marca_do_carro.setLookup("sistema_de_carros","Carro_lookup_","index");
		marca_do_carro.addParam("target","_blank");
		marca_do_carro.addLookupParam("p_marca_do_carro","marca");
		marca_do_carro.addLookupParam("p_modelo","modelo");
		marca_do_carro.addLookupParam("p_data_registro","data_registro");
		marca_do_carro.addLookupParam("p_id_carro","id_carro");
		marca_do_carro.propertie().add("name","p_marca_do_carro").add("type","lookup").add("field_param","").add("lookup_eraser","true").add("lookup_type","LOOKUP").add("class","primary").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false");
		
		modelo = new TextField(model,"modelo");
		modelo.setLabel(gt("Modelo"));
		modelo.propertie().add("name","p_modelo").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		data_registro = new DateField(model,"data_registro");
		data_registro.setLabel(gt("Data Registro"));
		data_registro.propertie().add("name","p_data_registro").add("type","date").add("range","false").add("disableWeekends","false").add("disabledBeforetoday","false").add("daysoff","false").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false").add("class","primary");
		
		id_carro = new HiddenField(model,"id_carro");
		id_carro.setLabel(gt(""));
		id_carro.propertie().add("name","p_id_carro").add("type","hidden").add("maxlength","250").add("java-type","").add("tooltip","false").add("disable_copy_paste","false").add("tag","id_carro");
		
		nome_acessorio = new TextField(model,"nome_acessorio");
		nome_acessorio.setLabel(gt("Nome Acessorio"));
		nome_acessorio.propertie().add("name","p_nome_acessorio").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("desc","true");
		
		preco = new NumberField(model,"preco");
		preco.setLabel(gt("Preco"));
		preco.propertie().add("name","p_preco").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("java-type","").add("desc","true");
		

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_salvar = new IGRPButton("Salvar","sistema_de_carros","Insserir_acessorio","salvar","submit_form","success|fa-angle-right","","");
		btn_salvar.propertie.add("type","specific").add("rel","salvar").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		
		form_1.addField(marca_do_carro);
		form_1.addField(modelo);
		form_1.addField(data_registro);
		form_1.addField(id_carro);

		separatorlist_1.addField(nome_acessorio);
		separatorlist_1.addField(preco);


		toolsbar_1.addButton(btn_salvar);
		this.addToPage(form_1);
		this.addToPage(separatorlist_1);
		this.addToPage(toolsbar_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		marca_do_carro.setValue(model);
		modelo.setValue(model);
		data_registro.setValue(model);
		id_carro.setValue(model);
		nome_acessorio.setValue(model);
		preco.setValue(model);	

		separatorlist_1.loadModel(((Insserir_acessorio) model).getSeparatorlist_1());
		}
}