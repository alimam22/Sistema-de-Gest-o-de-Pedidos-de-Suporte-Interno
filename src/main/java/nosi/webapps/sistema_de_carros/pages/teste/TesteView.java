package nosi.webapps.sistema_de_carros.pages.teste;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class TesteView extends View {

	public Field bola;
	public Field mota;
	public Field carro;
	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPButton btn_gravar;
	public IGRPButton btn_table_1_button_1;

	public TesteView(){

		this.setPageTitle("Teste");
			
		form_1 = new IGRPForm("form_1","");

		table_1 = new IGRPTable("table_1","");

		bola = new TextField(model,"bola");
		bola.setLabel(gt("Bola"));
		bola.propertie().add("name","p_bola").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		mota = new TextField(model,"mota");
		mota.setLabel(gt("Mota"));
		mota.propertie().add("name","p_mota").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		carro = new TextField(model,"carro");
		carro.setLabel(gt("Carro"));
		carro.propertie().add("name","p_carro").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		


		btn_gravar = new IGRPButton("Gravar","sistema_de_carros","Teste","gravar","_blank","primary|fa-angle-right","","");
		btn_gravar.propertie.add("id","button_6a58_6902").add("type","form").add("class","primary").add("rel","gravar").add("refresh_components","");

		btn_table_1_button_1 = new IGRPButton("Button","sistema_de_carros","Teste","table_1_button_1","_blank","primary|fa-angle-right","","");
		btn_table_1_button_1.propertie.add("id","button_f4d6_8f07").add("type","specific").add("class","primary").add("rel","table_1_button_1").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		
		form_1.addField(bola);

		table_1.addField(mota);
		table_1.addField(carro);

		form_1.addButton(btn_gravar);
		table_1.addButton(btn_table_1_button_1);
		this.addToPage(form_1);
		this.addToPage(table_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		bola.setValue(model);
		mota.setValue(model);
		carro.setValue(model);	

		table_1.loadModel(((Teste) model).getTable_1());
		}
}