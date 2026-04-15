package nosi.webapps.gestao_de_vendas.pages.cadastro_de_cliente;

import nosi.core.webapp.Controller;
import java.io.IOException;

import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.PageHelperCadastroCliente.PageHelperCadastroClienteController;


/*----#start-code(packages_import)----*/
/*----#end-code----*/

@SuppressWarnings("unused") // Instanciado via reflexão pelo IGRP
public class Cadastro_de_clienteController extends Controller {

	// =========================================================================
	// INDEX — exibe o formulário (criação ou edição)
	// =========================================================================
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Cadastro_de_cliente();
		model.load();
		var view = new Cadastro_de_clienteView();

		/*----#start-code(index)----*/
		// chamada de page helper
		PageHelperCadastroClienteController.handleIndex(model, view);
		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);
	}

	// =========================================================================
	// SALVA — INSERT ou UPDATE (rel="salva" no btn_salva da View)
	// =========================================================================
	@SuppressWarnings("unused") // Chamado via reflexão pelo IGRP (rel="salva")
	public Response actionSalva() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Cadastro_de_cliente();
		model.load();

		/*----#start-code(salva)----*/

		boolean isSucesso = PageHelperCadastroClienteController.processarSalva(model);

		if (isSucesso) {
			// Se salvou com sucesso, vai para a lista de clientes
			return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
		}

		/*----#end-code----*/
		// Em caso de erro, mantém os dados digitados no formulário
		return this.forward("gestao_de_vendas", "Cadastro_de_cliente", "index", this.queryString());
	}

	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
	/*----#start-code(custom_actions)----*/
	/*----#end-code----*/
}