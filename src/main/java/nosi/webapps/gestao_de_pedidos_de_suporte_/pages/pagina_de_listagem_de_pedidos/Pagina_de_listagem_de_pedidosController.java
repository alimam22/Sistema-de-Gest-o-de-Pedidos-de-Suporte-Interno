package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_de_listagem_de_pedidos;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/
import nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper.PageHelperPaginaListagemPedidoController;
import nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte.ListagemPedidosService;
/*----#end-code----*/

public class Pagina_de_listagem_de_pedidosController extends Controller {

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_de_listagem_de_pedidos();
		model.load();
		var view = new Pagina_de_listagem_de_pedidosView();
		view.tbl_listapedido_id.setParam(true);
		/* Start-Code-Block (index) *//* End-Code-Block (index) */
		/*----#start-code(index)----*/
		PageHelperPaginaListagemPedidoController helper = new PageHelperPaginaListagemPedidoController();

		// 1. Carrega os dados da tabela
		helper.carregarTabela(model);

		// 2. Aplica regras de visibilidade dos botões
		helper.aplicarPermissoesBotoes(view);
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);
	}

	public Response actionNovo_pedido() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_de_listagem_de_pedidos();
		model.load();
		/* Start-Code-Block (novo_pedido)  *//* End-Code-Block  */
		/*----#start-code(novo_pedido)----*/
		if (Core.getCurrentProfileCode().toLowerCase().contains("tecnico")) {
			Core.setMessageError("Técnicos não podem criar novos pedidos.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Paginapedidos","index", this.queryString());
	}

	public Response actionPesquisar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_de_listagem_de_pedidos();
		model.load();
		/* Start-Code-Block (pesquisar)  *//* End-Code-Block  */
		/*----#start-code(pesquisar)----*/
		// O model.load() já captura os filtros, o actionIndex tratará de filtrar na tabela
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Pagina_de_listagem_de_pedidos","index", this.queryString());
	}

	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_de_listagem_de_pedidos();
		model.load();
		/* Start-Code-Block (editar)  *//* End-Code-Block  */
		/*----#start-code(editar)----*/
		PageHelperPaginaListagemPedidoController helper = new PageHelperPaginaListagemPedidoController();

		if (helper.prepararEdicao(this)) {
			return this.redirect("gestao_de_pedidos_de_suporte_", "Paginapedidos", "index", this.queryString());
		}
		Core.setMessageError("Por favor, selecione um registro.");
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index", this.queryString());
	}

	public Response actionAtribuir() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_de_listagem_de_pedidos();
		model.load();
		/* Start-Code-Block (atribuir)  *//* End-Code-Block  */
		/*----#start-code(atribuir)----*/
		String idPedido = Core.getParam("p_tbl_listapedido_id");

		if (Core.isNull(idPedido)) {
			Core.setMessageError("Selecione um pedido.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}

		this.addQueryString("p_id", idPedido);
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_", "Paginapedidos", "index", this.queryString());
	}

	public Response actionResolver() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#start-code(resolver)----*/
		// Esta ação captura o ID da linha da tabela e envia para a página do técnico
		String idPedido = Core.getParam("p_tbl_listapedido_id");

		if (Core.isNotNull(idPedido)) {
			this.addQueryString("p_id", idPedido);
			return this.redirect("gestao_de_pedidos_de_suporte_", "Meus_pedidos_", "index", this.queryString());
		}

		Core.setMessageError("ID do pedido não encontrado.");
		return this.forward("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		/*----#end-code----*/
	}

	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_de_listagem_de_pedidos();
		model.load();
		/* Start-Code-Block (eliminar)  *//* End-Code-Block  */
		/*----#start-code(eliminar)----*/
		String id = Core.getParam("p_tbl_listapedido_id");
		if (Core.isNotNull(id)) {
			ListagemPedidosService service = new ListagemPedidosService();
			if (service.eliminarPedido(id)) {
				Core.setMessageSuccess("Pedido removido.");
			}
		}
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Pagina_de_listagem_de_pedidos","index", this.queryString());
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
	/*----#start-code(custom_actions)----*/
	/*----#end-code----*/
}