package nosi.webapps.sistema_de_carros.pages.registro_de_carros_;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;
import nosi.core.webapp.Core;

public class Registro_de_carros_View extends View {

	public Field foto;
	public Field marca;
	public Field modelo;
	public Field data_registro;
	public Field preco;
	public Field manual_de_utilizacao;
	public Field mostrar_documento;
	public Field id_carro;
	public IGRPForm form_1;

	public IGRPButton btn_registro;

	public Registro_de_carros_View(){

		this.setPageTitle("Registro de Carros ");
			
		form_1 = new IGRPForm("form_1","Resgistro de Carros");

		foto = new TextField(model,"foto");
		foto.setLabel(gt("Foto"));
		foto.setValue(gt("../images/IGRP/IGRP2.3/assets/img/jon_doe.jpg"));
		foto.propertie().add("name","p_foto").add("type","img").add("img","../images/IGRP/IGRP2.3/assets/img/jon_doe.jpg").add("width","").add("height","").add("croppie","true").add("rounded","false").add("autoupload","true").add("maxlength","250").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false");
		
		marca = new ListField(model,"marca");
		marca.setLabel(gt("Marca"));
		marca.propertie().add("name","p_marca").add("type","select").add("multiple","false").add("tags","false").add("load_service_data","false").add("domain","").add("maxlength","250").add("required","true").add("disabled","false").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");
		
		modelo = new ListField(model,"modelo");
		modelo.setLabel(gt("Modelo"));
		modelo.propertie().add("name","p_modelo").add("type","select").add("multiple","false").add("tags","false").add("load_service_data","false").add("domain","").add("maxlength","250").add("required","true").add("disabled","false").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");
		
		data_registro = new DateField(model,"data_registro");
		data_registro.setLabel(gt("Data Registro"));
		data_registro.propertie().add("name","p_data_registro").add("type","date").add("range","false").add("disableWeekends","false").add("disabledBeforetoday","false").add("daysoff","false").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false").add("class","primary");
		
		preco = new NumberField(model,"preco");
		preco.setLabel(gt("Preço"));
		preco.propertie().add("name","p_preco").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");
		
		manual_de_utilizacao = new FileField(model,"manual_de_utilizacao");
		manual_de_utilizacao.setLabel(gt("Manual_de_utilizacao"));
		manual_de_utilizacao.propertie().add("name","p_manual_de_utilizacao").add("type","file").add("accept","").add("targetrend","").add("multiple","false").add("rendvalue","false").add("maxlength","250").add("required","false").add("disabled","false").add("tooltip","false").add("disable_copy_paste","false").add("class","primary");
		
		mostrar_documento = new LinkField(model,"mostrar_documento");
		mostrar_documento.setLabel(gt("Mostrar_documento"));
		mostrar_documento.setValue(Core.getIGRPLink("sistema_de_carros","Parametrizacao_marcas","index"));

									mostrar_documento.propertie().add("name","p_mostrar_documento").add("type","link").add("target","mpsubmit").add("request_fields","").add("refresh_components","").add("refresh_submit","false").add("adbcli","").add("class","link").add("img","fa-link").add("maxlength","250").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false");
		
		id_carro = new HiddenField(model,"id_carro");
		id_carro.setLabel(gt(""));
		id_carro.propertie().add("name","p_id_carro").add("type","hidden").add("maxlength","250").add("java-type","").add("tooltip","false").add("disable_copy_paste","false").add("tag","id_carro");
		


		btn_registro = new IGRPButton("Registro","sistema_de_carros","Registro_de_carros_","registro","submit_form","primary|fa-save","","");
		btn_registro.propertie.add("id","button_1e9f_7b4a").add("type","form").add("class","primary").add("rel","registro").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		
		form_1.addField(foto);
		form_1.addField(marca);
		form_1.addField(modelo);
		form_1.addField(data_registro);
		form_1.addField(preco);
		form_1.addField(manual_de_utilizacao);
		form_1.addField(mostrar_documento);
		form_1.addField(id_carro);

		form_1.addButton(btn_registro);
		this.addToPage(form_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		marca.setValue(model);
		modelo.setValue(model);
		data_registro.setValue(model);
		preco.setValue(model);
		manual_de_utilizacao.setValue(model);
		mostrar_documento.setValue(model);
		id_carro.setValue(model);	

		}
}