package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.meus_pedidos_;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;
import java.util.Arrays;
import java.util.List;

public class Meus_pedidos_View extends View {

	public Field solicitante;
	public Field estado_atual;
	public Field numero_de_pedido;
	public Field assunto;
	public Field detalhe_de_pedido;
	public Field parecerobervacao_tecnica;
	public IGRPForm form_1;

	// Combo de estado para permitir mudar o estado
	public ListField estado_combo;

	public IGRPToolsBar toolsbar_1;

	// Lista de estados possíveis para o combo
	private List<String> estadosPermitidos = Arrays.asList(
			"ATRIBUIDO", "EM_ANALISE", "EM_EXECUCAO", "CONCLUIDO", "REJEITADO", "CANCELADO", "REABERTO"
	);

	public Field p_id;

	public IGRPButton btn_aceitar;
	public IGRPButton btn_resolver;
	public IGRPButton btn_rejeitar;
	public IGRPButton btn_cancelar;

	// FIX 1: btn_atualizar_estado declarado como campo da classe (não dentro do render)
	public IGRPButton btn_atualizar_estado;

	public Meus_pedidos_View() {

		this.setPageTitle("Meus Pedidos tecnicos");

		form_1 = new IGRPForm("form_1", "Informação de pedidos");

		p_id = new HiddenField(model, "p_id");
		p_id.propertie().add("name", "p_id").add("type", "hidden").add("java-type", "Integer");

		// FIX 2: Campos de visualização passam a readonly=true e disabled=true
		solicitante = new TextField(model, "solicitante");
		solicitante.setLabel(gt("Solicitante"));
		solicitante.propertie()
				.add("name", "p_solicitante")
				.add("type", "text")
				.add("maxlength", "250")
				.add("required", "false")
				.add("readonly", "true")
				.add("disabled", "true")
				.add("disablehtml", "true")
				.add("placeholder", gt(""))
				.add("desclabel", "false")
				.add("inputmask", "")
				.add("tooltip", "false")
				.add("disable_copy_paste", "false");

		estado_atual = new TextField(model, "estado_atual");
		estado_atual.setLabel(gt("Estado atual"));
		estado_atual.propertie()
				.add("name", "p_estado_atual")
				.add("type", "text")
				.add("maxlength", "250")
				.add("required", "false")
				.add("readonly", "true")
				.add("disabled", "true")
				.add("disablehtml", "true")
				.add("placeholder", gt(""))
				.add("desclabel", "false")
				.add("inputmask", "")
				.add("tooltip", "false")
				.add("disable_copy_paste", "false");

		numero_de_pedido = new TextField(model, "numero_de_pedido");
		numero_de_pedido.setLabel(gt("Numero de pedido"));
		numero_de_pedido.propertie()
				.add("name", "p_numero_de_pedido")
				.add("type", "text")
				.add("maxlength", "250")
				.add("required", "false")
				.add("readonly", "true")
				.add("disabled", "true")
				.add("disablehtml", "true")
				.add("placeholder", gt(""))
				.add("desclabel", "false")
				.add("inputmask", "")
				.add("tooltip", "false")
				.add("disable_copy_paste", "false");

		assunto = new TextField(model, "assunto");
		assunto.setLabel(gt("Assunto"));
		assunto.propertie()
				.add("name", "p_assunto")
				.add("type", "text")
				.add("maxlength", "250")
				.add("required", "false")
				.add("readonly", "true")
				.add("disabled", "true")
				.add("disablehtml", "true")
				.add("placeholder", gt(""))
				.add("desclabel", "false")
				.add("inputmask", "")
				.add("tooltip", "false")
				.add("disable_copy_paste", "false");

		detalhe_de_pedido = new TextField(model, "detalhe_de_pedido");
		detalhe_de_pedido.setLabel(gt("Detalhe de pedido"));
		detalhe_de_pedido.propertie()
				.add("name", "p_detalhe_de_pedido")
				.add("type", "text")
				.add("maxlength", "250")
				.add("required", "false")
				.add("readonly", "true")
				.add("disabled", "true")
				.add("disablehtml", "true")
				.add("placeholder", gt(""))
				.add("desclabel", "false")
				.add("inputmask", "")
				.add("tooltip", "false")
				.add("disable_copy_paste", "false");

		// Combo de estado para permitir mudar o estado
		estado_combo = new ListField(model, "estado_combo");
		estado_combo.setLabel(gt("Novo Estado"));
		estado_combo.propertie()
				.add("name", "p_estado_combo")
				.add("type", "select")
				.add("multiple", "false")
				.add("java-type", "String");
		// Adiciona as opções de estado
		for (String estado : estadosPermitidos) {
			estado_combo.propertie().add("option", estado + "|" + estado);
		}

		parecerobervacao_tecnica = new TextAreaField(model, "parecerobervacao_tecnica");
		parecerobervacao_tecnica.setLabel(gt("Parecer/Obervação técnica"));
		parecerobervacao_tecnica.propertie()
				.add("name", "p_parecerobervacao_tecnica")
				.add("type", "textarea")
				.add("maxlength", "250")
				.add("required", "false")
				.add("readonly", "false")
				.add("disabled", "false")
				.add("disablehtml", "true")
				.add("placeholder", gt(""))
				.add("desclabel", "false")
				.add("tooltip", "false")
				.add("disable_copy_paste", "false");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_aceitar = new IGRPButton("Aceitar", "gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "aceitar", "submit", "info|fa-angle-right", "", "");
		btn_aceitar.propertie.add("id", "button_6bd0_edea").add("type", "form").add("class", "info").add("rel", "aceitar").add("refresh_components", "");

		btn_resolver = new IGRPButton("Resolver", "gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "resolver", "submit", "success|fa-angle-right", "", "");
		btn_resolver.propertie.add("id", "button_182b_70ba").add("type", "form").add("class", "success").add("rel", "resolver").add("refresh_components", "");

		btn_rejeitar = new IGRPButton("Rejeitar", "gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "rejeitar", "submit", "danger|fa-angle-right", "", "");
		btn_rejeitar.propertie.add("id", "button_3f70_16a4").add("type", "form").add("class", "danger").add("rel", "rejeitar").add("refresh_components", "");

		btn_cancelar = new IGRPButton("Cancelar", "gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "cancelar", "submit", "grey|fa-angle-right", "", "");
		btn_cancelar.propertie.add("id", "button_81b6_8849").add("type", "form").add("class", "grey").add("rel", "cancelar").add("refresh_components", "");

		// FIX 1: Inicializado no construtor (declarado como campo da classe acima)
		btn_atualizar_estado = new IGRPButton("Atualizar Estado", "gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "atualizar_estado", "submit", "primary|fa-save", "", "");
		btn_atualizar_estado.propertie.add("id", "button_atualizar_estado").add("type", "form").add("class", "primary").add("rel", "atualizar_estado").add("refresh_components", "");
	}

	@Override
	public void render() {

		form_1.addField(p_id);
		form_1.addField(solicitante);
		form_1.addField(estado_atual);
		form_1.addField(numero_de_pedido);
		form_1.addField(assunto);
		form_1.addField(detalhe_de_pedido);
		form_1.addField(parecerobervacao_tecnica);
		form_1.addField(estado_combo);

		form_1.addButton(btn_aceitar);
		form_1.addButton(btn_resolver);
		form_1.addButton(btn_rejeitar);
		form_1.addButton(btn_cancelar);
		form_1.addButton(btn_atualizar_estado);

		this.addToPage(form_1);
		this.addToPage(toolsbar_1);
	}

	@Override
	public void setModel(Model model) {

		p_id.setValue(model);
		solicitante.setValue(model);
		estado_atual.setValue(model);
		numero_de_pedido.setValue(model);
		assunto.setValue(model);
		detalhe_de_pedido.setValue(model);
		parecerobervacao_tecnica.setValue(model);

		// FIX 3: estado_combo incluído no setModel para carregar valor do modelo
		estado_combo.setValue(model);
	}
}