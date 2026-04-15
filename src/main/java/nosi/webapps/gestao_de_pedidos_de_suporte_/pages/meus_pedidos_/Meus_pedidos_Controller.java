package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.meus_pedidos_;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper.PageHelperMeusPedidoController;

/*----#start-code(packages_import)----*/
/*----#end-code----*/

public class Meus_pedidos_Controller extends Controller {

	// =========================================================================
	// Utilitário: resolve o ID do pedido a partir de qualquer parâmetro possível
	// =========================================================================
	private String resolverIdPedido() {
		// Primeiro tenta o parâmetro da tabela (quando vem da listagem)
		String id = Core.getParam("p_tbl_listapedido_id");
		// Depois tenta o p_id (quando vem de redirecionamento ou URL)
		if (Core.isNull(id)) id = Core.getParam("p_id");
		return id;
	}

	// =========================================================================
	// INDEX — carrega o pedido e aplica permissões
	// =========================================================================
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Meus_pedidos_();
		model.load();
		var view = new Meus_pedidos_View();

		/*----#start-code(index)----*/
		PageHelperMeusPedidoController helper = new PageHelperMeusPedidoController();
		helper.carregarDadosPedido(model);
		helper.aplicarPermissoesBotoes(view, model);
		
		// Garante que o p_id é preservado no model para as ações subsequentes
		String idParam = resolverIdPedido();
		if (Core.isNotNull(idParam)) {
			try {
				model.setP_id(Integer.parseInt(idParam));
			} catch (NumberFormatException ignored) {}
		}
		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}

	// =========================================================================
	// ACEITAR — avança o estado: ATRIBUIDO→EM_ANALISE, EM_ANALISE→EM_EXECUCAO
	// =========================================================================
	public Response actionAceitar() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Meus_pedidos_();
		model.load();

		/*----#start-code(aceitar)----*/
		// Tenta obter o ID de várias fontes
		String id = resolverIdPedido();
		
		// Se não encontrou nos parâmetros, tenta obter do model carregado
		if (Core.isNull(id) && model.getP_id() != null) {
			id = String.valueOf(model.getP_id());
		}

		if (Core.isNull(id)) {
			Core.setMessageError("Não foi possível identificar o pedido. Por favor, selecione o pedido novamente.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}

		PageHelperMeusPedidoController helper = new PageHelperMeusPedidoController();

		if (helper.aceitar(model)) {
			Core.setMessageSuccess("Estado do pedido atualizado com sucesso!");
		}

		this.addQueryString("p_id", id);
		return this.forward("gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "index", this.queryString());
		/*----#end-code----*/
	}

	// =========================================================================
	// RESOLVER — conclui o pedido: EM_EXECUCAO→CONCLUIDO
	// =========================================================================
	public Response actionResolver() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Meus_pedidos_();
		model.load();

		/*----#start-code(resolver)----*/
		// Tenta obter o ID de várias fontes
		String id = resolverIdPedido();
		
		// Se não encontrou nos parâmetros, tenta obter do model carregado
		if (Core.isNull(id) && model.getP_id() != null) {
			id = String.valueOf(model.getP_id());
		}

		PageHelperMeusPedidoController helper = new PageHelperMeusPedidoController();

		if (helper.resolver(model)) {
			Core.setMessageSuccess("Pedido concluído com sucesso!");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}

		this.addQueryString("p_id", id);
		return this.forward("gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "index", this.queryString());
		/*----#end-code----*/
	}

	// =========================================================================
	// REJEITAR — rejeita o pedido com observação obrigatória
	// =========================================================================
	public Response actionRejeitar() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Meus_pedidos_();
		model.load();

		/*----#start-code(rejeitar)----*/
		// Tenta obter o ID de várias fontes
		String id = resolverIdPedido();
		
		// Se não encontrou nos parâmetros, tenta obter do model carregado
		if (Core.isNull(id) && model.getP_id() != null) {
			id = String.valueOf(model.getP_id());
		}

		PageHelperMeusPedidoController helper = new PageHelperMeusPedidoController();

		if (helper.rejeitar(model)) {
			Core.setMessageWarning("O pedido foi rejeitado.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}

		this.addQueryString("p_id", id);
		return this.forward("gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "index", this.queryString());
		/*----#end-code----*/
	}

	// =========================================================================
	// CANCELAR — cancela o pedido
	// =========================================================================
	public Response actionCancelar() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Meus_pedidos_();
		model.load();

		/*----#start-code(cancelar)----*/
		// Tenta obter o ID de várias fontes
		String id = resolverIdPedido();
		
		// Se não encontrou nos parâmetros, tenta obter do model carregado
		if (Core.isNull(id) && model.getP_id() != null) {
			id = String.valueOf(model.getP_id());
		}

		PageHelperMeusPedidoController helper = new PageHelperMeusPedidoController();

		if (helper.cancelar(model)) {
			Core.setMessageInfo("O pedido foi cancelado.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}

		this.addQueryString("p_id", id);
		return this.forward("gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "index", this.queryString());
		/*----#end-code----*/
	}

	public Response actionAtualizar_estado() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Meus_pedidos_();
		model.load();

		String id = resolverIdPedido();
		if (Core.isNull(id)) {
			Core.setMessageError("Selecione um pedido.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}

		PageHelperMeusPedidoController helper = new PageHelperMeusPedidoController();
		if (helper.atualizarEstado(model)) {
			Core.setMessageSuccess("Estado atualizado com sucesso!");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}

		this.addQueryString("p_id", id);
		return this.forward("gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "index", this.queryString());
	}

	/*----#start-code(custom_actions)----*/
	/*----#end-code----*/
}
