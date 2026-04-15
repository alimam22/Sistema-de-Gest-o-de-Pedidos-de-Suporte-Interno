package nosi.webapps.sistema_de_carros.pages.carro_lookup_;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Carro_lookup_View extends View {

	public Field marca;
	public Field modelo;
	public Field data_registro;
	public Field id_carro;
	public IGRPTable table_1;


	public Carro_lookup_View(){

		this.setPageTitle("Carro Lookup ");
			
		table_1 = new IGRPTable("table_1","");

		marca = new TextField(model,"marca");
		marca.setLabel(gt("Marca"));
		marca.propertie().add("name","p_marca").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		modelo = new TextField(model,"modelo");
		modelo.setLabel(gt("Modelo"));
		modelo.propertie().add("name","p_modelo").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		data_registro = new TextField(model,"data_registro");
		data_registro.setLabel(gt("Data Registro"));
		data_registro.propertie().add("name","p_data_registro").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		id_carro = new HiddenField(model,"id_carro");
		id_carro.setLabel(gt(""));
		id_carro.propertie().add("name","p_id_carro").add("type","hidden").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("tag","id_carro");
		


		
	}
		
	@Override
	public void render(){
		
		table_1.addField(marca);
		table_1.addField(modelo);
		table_1.addField(data_registro);
		table_1.addField(id_carro);

		this.addToPage(table_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		marca.setValue(model);
		modelo.setValue(model);
		data_registro.setValue(model);
		id_carro.setValue(model);	

		table_1.loadModel(((Carro_lookup_) model).getTable_1());
		}
}