package nosi.webapps.sistema_de_carros.pages.vender_carro;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;
import nosi.core.webapp.Core;

public class Vender_carroView extends View {

	public Field marca;
	public Field modelo;
	public Field preco;
	public Field manual;
	public Field view_1_img;
	public Field comprador;
	public Field id_carro;
	public IGRPView view_1;
	public IGRPForm form_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_venda;

	public Vender_carroView(){

		this.setPageTitle("Vender Carro");
			
		view_1 = new IGRPView("view_1","Vender Carro");

		form_1 = new IGRPForm("form_1","");

		marca = new TextField(model,"marca");
		marca.setLabel(gt("Marca"));
		marca.propertie().add("name","p_marca").add("type","text").add("maxlength","250").add("class","default").add("img","").add("showlabel","true");
		
		modelo = new TextField(model,"modelo");
		modelo.setLabel(gt("Modelo"));
		modelo.propertie().add("name","p_modelo").add("type","text").add("maxlength","250").add("class","default").add("img","").add("showlabel","true");
		
		preco = new NumberField(model,"preco");
		preco.setLabel(gt("Preço"));
		preco.propertie().add("name","p_preco").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","250").add("class","default").add("img","").add("showlabel","true");
		
		manual = new LinkField(model,"manual");
		manual.setLabel(gt("Manual"));
		manual.setValue(Core.getIGRPLink("sistema_de_carros","Parametrizacao_marcas","index"));

									manual.propertie().add("name","p_manual").add("type","link").add("target","modal").add("request_fields","").add("refresh_components","").add("refresh_submit","false").add("adbcli","").add("class","[object Object]").add("img","[object Object]").add("maxlength","250").add("showlabel","true");
		
		view_1_img = new TextField(model,"view_1_img");
		view_1_img.setLabel(gt(""));
		view_1_img.propertie().add("type","text").add("name","p_view_1_img").add("maxlength","300");
		
		comprador = new TextField(model,"comprador");
		comprador.setLabel(gt("Comprador"));
		comprador.propertie().add("name","p_comprador").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		id_carro = new HiddenField(model,"id_carro");
		id_carro.setLabel(gt(""));
		id_carro.propertie().add("name","p_id_carro").add("type","hidden").add("maxlength","250").add("java-type","").add("tooltip","false").add("disable_copy_paste","false").add("tag","id_carro");
		

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_venda = new IGRPButton("Venda","sistema_de_carros","Vender_carro","venda","submit","info|fa-shopping-cart","","");
		btn_venda.propertie.add("type","specific").add("rel","venda").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		
		view_1.addField(marca);
		view_1.addField(modelo);
		view_1.addField(preco);
		view_1.addField(manual);
		view_1.addField(view_1_img);

		form_1.addField(comprador);
		form_1.addField(id_carro);


		toolsbar_1.addButton(btn_venda);
		this.addToPage(view_1);
		this.addToPage(form_1);
		this.addToPage(toolsbar_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		marca.setValue(model);
		modelo.setValue(model);
		preco.setValue(model);
		manual.setValue(model);
		view_1_img.setValue(model);
		comprador.setValue(model);
		id_carro.setValue(model);	

		}
}