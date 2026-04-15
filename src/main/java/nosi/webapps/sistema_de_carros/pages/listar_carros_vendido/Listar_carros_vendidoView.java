package nosi.webapps.sistema_de_carros.pages.listar_carros_vendido;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Listar_carros_vendidoView extends View {

	public Field marca;
	public Field modelo;
	public Field comprador;
	public Field id_venda;
	public Field paragraph_1_text;
	public IGRPTable table_1;
	public IGRPParagraph paragraph_1;

	public IGRPButton btn_emitir_relatorio;

	public Listar_carros_vendidoView(){

		this.setPageTitle("Listar Carros Vendido");
			
		table_1 = new IGRPTable("table_1","Lista de Carros Vendido");

		paragraph_1 = new IGRPParagraph("paragraph_1","");

		marca = new TextField(model,"marca");
		marca.setLabel(gt("Marca"));
		marca.propertie().add("name","p_marca").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		modelo = new TextField(model,"modelo");
		modelo.setLabel(gt("Modelo"));
		modelo.propertie().add("name","p_modelo").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		comprador = new TextField(model,"comprador");
		comprador.setLabel(gt("Comprador"));
		comprador.propertie().add("name","p_comprador").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");
		
		id_venda = new HiddenField(model,"id_venda");
		id_venda.setLabel(gt(""));
		id_venda.propertie().add("name","p_id_venda").add("type","hidden").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("tag","id_venda");
		
		paragraph_1_text = new TextField(model,"paragraph_1_text");
		paragraph_1_text.setLabel(gt(""));
		paragraph_1_text.setValue(gt("<p>Os carros vendido tem garantia de 3 anos</p>"));
		paragraph_1_text.propertie().add("type","text").add("name","p_paragraph_1_text").add("maxlength","4000");
		


		btn_emitir_relatorio = new IGRPButton("Emitir Relatorio","sistema_de_carros","Listar_carros_vendido","emitir_relatorio","_newtab","danger|fa-address-card","","");
		btn_emitir_relatorio.propertie.add("id","button_6354_296f").add("type","specific").add("class","danger").add("rel","emitir_relatorio").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		
		table_1.addField(marca);
		table_1.addField(modelo);
		table_1.addField(comprador);
		table_1.addField(id_venda);

		paragraph_1.addField(paragraph_1_text);

		table_1.addButton(btn_emitir_relatorio);
		this.addToPage(table_1);
		this.addToPage(paragraph_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		marca.setValue(model);
		modelo.setValue(model);
		comprador.setValue(model);
		id_venda.setValue(model);	

		table_1.loadModel(((Listar_carros_vendido) model).getTable_1());
		}
}