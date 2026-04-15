package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.paginapedidos;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/

import nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper.PageHelperPaginaPedidoController;

/*----#end-code----*/

public class PaginapedidosController extends Controller {

	/**
	 * Validação de Segurança Centralizada
	 */
	private boolean isPerfilValido() {
		String code = Core.getCurrentProfileCode().toLowerCase();
		return code.contains("gestor") || code.contains("tecnico") || code.contains("solicitante");
	}
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		// 1. VALIDAÇÃO DE ACESSO: Se não tiver um dos 3 perfis, volta para a listagem
		if (!isPerfilValido()) {
			Core.setMessageError("Acesso negado: Perfil não autorizado.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}
		var model = new Paginapedidos();
		model.load();
		var view = new PaginapedidosView();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		view.departamento.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		view.categoria.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/* Start-Code-Block (index) *//* End-Code-Block (index) */
		/*----#start-code(index)----*/
		var pageHelper = new PageHelperPaginaPedidoController();




		// 2. Aplica regras de visibilidade (ex: esconde campos do João ou bloqueia do Carlos)
		pageHelper.actionIndex(view);

		// 3. Segurança: O Helper já valida se o Solicitante é dono do ID que está a carregar
		pageHelper.carregarPedidoParaEdicao(model);

		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);
	}

	public Response actionSubmeter() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Paginapedidos();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("gestao_de_pedidos_de_suporte_","Criar_pedido","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (submeter)  *//* End-Code-Block  */
		/*----#start-code(submeter)----*/
		// 1. VALIDAÇÃO DE PERMISSÃO NA SUBMISSÃO
		String perfil = Core.getCurrentProfileCode().toLowerCase();

		// Impede que Técnico crie novos pedidos (se não houver ID)
		if (perfil.contains("tecnico") && (model.getP_id() == null || model.getP_id() == 0)) {
			Core.setMessageError("Técnicos só podem atualizar pedidos existentes, não criar novos.");
			return this.forward("gestao_de_pedidos_de_suporte_", "Paginapedidos", "index");
		}
		PageHelperPaginaPedidoController pageHelper = new PageHelperPaginaPedidoController();

		try {
			// 1. Tenta executar a lógica de negócio
			pageHelper.actionSubmeter(model);

			// 2. Se sucesso, limpa a query string (opcional) e redireciona
			return this.redirect("gestao_de_pedidos_de_suporte_", "Paginapedidos", "index");

		} catch (IllegalArgumentException e) {
			// Erros de validação de negócio (campos vazios, etc)
			Core.setMessageError(e.getMessage());
		} catch (Exception e) {
			// Erros inesperados (Banco de dados, NullPointer, etc)
			Core.setMessageError("Ocorreu um erro inesperado: " + e.getMessage());
		}

		// 3. Em caso de erro, recarregamos a página atual mantendo os dados do model
		PaginapedidosView view = new PaginapedidosView();

		// Importante: Recarregar os combos (departamento/categoria) que estão no index
		pageHelper.actionIndex(view);

		view.setModel(model);
		return this.renderView(view);
		/*----#end-code----*/
		//return this.redirect("gestao_de_pedidos_de_suporte_","Criar_pedido","index", this.queryString());
	}

	public Response actionCancelar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Paginapedidos();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("gestao_de_pedidos_de_suporte_","Criar_pedido","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (cancelar)  *//* End-Code-Block  */
		/*----#start-code(cancelar)----*/


		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Paginapedidos","index", this.queryString());
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}