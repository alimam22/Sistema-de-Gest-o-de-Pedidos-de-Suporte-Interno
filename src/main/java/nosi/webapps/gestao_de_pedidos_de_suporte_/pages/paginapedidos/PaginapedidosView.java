package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.paginapedidos;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class PaginapedidosView extends View {

	public Field button_1;
	public Field assunto;
	public Field departamento;
	public Field categoria;

	// ALTERADO: De TextField para ListField
	public Field propriedade;
	public Field estado;

	public Field detalhe_de_pedido;
	public Field p_id;

	// NOVO CAMPO
	public Field atribuir_tecnico;

	public IGRPPanel panels_1;
	public IGRPForm form_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_submeter;
	public IGRPButton btn_cancelar;

	public PaginapedidosView(){

		this.setPageTitle("PaginaPedidos");

		panels_1 = new IGRPPanel("panels_1","");
		form_1 = new IGRPForm("form_1","Detalhe de pedido");

		p_id = new HiddenField(model,"p_id");
		p_id.propertie().add("name","p_id").add("type","hidden").add("java-type","Integer");

		button_1 = new TextField(model,"button_1");
		button_1.setLabel(gt("Panel Item 1"));
		button_1.propertie().add("name","p_panels_1_button_1").add("type","button").add("img","fa-angle-right");

		assunto = new TextField(model,"assunto");
		assunto.setLabel(gt("Assunto"));
		assunto.propertie().add("name","p_assunto").add("type","text").add("required","true");

		departamento = new ListField(model,"departamento");
		departamento.setLabel(gt("Departamento"));
		departamento.propertie().add("name","p_departamento").add("type","select").add("required","true");

		categoria = new ListField(model,"categoria");
		categoria.setLabel(gt("Categoria"));
		categoria.propertie().add("name","p_categoria").add("type","select").add("required","true");

		// --- CORREÇÃO: PROPRIEDADE (NORMALMENTE PRIORIDADE NO DOCUMENTO) ---
		propriedade = new ListField(model,"propriedade");
		propriedade.setLabel(gt("Prioridade")); // Ajustado label conforme o doc
		propriedade.propertie().add("name","p_propriedade").add("type","select").add("required","true").add("multiple","false");

		// --- CORREÇÃO: ESTADO ---
		estado = new ListField(model,"estado");
		estado.setLabel(gt("Estado"));
		estado.propertie().add("name","p_estado").add("type","select").add("required","true").add("multiple","false");

		detalhe_de_pedido = new TextField(model,"detalhe_de_pedido");
		detalhe_de_pedido.setLabel(gt("Detalhe de pedido"));
		detalhe_de_pedido.propertie().add("name","p_detalhe_de_pedido").add("type","text").add("required","true");

		// NOVO CAMPO: Atribuir Técnico
		atribuir_tecnico = new ListField(model,"atribuir_tecnico");
		atribuir_tecnico.setLabel(gt("Atribuir técnico"));
		atribuir_tecnico.propertie().add("name","p_atribuir_tecnico").add("type","select").add("required","true").add("multiple","false");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_submeter = new IGRPButton("Submeter","gestao_de_pedidos_de_suporte_","Paginapedidos","submeter","submit","success|fa-check","","");
		btn_submeter.propertie.add("type","form").add("rel","submeter");

		btn_cancelar = new IGRPButton("Cancelar","gestao_de_pedidos_de_suporte_","Paginapedidos","cancelar","_self","grey|fa-times","","");
		btn_cancelar.propertie.add("type","form").add("rel","cancelar");
	}

	@Override
	public void render(){
		panels_1.addField(button_1);
		form_1.addField(p_id);
		form_1.addField(assunto);
		form_1.addField(departamento);
		form_1.addField(categoria);
		form_1.addField(propriedade);
		form_1.addField(estado);
		form_1.addField(detalhe_de_pedido);
		form_1.addField(atribuir_tecnico); // NOVO

		form_1.addButton(btn_submeter);
		form_1.addButton(btn_cancelar);

		this.addToPage(panels_1);
		this.addToPage(form_1);
		this.addToPage(toolsbar_1);
	}

	@Override
	public void setModel(Model model) {
		p_id.setValue(model);
		button_1.setValue(model);
		assunto.setValue(model);
		departamento.setValue(model);
		categoria.setValue(model);
		propriedade.setValue(model);
		estado.setValue(model);
		detalhe_de_pedido.setValue(model);
		atribuir_tecnico.setValue(model); // NOVO
	}
}