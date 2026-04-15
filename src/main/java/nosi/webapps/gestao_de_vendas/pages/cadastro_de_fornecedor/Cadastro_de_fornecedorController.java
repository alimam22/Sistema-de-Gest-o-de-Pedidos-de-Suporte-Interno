package nosi.webapps.gestao_de_vendas.pages.cadastro_de_fornecedor;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
import nosi.webapps.gestao_de_vendas.dao.Fornecedores;
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/

public class Cadastro_de_fornecedorController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Cadastro_de_fornecedor();
		model.load();
		var view = new Cadastro_de_fornecedorView();

		/*----#start-code(index)----*/
		String id = Core.getParam("p_id"); // O p_id que veio do actionEditar

		if (Core.isNotNull(id)) {
			// REGRA DE OURO: Adicione o p_id na QueryString para o botão Salvar não o perder
			this.addQueryString("p_id", id);

			Fornecedores f = new Fornecedores().findOne(Core.toInt(id));
			if (f != null) {
				model.setNome(f.getNome());
				model.setEmail(f.getEmail());
				model.setEndereco(f.getEndereco());
				// Se o telefone for String no banco e Integer no model:
				if(f.getTelefone() != null)
					model.setTelefone(Core.toInt(f.getTelefone().replaceAll("[^0-9]", "")));
			}
		}
		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}

	public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Cadastro_de_fornecedor();
		model.load();

		/*----#start-code(salvar)----*/
		// 1. Pegamos o p_id que mantivemos na QueryString no passo anterior
		String id = Core.getParam("p_id");

		Fornecedores fornecedor = new Fornecedores();

		// 2. Se o ID existir, carregamos o objeto do banco para garantir o UPDATE
		if (Core.isNotNull(id) && !id.isEmpty()) {
			fornecedor = new Fornecedores().findOne(Core.toInt(id));
			if(fornecedor == null) {
				fornecedor = new Fornecedores(); // Fallback se não achar no banco
			}
		}

		// 3. Mapeamos os dados da tela para o objeto
		fornecedor.setNome(model.getNome());
		fornecedor.setEmail(model.getEmail());
		fornecedor.setEndereco(model.getEndereco());
		fornecedor.setTelefone(String.valueOf(model.getTelefone()));

		// 4. PERSISTÊNCIA: Se o objeto já tem um ID do banco, o IGRP faz UPDATE automaticamente
		if (fornecedor.getId() != null) {
			fornecedor = fornecedor.update();
			System.out.println(">>> SUCESSO: Registro ID " + id + " ATUALIZADO.");
		} else {
			fornecedor = fornecedor.insert();
			System.out.println(">>> SUCESSO: NOVO registro criado.");
		}

		if (fornecedor != null && !fornecedor.hasError()) {
			Core.setMessageSuccess("Dados guardados com sucesso!");
			return this.redirect("gestao_de_vendas", "Pagina_fornecedor", "index");
		}

		Core.setMessageError("Erro ao salvar dados.");
		/*----#end-code----*/

		return this.forward("gestao_de_vendas", "Cadastro_de_fornecedor", "index", this.queryString());
	}
}