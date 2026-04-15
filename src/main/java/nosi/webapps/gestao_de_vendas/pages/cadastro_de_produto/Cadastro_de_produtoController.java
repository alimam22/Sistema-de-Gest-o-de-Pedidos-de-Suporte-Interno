package nosi.webapps.gestao_de_vendas.pages.cadastro_de_produto;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.PageHelperCadastroProduto.PageHelperCadastroProdutoController;

public class Cadastro_de_produtoController extends Controller {

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Cadastro_de_produto();
		model.load();
		var view = new Cadastro_de_produtoView();

		/*----#start-code(index)----*/
		// Chama o helper para carregar dados se for edição
		PageHelperCadastroProdutoController.handleIndex(model, view);
		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}

	public Response actionSalva() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Cadastro_de_produto();
		model.load();

		/*----#start-code(salva)----*/
		// Delega a lógica de salvar e recebe o status de sucesso
		boolean sucesso = PageHelperCadastroProdutoController.processarSalva(model);

		if (sucesso) {
			return this.redirect("gestao_de_vendas", "Lista_de_produtos", "index");
		}

		// Se falhar (validação ou erro), volta para o form mantendo o p_id na URL
		this.addQueryString("p_id", Core.getParam("p_id"));
		/*----#end-code----*/

		return this.forward("gestao_de_vendas", "Cadastro_de_produto", "index", this.queryString());
	}
}