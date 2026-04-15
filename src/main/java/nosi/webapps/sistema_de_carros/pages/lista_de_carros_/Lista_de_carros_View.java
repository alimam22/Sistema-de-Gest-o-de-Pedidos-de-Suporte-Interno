package nosi.webapps.sistema_de_carros.pages.lista_de_carros_;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;
import nosi.core.webapp.Core;

public class Lista_de_carros_View extends View {

	public Field sectionheader_1_text;
	public Field preco;
	public Field intervalo_de_data;
	public Field foto;
	public Field marca_tbl;
	public Field preco_tbl;
	public Field modelo_tbl;
	public Field manual_tbl;
	public Field manual_tbl_desc;
	public Field data_resgistro_tbl;
	public Field id_carro_tbl;
	public IGRPSectionHeader sectionheader_1;
	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_novo;
	public IGRPButton btn_pesquisar;
	public IGRPButton btn_vender_carro;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Lista_de_carros_View(){

		this.setPageTitle("Lista de Carros ");
			
		sectionheader_1 = new IGRPSectionHeader("sectionheader_1","");

		form_1 = new IGRPForm("form_1","");

		table_1 = new IGRPTable("table_1","");

		sectionheader_1_text = new TextField(model,"sectionheader_1_text");
		sectionheader_1_text.setLabel(gt(""));
		sectionheader_1_text.setValue(gt("<p>Gest&atilde;o de Carro</p>"));
		sectionheader_1_text.propertie().add("type","text").add("name","p_sectionheader_1_text").add("maxlength","4000");
		
		preco = new TextField(model,"preco");
		preco.setLabel(gt("Preço"));
		preco.propertie().add("name","p_preco").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		intervalo_de_data = new DateField(model,"intervalo_de_data");
		intervalo_de_data.setLabel(gt("Intervalo de Data"));
		intervalo_de_data.propertie().add("name","p_intervalo_de_data").add("type","date").add("range","true").add("disableWeekends","false").add("disabledBeforetoday","false").add("daysoff","false").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("tooltip","false").add("disable_copy_paste","false").add("class","primary");
		
		foto = new TextField(model,"foto");
		foto.setLabel(gt("Foto"));
		foto.setValue(gt(""));
		foto.propertie().add("name","p_foto").add("type","img").add("img","../images/IGRP/IGRP2.3/assets/img/jon_doe.jpg").add("width","").add("height","").add("croppie","false").add("rounded","false").add("autoupload","false").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		marca_tbl = new TextField(model,"marca_tbl");
		marca_tbl.setLabel(gt("Marca"));
		marca_tbl.propertie().add("name","p_marca_tbl").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		preco_tbl = new NumberField(model,"preco_tbl");
		preco_tbl.setLabel(gt("Preco"));
		preco_tbl.propertie().add("name","p_preco_tbl").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","30").add("showLabel","true").add("total_footer","false").add("group_in","").add("java-type","");
		
		modelo_tbl = new TextField(model,"modelo_tbl");
		modelo_tbl.setLabel(gt("Modelo"));
		modelo_tbl.propertie().add("name","p_modelo_tbl").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		manual_tbl = new LinkField(model,"manual_tbl");
		manual_tbl.setLabel(gt("Manual_tbl"));
		manual_tbl.setValue(Core.getIGRPLink("sistema_de_carros","Parametrizacao_marcas","index"));

									manual_tbl_desc = new LinkField(model,"manual_tbl_desc");
		manual_tbl_desc.setLabel(gt("Manual_tbl"));
		manual_tbl.propertie().add("name","p_manual_tbl").add("type","link").add("target","_self").add("request_fields","").add("list_source","").add("refresh_components","").add("refresh_submit","false").add("adbcli","").add("class","link").add("img","fa-link").add("maxlength","30").add("showLabel","true").add("group_in","").add("show_header","true").add("desc","true");
		
		data_resgistro_tbl = new DateField(model,"data_resgistro_tbl");
		data_resgistro_tbl.setLabel(gt("Data_resgistro"));
		data_resgistro_tbl.propertie().add("name","p_data_resgistro_tbl").add("type","date").add("range","false").add("disableWeekends","false").add("disabledBeforetoday","false").add("daysoff","false").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		id_carro_tbl = new HiddenField(model,"id_carro_tbl");
		id_carro_tbl.setLabel(gt(""));
		id_carro_tbl.propertie().add("name","p_id_carro_tbl").add("type","hidden").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("tag","id_carro_tbl");
		

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_novo = new IGRPButton("Novo","sistema_de_carros","Lista_de_carros_","novo","modal|refresh","success|fa-plus-circle","","");
		btn_novo.propertie.add("type","specific").add("rel","novo").add("refresh_components","");

		btn_pesquisar = new IGRPButton("Pesquisar","sistema_de_carros","Lista_de_carros_","pesquisar","submit_ajax","primary|fa-search","","");
		btn_pesquisar.propertie.add("id","button_b85c_7f3d").add("type","form").add("class","primary").add("rel","pesquisar").add("refresh_components","table_1");

		btn_vender_carro = new IGRPButton("Vender Carro","sistema_de_carros","Lista_de_carros_","vender_carro","mpsubmit|refresh","grey|fa-shopping-cart","","");
		btn_vender_carro.propertie.add("id","button_3e3b_2eed").add("type","specific").add("class","grey").add("rel","vender_carro").add("refresh_components","");

		btn_editar = new IGRPButton("Editar","sistema_de_carros","Lista_de_carros_","editar","mpsubmit|refresh","warning|fa-edit","","");
		btn_editar.propertie.add("id","button_8bb8_96c1").add("type","specific").add("class","warning").add("rel","editar").add("refresh_components","");

		btn_eliminar = new IGRPButton("Eliminar","sistema_de_carros","Lista_de_carros_","eliminar","confirm","danger|fa-trash","","");
		btn_eliminar.propertie.add("id","button_4499_2110").add("type","specific").add("class","danger").add("rel","eliminar").add("refresh_components","").add("labelConfirm","Deseja realmente realizar esta operação?");

		
	}
		
	@Override
	public void render(){
		
		sectionheader_1.addField(sectionheader_1_text);


		form_1.addField(preco);
		form_1.addField(intervalo_de_data);

		table_1.addField(foto);
		table_1.addField(marca_tbl);
		table_1.addField(preco_tbl);
		table_1.addField(modelo_tbl);
		table_1.addField(manual_tbl);
		table_1.addField(manual_tbl_desc);
		table_1.addField(data_resgistro_tbl);
		table_1.addField(id_carro_tbl);

		toolsbar_1.addButton(btn_novo);
		form_1.addButton(btn_pesquisar);
		table_1.addButton(btn_vender_carro);
		table_1.addButton(btn_editar);
		table_1.addButton(btn_eliminar);
		this.addToPage(sectionheader_1);
		this.addToPage(form_1);
		this.addToPage(table_1);
		this.addToPage(toolsbar_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		preco.setValue(model);
		intervalo_de_data.setValue(model);
		marca_tbl.setValue(model);
		preco_tbl.setValue(model);
		modelo_tbl.setValue(model);
		manual_tbl.setValue(model);
		manual_tbl_desc.setValue(model);
		data_resgistro_tbl.setValue(model);
		id_carro_tbl.setValue(model);	

		table_1.loadModel(((Lista_de_carros_) model).getTable_1());
		}
}