package nosi.webapps.sistema_de_carros.pages.parametrizacao_marcas;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Parametrizacao_marcasView extends View {

	public Field marca;
	public Field id_marca;
	public Field marca_tbl;
	public Field id_marca_tbl;
	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPButton btn_salvar;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Parametrizacao_marcasView(){

		this.setPageTitle("Parametrizacão Marcas");
			
		form_1 = new IGRPForm("form_1","Resgistro de Marcas");

		table_1 = new IGRPTable("table_1","");

		marca = new TextField(model,"marca");
		marca.setLabel(gt("Marca"));
		marca.propertie().add("name","p_marca").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		id_marca = new HiddenField(model,"id_marca");
		id_marca.setLabel(gt(""));
		id_marca.propertie().add("name","p_id_marca").add("type","hidden").add("maxlength","250").add("java-type","").add("tooltip","false").add("disable_copy_paste","false").add("tag","id_marca");
		
		marca_tbl = new TextField(model,"marca_tbl");
		marca_tbl.setLabel(gt("Marca"));
		marca_tbl.propertie().add("name","p_marca_tbl").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		id_marca_tbl = new HiddenField(model,"id_marca_tbl");
		id_marca_tbl.setLabel(gt(""));
		id_marca_tbl.propertie().add("name","p_id_marca_tbl").add("type","hidden").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("tag","id_marca_tbl");
		


		btn_salvar = new IGRPButton("Salvar","sistema_de_carros","Parametrizacao_marcas","salvar","submit_form","primary|fa-save","","");
		btn_salvar.propertie.add("id","button_1e9f_7b4a").add("type","form").add("class","primary").add("rel","salvar").add("refresh_components","");

		btn_editar = new IGRPButton("Editar","sistema_de_carros","Parametrizacao_marcas","editar","mpsubmit|refresh","warning|fa-edit","","");
		btn_editar.propertie.add("id","button_5049_3820").add("type","specific").add("class","warning").add("rel","editar").add("refresh_components","");

		btn_eliminar = new IGRPButton("Eliminar","sistema_de_carros","Parametrizacao_marcas","eliminar","confirm","danger|fa-trash","","");
		btn_eliminar.propertie.add("id","button_4cd9_9ca7").add("type","specific").add("class","danger").add("rel","eliminar").add("refresh_components","").add("labelConfirm","Deseja realmente realizar esta operação?");

		
	}
		
	@Override
	public void render(){
		
		form_1.addField(marca);
		form_1.addField(id_marca);

		table_1.addField(marca_tbl);
		table_1.addField(id_marca_tbl);

		form_1.addButton(btn_salvar);
		table_1.addButton(btn_editar);
		table_1.addButton(btn_eliminar);
		this.addToPage(form_1);
		this.addToPage(table_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		marca.setValue(model);
		id_marca.setValue(model);
		marca_tbl.setValue(model);
		id_marca_tbl.setValue(model);	

		table_1.loadModel(((Parametrizacao_marcas) model).getTable_1());
		}
}