package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_de_listagem_de_pedidos;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Pagina_de_listagem_de_pedidosView extends View {

	// Colunas da Tabela (Table 1)
	public Field n_do_pedido_tbl;
	public Field assunto;
	public Field solicitante_tbl;
	public Field tecnico_tbl;
	public Field data_criacao;
	public Field prioridade_tbl;
	public Field estado_tbl;
	public Field tbl_listapedido_id;

	public IGRPTable table_1;
	public IGRPToolsBar toolsbar_1;

	public IGRPButton btn_novo_pedido;
	public IGRPButton btn_ver;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;
	public IGRPButton btn_atribuir;
	public IGRPButton btn_resolver;
	public IGRPButton btn_atualizar_estado;


	public Pagina_de_listagem_de_pedidosView(){

		this.setPageTitle("Lista de Pedidos de Suporte");

		table_1 = new IGRPTable("table_1","Lista de Pedidos");

		// --- COLUNAS DA TABELA ---

		n_do_pedido_tbl = new TextField(model,"n_do_pedido_tbl");
		n_do_pedido_tbl.setLabel(gt("Nº Pedido"));

		assunto = new TextField(model,"assunto");
		assunto.setLabel(gt("Assunto"));

		solicitante_tbl = new TextField(model,"solicitante_tbl");
		solicitante_tbl.setLabel(gt("Solicitante"));

		tecnico_tbl = new TextField(model,"tecnico_tbl");
		tecnico_tbl.setLabel(gt("Técnico"));

		data_criacao = new TextField(model,"data_criacao");
		data_criacao.setLabel(gt("Data Criação"));

		prioridade_tbl = new TextField(model,"prioridade_tbl");
		prioridade_tbl.setLabel(gt("Prioridade"));

		estado_tbl = new TextField(model,"estado_tbl");
		estado_tbl.setLabel(gt("Estado"));

		tbl_listapedido_id = new HiddenField(model,"tbl_listapedido_id");
		tbl_listapedido_id.setLabel(gt(""));
		tbl_listapedido_id.propertie().add("name","p_tbl_listapedido_id").add("type","hidden");

		// --- BOTÕES ---

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_novo_pedido = new IGRPButton("Novo Pedido","gestao_de_pedidos_de_suporte_","Paginapedidos","index","_self","info|fa-plus","","");
		btn_novo_pedido.propertie.add("type","specific").add("rel","novo_pedido");

		btn_ver = new IGRPButton("Ver","gestao_de_pedidos_de_suporte_","Detalheshistorico","index","mpsubmit","info|fa-eye","","");
		btn_ver.propertie.add("type","specific").add("rel","ver");

		btn_editar = new IGRPButton("Editar","gestao_de_pedidos_de_suporte_","Pagina_de_listagem_de_pedidos","editar","mpsubmit","warning|fa-edit","","");
		btn_editar.propertie.add("type","specific").add("rel","editar");

		btn_eliminar = new IGRPButton("Eliminar","gestao_de_pedidos_de_suporte_","Pagina_de_listagem_de_pedidos","eliminar","confirm","danger|fa-trash","","");
		btn_eliminar.propertie.add("type","specific").add("rel","eliminar").add("labelConfirm",gt("Deseja eliminar este pedido?"));

		// NOVO: botão Atribuir — visível apenas para Gestor, abre PaginaPedidos com o pedido já carregado
		btn_atribuir = new IGRPButton("Atribuir","gestao_de_pedidos_de_suporte_","Pagina_de_listagem_de_pedidos","atribuir","mpsubmit","success|fa-user-plus","","");
		btn_atribuir.propertie.add("type","specific").add("rel","atribuir");

		btn_resolver = new IGRPButton("Resolver","gestao_de_pedidos_de_suporte_","meus_pedidos_","index","mpsubmit","success|fa-check","","");
		btn_resolver.propertie.add("type","specific").add("rel","resolver");
	}

	@Override
	public void render(){
		table_1.addField(n_do_pedido_tbl);
		table_1.addField(assunto);
		table_1.addField(solicitante_tbl);
		table_1.addField(tecnico_tbl);
		table_1.addField(data_criacao);
		table_1.addField(prioridade_tbl);
		table_1.addField(estado_tbl);
		table_1.addField(tbl_listapedido_id);

		toolsbar_1.addButton(btn_novo_pedido);

		table_1.addButton(btn_ver);
		table_1.addButton(btn_editar);
		table_1.addButton(btn_atribuir);
		table_1.addButton(btn_eliminar);
		table_1.addButton(btn_resolver);

		this.addToPage(table_1);
		this.addToPage(toolsbar_1);
	}

	@Override
	public void setModel(Model model) {
		n_do_pedido_tbl.setValue(model);
		assunto.setValue(model);
		solicitante_tbl.setValue(model);
		tecnico_tbl.setValue(model);
		data_criacao.setValue(model);
		prioridade_tbl.setValue(model);
		estado_tbl.setValue(model);
		tbl_listapedido_id.setValue(model);

		table_1.loadModel(((Pagina_de_listagem_de_pedidos) model).getTable_1());
	}
}
