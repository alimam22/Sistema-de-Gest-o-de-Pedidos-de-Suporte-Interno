package nosi.webapps.sistema_gestāo_carro.pages.parametrizacao_marca;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Parametrizacao_marcaView extends View {

	public Field marca;
	public Field id_marca;
	public Field marca_tbl_1;
	public Field id_marca_tbl;
	public IGRPForm form_1;
	public IGRPTable table_2;

	public IGRPButton btn_salvar;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Parametrizacao_marcaView(){

		this.setPageTitle("Parametrização Marca");
			
		form_1 = new IGRPForm("form_1","Registro de Marcas");

		table_2 = new IGRPTable("table_2","");

		marca = new TextField(model,"marca");
		marca.setLabel(gt("Marca"));
		marca.propertie().add("name","p_marca").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		id_marca = new HiddenField(model,"id_marca");
		id_marca.setLabel(gt(""));
		id_marca.propertie().add("name","p_id_marca").add("type","hidden").add("maxlength","250").add("java-type","").add("tooltip","false").add("disable_copy_paste","false").add("tag","id_marca");
		
		marca_tbl_1 = new TextField(model,"marca_tbl_1");
		marca_tbl_1.setLabel(gt("Marca"));
		marca_tbl_1.propertie().add("name","p_marca_tbl").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		id_marca_tbl = new HiddenField(model,"id_marca_tbl");
		id_marca_tbl.setLabel(gt(""));
		id_marca_tbl.propertie().add("name","p_id_marca_tbl").add("type","hidden").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("tag","id_marca_tbl");
		


		btn_salvar = new IGRPButton("Salvar","sistema_gestāo_carro","Parametrizacao_marca","salvar","submit_form","primary|fa-save","","");
		btn_salvar.propertie.add("id","button_8c21_3d96").add("type","form").add("class","primary").add("rel","salvar").add("refresh_components","");

		btn_editar = new IGRPButton("Editar","sistema_gestāo_carro","Parametrizacao_marca","editar","mpsubmit","warning|fa-edit","","");
		btn_editar.propertie.add("id","button_f801_3ed0").add("type","specific").add("class","warning").add("rel","editar").add("refresh_components","");

		btn_eliminar = new IGRPButton("Eliminar","sistema_gestāo_carro","Parametrizacao_marca","eliminar","confirm","danger|fa-trash","","");
		btn_eliminar.propertie.add("id","button_352d_c1ad").add("type","specific").add("class","danger").add("rel","eliminar").add("refresh_components","").add("labelConfirm","Deseja realmente realizar esta operação?");

		
	}
		
	@Override
	public void render(){
		
		form_1.addField(marca);
		form_1.addField(id_marca);

		table_2.addField(marca_tbl_1);
		table_2.addField(id_marca_tbl);

		form_1.addButton(btn_salvar);
		table_2.addButton(btn_editar);
		table_2.addButton(btn_eliminar);
		this.addToPage(form_1);
		this.addToPage(table_2);
	}
		
	@Override
	public void setModel(Model model) {
		
		marca.setValue(model);
		id_marca.setValue(model);
		marca_tbl_1.setValue(model);
		id_marca_tbl.setValue(model);	

		table_2.loadModel(((Parametrizacao_marca) model).getTable_2());
		}
}