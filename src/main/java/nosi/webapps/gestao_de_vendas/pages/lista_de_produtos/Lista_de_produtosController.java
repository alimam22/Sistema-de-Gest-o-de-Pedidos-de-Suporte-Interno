package nosi.webapps.gestao_de_vendas.pages.lista_de_produtos;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.PageHelperPaginaProduto.PageHelperPaginaProdutoController;

public class Lista_de_produtosController extends Controller {

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Lista_de_produtos();
		model.load();
		var view = new Lista_de_produtosView();
		view.id_tbl_produto.setParam(true);

		/*----#start-code(index)----*/
		PageHelperPaginaProdutoController.handleIndex(model, view);
		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}

	public Response actionNovo_produto() throws IOException {
		return this.redirect("gestao_de_vendas", "Cadastro_de_produto", "index");
	}

	public Response actionEditar() throws IOException {
		/*----#start-code(editar)----*/
		if (PageHelperPaginaProdutoController.prepararEditar(this)) {
			return this.redirect("gestao_de_vendas", "Cadastro_de_produto", "index", this.queryString());
		}
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas", "Lista_de_produtos", "index");
	}

	public Response actionEliminar() throws IOException {
		/*----#start-code(eliminar)----*/
		PageHelperPaginaProdutoController.eliminar();
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas", "Lista_de_produtos", "index");
	}
}