package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_categoria;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper.PageHelperPaginaCategoriaController;
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/

public class Pagina_categoriaController extends Controller {
	/**
	 * Validação centralizada no Controller para proteger as ações.
	 */
	private boolean isGestor() {
		String code = Core.getCurrentProfileCode();
		return code != null && code.toLowerCase().contains("gestor");
	}
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_categoria();
		model.load();
		var view = new Pagina_categoriaView();
		view.tbl_categoria_id.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_1(Core.query(null,"SELECT 'Accusantium unde accusantium d' as nome_categoria,'Totam consectetur sed magna to' as descricao_categoria,'Rem lorem perspiciatis officia' as activo_categoria,'Ipsum totam lorem sit ut' as data_categoria,'hidden-8cb5_52e0' as tbl_categoria_id "));
		view.activo.setQuery(Core.query(null,"SELECT 'id' as ID,'name' as NAME "));
		  ----#gen-example */
		/* Start-Code-Block (index) */ /* End-Code-Block (index) */
		/*----#start-code(index)----*/

		// VALIDAÇÃO DE PERMISSÃO: Apenas GESTOR pode aceder à gestão de Categorias
//		if (!Perfil.isGestor()) {
//			Core.setMessageError("Apenas o Gestor pode aceder à gestão de Categorias.");
//			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
//		}

		PageHelperPaginaCategoriaController helper = new PageHelperPaginaCategoriaController();

		helper.carregarTabela(model);

		view.activo.setQuery(Core.query(null, "SELECT '1' as ID, 'Sim' as NAME UNION SELECT '0' as ID, 'Não' as NAME"));

		Integer id = Core.getParamInt("p_tbl_categoria_id");
		if (Core.isNull(id)) {
			id = Core.getParamInt("p_id");
		}

		if (Core.isNotNull(id)) {
			helper.preencherFormulario(model, id);
		}
		
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);	
	}
	
	public Response actionAdicionar() throws IOException, IllegalArgumentException, IllegalAccessException{
		if (!isGestor()) {
			Core.setMessageError("Ação não autorizada.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_categoria", "index");
		}
		var model = new Pagina_categoria();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_tbl_categoria_id",Core.getParam("p_tbl_categoria_id"));
		  return this.forward("gestao_de_pedidos_de_suporte_","Criar_pedido","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (adicionar)  */ /* End-Code-Block  */
		/*----#start-code(adicionar)----*/

		// VALIDAÇÃO DE PERMISSÃO: Apenas GESTOR
//		if (!Perfil.isGestor()) {
//			Core.setMessageError("Apenas o Gestor pode adicionar/editar categorias.");
//			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
//		}

		PageHelperPaginaCategoriaController helper = new PageHelperPaginaCategoriaController();

		if (Core.isHttpPost()) {
			if (helper.guardar(model)) {
				this.removeQueryString("p_id");
				this.removeQueryString("p_tbl_categoria_id");
			} else {
				return this.forward("gestao_de_pedidos_de_suporte_", "Pagina_categoria", "index", this.queryString());
			}
		}
		
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Pagina_categoria","index", this.queryString());
	}
	
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Pagina_categoria();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_tbl_categoria_id",Core.getParam("p_tbl_categoria_id"));
		  return this.forward("gestao_de_pedidos_de_suporte_","Pagina_categoria","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (editar)  */ /* End-Code-Block  */
		/*----#start-code(editar)----*/

		// VALIDAÇÃO DE PERMISSÃO: Apenas GESTOR
//		if (!Perfil.isGestor()) {
//			Core.setMessageError("Apenas o Gestor pode editar categorias.");
//			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
//		}

		String idTabela = Core.getParam("p_tbl_categoria_id");

		if (Core.isNotNull(idTabela)) {
			this.addQueryString("p_id", idTabela);
			return this.forward("gestao_de_pedidos_de_suporte_", "Pagina_categoria", "index", this.queryString());
		}
		
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Pagina_categoria","index", this.queryString());	
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		if (!isGestor()) {
			Core.setMessageError("Ação não autorizada.");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_categoria", "index");
		}
		var model = new Pagina_categoria();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_tbl_categoria_id",Core.getParam("p_tbl_categoria_id"));
		  return this.forward("gestao_de_pedidos_de_suporte_","Pagina_categoria","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (eliminar)  */ /* End-Code-Block  */
		/*----#start-code(eliminar)----*/

		// VALIDAÇÃO DE PERMISSÃO: Apenas GESTOR pode eliminar
//		if (!Perfil.isGestor()) {
//			Core.setMessageError("Apenas o Gestor pode eliminar categorias.");
//			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
//		}

		PageHelperPaginaCategoriaController helper = new PageHelperPaginaCategoriaController();

		Integer id = Core.getParamInt("p_tbl_categoria_id");

		if (Core.isNotNull(id)) {
			helper.eliminar(id);
		} else {
			Core.setMessageError("ID não encontrado na requisição.");
		}
		
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Pagina_categoria","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  */ /* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}
