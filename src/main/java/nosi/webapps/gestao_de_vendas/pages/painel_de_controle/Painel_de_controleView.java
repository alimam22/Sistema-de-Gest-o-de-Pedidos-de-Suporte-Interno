package nosi.webapps.gestao_de_vendas.pages.painel_de_controle;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Painel_de_controleView extends View {

	// ── Campos de Estatística (Estarão no topo) ───────────────────────────
	public Field total_clientes;
	public Field total_produtos;
	public Field vendas_hoje;
	public Field usuarios_ativos;

	// ── Componentes de Layout ──────────────────────────────────────────────
	public IGRPForm form_estatisticas;
	public IGRPForm form_1;

	// ── Botões de Ação ─────────────────────────────────────────────────────
	public IGRPButton btn_adicionar_cliente;
	public IGRPButton btn_adicionar_produto;
	public IGRPButton btn_adicionar_fornecedor;
	public IGRPButton btn_adicionar_usuario;

	public Painel_de_controleView(){

		this.setPageTitle("Painel de Controle");

		// 1. Inicializar os Formulários
		form_estatisticas = new IGRPForm("form_estatisticas", "Resumo do Sistema");
		form_1 = new IGRPForm("form_1","Acesso Rapido");

		// 2. Configurar os Campos de Estatística
		total_clientes = new TextField(model,"total_clientes");
		total_clientes.setLabel(gt("Total de Clientes"));
		total_clientes.propertie().add("name","p_total_clientes").add("type","text").add("readonly","true");

		total_produtos = new TextField(model,"total_produtos");
		total_produtos.setLabel(gt("Total de Produtos"));
		total_produtos.propertie().add("name","p_total_produtos").add("type","text").add("readonly","true");

		vendas_hoje = new TextField(model,"vendas_hoje");
		vendas_hoje.setLabel(gt("Vendas Hoje"));
		vendas_hoje.propertie().add("name","p_vendas_hoje").add("type","text").add("readonly","true");

		usuarios_ativos = new TextField(model,"usuarios_ativos");
		usuarios_ativos.setLabel(gt("Usuários Ativos"));
		usuarios_ativos.propertie().add("name","p_usuarios_ativos").add("type","text").add("readonly","true");

		// 3. Configurar os Botões (Seu código original com pequenos ajustes de rel)
		btn_adicionar_cliente = new IGRPButton("Adicionar cliente","gestao_de_vendas","Painel_de_controle","adicionar_cliente","submit","info|fa-plus-square","","");
		btn_adicionar_cliente.propertie.add("id","button_489c_f8c6").add("type","form").add("class","info").add("rel","adicionar_cliente");

		btn_adicionar_produto = new IGRPButton("Adicionar produto","gestao_de_vendas","Painel_de_controle","adicionar_produto","submit","grey|fa-plus-square","","");
		btn_adicionar_produto.propertie.add("id","button_f2f1_f73e").add("type","form").add("class","grey").add("rel","adicionar_produto");

		btn_adicionar_fornecedor = new IGRPButton("Adicionar fornecedor","gestao_de_vendas","Painel_de_controle","adicionar_fornecedor","submit","purple|fa-plus-square","","");
		btn_adicionar_fornecedor.propertie.add("id","button_ea2a_8ecb").add("type","form").add("class","purple").add("rel","adicionar_fornecedor");

		btn_adicionar_usuario = new IGRPButton("Adicionar usuario","gestao_de_vendas","Painel_de_controle","adicionar_usuario","submit","primary|fa-plus-square","","");
		btn_adicionar_usuario.propertie.add("id","button_8eab_fd2b").add("type","form").add("class","primary").add("rel","adicionar_usuario");
	}

	@Override
	public void render(){
		// Adicionar campos de números ao formulário de topo
		form_estatisticas.addField(total_clientes);
		form_estatisticas.addField(total_produtos);
		form_estatisticas.addField(vendas_hoje);
		form_estatisticas.addField(usuarios_ativos);

		// Adicionar botões ao formulário de ações
		form_1.addButton(btn_adicionar_cliente);
		form_1.addButton(btn_adicionar_produto);
		form_1.addButton(btn_adicionar_fornecedor);
		form_1.addButton(btn_adicionar_usuario);

		// Renderizar na página (Estatísticas primeiro, depois Ações)
		this.addToPage(form_estatisticas);
		this.addToPage(form_1);
	}

	@Override
	public void setModel(Model model) {
		// É crucial ligar os campos ao modelo para os valores do Controller aparecerem
		total_clientes.setValue(model);
		total_produtos.setValue(model);
		vendas_hoje.setValue(model);
		usuarios_ativos.setValue(model);
	}
}