package nosi.webapps.sistema_de_carros.pages.auxiliar_report;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Auxiliar_reportView extends View {

	public Field comprador;
	public Field data;
	public Field vendedor;
	public Field marca;
	public IGRPForm form_1;


	public Auxiliar_reportView(){

		this.setPageTitle("Auxiliar Report");
			
		form_1 = new IGRPForm("form_1","");

		comprador = new TextField(model,"comprador");
		comprador.setLabel(gt("Comprador"));
		comprador.propertie().add("name","p_comprador").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		data = new TextField(model,"data");
		data.setLabel(gt("Data"));
		data.propertie().add("name","p_data").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		vendedor = new TextField(model,"vendedor");
		vendedor.setLabel(gt("Vendedor"));
		vendedor.propertie().add("name","p_vendedor").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		marca = new TextField(model,"marca");
		marca.setLabel(gt("Marca"));
		marca.propertie().add("name","p_marca").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		


		
	}
		
	@Override
	public void render(){
		
		form_1.addField(comprador);
		form_1.addField(data);
		form_1.addField(vendedor);
		form_1.addField(marca);

		this.addToPage(form_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		comprador.setValue(model);
		data.setValue(model);
		vendedor.setValue(model);
		marca.setValue(model);	

		}
}