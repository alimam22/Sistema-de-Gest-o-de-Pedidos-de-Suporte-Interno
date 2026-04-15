package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.detalheshistorico;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class DetalheshistoricoView extends View {

	public Field n_do_pedido;
	public Field data_de_abertura;
	public Field estado;
	public Field departamento;
	public Field categoria;
	public Field form_1_hidden_1;
	public Field datahora;
	public Field tecnico;
	public Field estado_anterior;
	public Field novo_estado;
	public Field observacao;
	public Field tbi_historico_id; // Adicionado para evitar erro de mapping
	public Field nova_interacao__resolucao;
	public Field adicionar_comentario;
	public Field historico_de_comentario;
	public Field tbl_comentario_id;

	public IGRPForm form_1;
	public IGRPTable table_1;
	public IGRPForm form_2;
	public IGRPForm form_3;
	public IGRPTable table_2;

	public IGRPButton btn_assumir_pedido;
	public IGRPButton btn_finalizar_pedido;
	public IGRPButton btn_publicar_comentario;
	public IGRPButton btn_eliminar;

	public DetalheshistoricoView(){

		this.setPageTitle("Gestão e Acompanhamento de Pedido");

		form_1 = new IGRPForm("form_1", "Resumo do Chamado");
		table_1 = new IGRPTable("table_1", "Linha do Tempo de Atendimento");
		form_2 = new IGRPForm("form_2", "Finalização do Atendimento");
		form_3 = new IGRPForm("form_3", "Comunicação");
		table_2 = new IGRPTable("table_2", "Histórico de Conversas");

		// --- CAMPOS FORM 1 ---
		n_do_pedido = new TextField(model, "n_do_pedido");
		n_do_pedido.setLabel(gt("Nº do Pedido"));
		n_do_pedido.propertie().add("type", "text").add("readonly", "true").add("rel", "col-3");

		data_de_abertura = new TextField(model, "data_de_abertura");
		data_de_abertura.setLabel(gt("Data da Solicitação"));
		data_de_abertura.propertie().add("type", "text").add("readonly", "true").add("rel", "col-3");

		estado = new TextField(model, "estado");
		estado.setLabel(gt("Estado Atual"));
		estado.propertie().add("type", "text").add("readonly", "true").add("rel", "col-3").add("class", "primary");

		departamento = new TextField(model, "departamento");
		departamento.setLabel(gt("Departamento Destino"));
		departamento.propertie().add("type", "text").add("readonly", "true").add("rel", "col-3");

		categoria = new TextField(model, "categoria");
		categoria.setLabel(gt("Categoria de Suporte"));
		categoria.propertie().add("type", "text").add("readonly", "true").add("rel", "col-12");

		form_1_hidden_1 = new HiddenField(model, "form_1_hidden_1");
		form_1_hidden_1.setParam(true); // CRITICAL: Para que o ID circule nas ações

		// --- CAMPOS TABLE 1 ---
		datahora = new TextField(model, "datahora");
		datahora.setLabel(gt("Data/Hora"));

		tecnico = new TextField(model, "tecnico");
		tecnico.setLabel(gt("Executor"));

		estado_anterior = new TextField(model, "estado_anterior");
		estado_anterior.setLabel(gt("De:"));

		novo_estado = new TextField(model, "novo_estado");
		novo_estado.setLabel(gt("Para:"));

		observacao = new TextField(model, "observacao");
		observacao.setLabel(gt("Observação"));

		tbi_historico_id = new HiddenField(model, "tbi_historico_id");

		// --- CAMPOS FORM 2 ---
		nova_interacao__resolucao = new TextAreaField(model, "nova_interacao__resolucao");
		nova_interacao__resolucao.setLabel(gt("Parecer Técnico / Solução"));

		btn_assumir_pedido = new IGRPButton("Assumir Atendimento", "gestao_de_pedidos_de_suporte_", "Detalheshistorico", "assumir_pedido", "submit", "info|fa-play-circle", "", "");
		btn_finalizar_pedido = new IGRPButton("Concluir Pedido", "gestao_de_pedidos_de_suporte_", "Detalheshistorico", "finalizar_pedido", "confirm", "success|fa-check-square", "", "");
		btn_finalizar_pedido.propertie.add("labelConfirm", "Confirma a resolução deste pedido?");

		// --- CAMPOS FORM 3 ---
		adicionar_comentario = new TextAreaField(model, "adicionar_comentario");
		adicionar_comentario.setLabel(gt("Nova Mensagem"));

		btn_publicar_comentario = new IGRPButton("Enviar Comentário", "gestao_de_pedidos_de_suporte_", "Detalheshistorico", "publicar_comentario", "submit", "primary|fa-paper-plane", "", "");

		// --- CAMPOS TABLE 2 ---
		historico_de_comentario = new TextField(model, "historico_de_comentario");
		historico_de_comentario.setLabel(gt("Mensagens"));

		tbl_comentario_id = new HiddenField(model, "tbl_comentario_id");
		tbl_comentario_id.setParam(true); // IMPORTANTE: Para a ação de eliminar

		btn_eliminar = new IGRPButton("Eliminar", "gestao_de_pedidos_de_suporte_", "Detalheshistorico", "eliminar", "confirm", "danger|fa-trash", "", "");
	}

	@Override
	public void render(){
		form_1.addField(n_do_pedido);
		form_1.addField(data_de_abertura);
		form_1.addField(estado);
		form_1.addField(departamento);
		form_1.addField(categoria);
		form_1.addField(form_1_hidden_1);

		table_1.addField(datahora);
		table_1.addField(tecnico);
		table_1.addField(estado_anterior);
		table_1.addField(novo_estado);
		table_1.addField(observacao);
		table_1.addField(tbi_historico_id);

		form_2.addField(nova_interacao__resolucao);
		form_2.addButton(btn_assumir_pedido);
		form_2.addButton(btn_finalizar_pedido);

		form_3.addField(adicionar_comentario);
		form_3.addButton(btn_publicar_comentario);

		table_2.addField(historico_de_comentario);
		table_2.addField(tbl_comentario_id);
		table_2.addButton(btn_eliminar);

		this.addToPage(form_1);
		this.addToPage(table_1);
		this.addToPage(form_2);
		this.addToPage(form_3);
		this.addToPage(table_2);
	}

	@Override
	public void setModel(Model model) {
		n_do_pedido.setValue(model);
		data_de_abertura.setValue(model);
		estado.setValue(model);
		departamento.setValue(model);
		categoria.setValue(model);
		form_1_hidden_1.setValue(model);
		datahora.setValue(model);
		tecnico.setValue(model);
		estado_anterior.setValue(model);
		novo_estado.setValue(model);
		observacao.setValue(model);
		tbi_historico_id.setValue(model);
		nova_interacao__resolucao.setValue(model);
		adicionar_comentario.setValue(model);
		historico_de_comentario.setValue(model);
		tbl_comentario_id.setValue(model);

		table_1.loadModel(((Detalheshistorico) model).getTable_1());
		table_2.loadModel(((Detalheshistorico) model).getTable_2());
	}
}