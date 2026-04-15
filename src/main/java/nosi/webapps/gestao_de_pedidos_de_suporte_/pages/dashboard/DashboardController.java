package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.dashboard;

import nosi.core.webapp.Controller;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper.PageHelperDashboard;

import java.io.IOException;

/**
 * Controller do Dashboard — Gestão de Pedidos de Suporte.
 *
 * Responsável por:
 * - Carregar e apresentar o Dashboard com estatísticas e gráficos.
 * - Redirecionar para as páginas de Departamento, Categoria e Pedidos.
 */
public class DashboardController extends Controller {

	// =========================================================================
	// CONSTANTES — APLICAÇÃO E PÁGINAS
	// =========================================================================

	private static final String APP              = "gestao_de_pedidos_de_suporte_";
	private static final String PG_DASHBOARD     = "Dashboard";
	private static final String PG_DEPARTAMENTO  = "Pagina_departamento";
	private static final String PG_CATEGORIA     = "Criar_pedido";
	private static final String PG_PEDIDOS       = "Criar_pedido";
	private static final String PG_LISTA_PEDIDOS = "Pagina_listagem_de_pedidos";

	// =========================================================================
	// ACTIONS
	// =========================================================================

	/**
	 * Página principal do Dashboard.
	 * Carrega estatísticas, gráfico de estado e tabela de top técnicos.
	 */
	public Response actionIndex()
			throws IOException, IllegalArgumentException, IllegalAccessException {

		var model = new Dashboard();
		model.load();

		// TODO: Descomentar quando o controlo de perfis estiver activo
		// if (Perfil.isSolicitante()) {
		//     Core.setMessageWarning("O Dashboard é restrito a Gestores e Técnicos.");
		//     return this.redirect(APP, PG_LISTA_PEDIDOS, "index");
		// }

		new PageHelperDashboard().carregarDashboard(model);

		var view = new DashboardView();
		view.setModel(model);
		return this.renderView(view);
	}

	/**
	 * Redireciona para a gestão de Departamentos.
	 * Acesso restrito a Gestores.
	 */
	public Response actionDepartamento_button()
			throws IOException, IllegalArgumentException, IllegalAccessException {

		new Dashboard().load();

		// TODO: Descomentar quando o controlo de perfis estiver activo
		// if (!Perfil.isGestor()) {
		//     Core.setMessageError("Apenas o Gestor pode aceder à gestão de Departamentos.");
		//     return this.redirect(APP, PG_DASHBOARD, "index");
		// }

		return this.redirect(APP, PG_DEPARTAMENTO, "index", this.queryString());
	}

	/**
	 * Redireciona para a gestão de Categorias.
	 * Acesso restrito a Gestores.
	 */
	public Response actionCategoria()
			throws IOException, IllegalArgumentException, IllegalAccessException {

		new Dashboard().load();

		// TODO: Descomentar quando o controlo de perfis estiver activo
		// if (!Perfil.isGestor()) {
		//     Core.setMessageError("Apenas o Gestor pode aceder à gestão de Categorias.");
		//     return this.redirect(APP, PG_DASHBOARD, "index");
		// }

		return this.redirect(APP, PG_CATEGORIA, "index", this.queryString());
	}

	/**
	 * Redireciona para a criação de um novo Pedido.
	 */
	public Response actionPedidos()
			throws IOException, IllegalArgumentException, IllegalAccessException {

		new Dashboard().load();
		return this.redirect(APP, PG_PEDIDOS, "index", this.queryString());
	}

	/**
	 * Redireciona para a listagem de Pedidos.
	 */
	public Response actionLista_de_pedidos()
			throws IOException, IllegalArgumentException, IllegalAccessException {

		new Dashboard().load();
		return this.redirect(APP, PG_LISTA_PEDIDOS, "index", this.queryString());
	}
}