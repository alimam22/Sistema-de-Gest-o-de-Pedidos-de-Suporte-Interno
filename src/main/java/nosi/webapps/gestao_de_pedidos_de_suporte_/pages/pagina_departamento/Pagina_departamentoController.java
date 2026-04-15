package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_departamento;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper.PageHelperPaginaDepartamentoController;

public class Pagina_departamentoController extends Controller {

	/**
	 * LOGICA DE VALIDAÇÃO DO UTILIZADOR
	 * Verifica se o código do perfil do utilizador logado é "Gestor"
	 */
	private boolean isGestor() {
		String perfilCodigo = Core.getCurrentProfileCode();
		return perfilCodigo != null && perfilCodigo.toLowerCase().contains("gestor");
	}
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{

		// Se não for Gestor, bloqueia o acesso imediatamente
		if (!isGestor()) {
			Core.setMessageError("Acesso negado: O seu perfil (" + Core.getCurrentProfileCode() + ") não tem permissão.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		}
		var model = new Pagina_departamento();
		model.load();
		var view = new Pagina_departamentoView();
		view.tbl_departamento_id.setParam(true);
		PageHelperPaginaDepartamentoController helper = new PageHelperPaginaDepartamentoController();

		model.setTable_1(helper.carregarTabela());

		view.activos.setQuery(Core.query(null, "SELECT '1' as ID, 'Sim' as NAME UNION SELECT '0' as ID, 'Não' as NAME"));
		Integer id = Core.getParamInt("p_id");
		if (Core.isNotNull(id)) {
			helper.preencherFormulario(model, id);
		}
		
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionAdicionar() throws IOException, IllegalArgumentException, IllegalAccessException{

		if (!isGestor()) {
			Core.setMessageError("Operação restrita ao perfil Gestor.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_departamento", "index");
		}
		var model = new Pagina_departamento();
		model.load();
		PageHelperPaginaDepartamentoController helper = new PageHelperPaginaDepartamentoController();

		if (Core.isHttpPost()) {
			if (helper.guardar(model)) {
				Core.setMessageSuccess("Dados salvos com sucesso!");
				this.removeQueryString("p_id");
				return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_departamento", "index");
			} else {
				return this.forward("gestao_de_pedidos_de_suporte_", "Pagina_departamento", "index", this.queryString());
			}
		}
		
		return this.redirect("gestao_de_pedidos_de_suporte_","Pagina_departamento","index", this.queryString());
	}

	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException {

		if (!isGestor()) return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_departamento", "index");
		String idTabela = Core.getParam("p_tbl_departamento_id");

		if (Core.isNotNull(idTabela)) {
			this.restartQueryString();
			this.addQueryString("p_id", idTabela);
			return this.forward("gestao_de_pedidos_de_suporte_", "Pagina_departamento", "index", this.queryString());
		}

		Core.setMessageError("Selecione um registro válido para editar.");
		return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_departamento", "index", this.queryString());
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{

		if (!isGestor()) {
			Core.setMessageError("Operação não permitida.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_departamento", "index");
		}
		var model = new Pagina_departamento();
		model.load();

		PageHelperPaginaDepartamentoController helper = new PageHelperPaginaDepartamentoController();

		Integer id = Core.getParamInt("p_tbl_departamento_id");

		if (Core.isNotNull(id)) {
			helper.eliminar(id);
		} else {
			Core.setMessageError("ID não encontrado na requisição.");
		}
		
		return this.redirect("gestao_de_pedidos_de_suporte_","Pagina_departamento","index", this.queryString());	
	}
}
