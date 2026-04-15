package nosi.webapps.gestao_de_vendas.pages.pagina_fornecedor;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import java.util.ArrayList;
import java.util.List;

import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
import nosi.webapps.gestao_de_vendas.dao.Fornecedores;
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/

public class Pagina_fornecedorController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {

		var model = new Pagina_fornecedor();
		model.load();

		var view = new Pagina_fornecedorView();

		// importante para Edit/Delete
		view.id_tbl_fornecedor.setParam(true);

		/*----#start-code(index)----*/

		// 1. Lista que a tabela espera
		List<Pagina_fornecedor.Table_1> listaTabela = new ArrayList<>();

		try {
			// 2. Buscar dados do banco (DAO)
			List<Fornecedores> fornecedoresDb = new Fornecedores().findAll();

			if (fornecedoresDb != null) {
				for (Fornecedores f : fornecedoresDb) {

					Pagina_fornecedor.Table_1 linha = new Pagina_fornecedor.Table_1();

					// 3. Mapear campos (AGORA CORRETOS)
					linha.setNome(f.getNome());
					linha.setEmail(f.getEmail());
					linha.setTelefone(f.getTelefone());
					linha.setEndereco(f.getEndereco());



					// MUITO IMPORTANTE (ID da linha)
					linha.setId_tbl_fornecedor(String.valueOf(f.getId()));

					listaTabela.add(linha);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Core.setMessageError("Erro ao carregar lista de fornecedores: " + e.getMessage());
		}

		// 4. Alimentar a tabela
		model.setTable_1(listaTabela);

		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException {
		/*----#start-code(editar)----*/

		// 1. Tenta pegar o ID da forma mais direta
		String id = Core.getParam("p_id_tbl_fornecedor");

		// 2. Se falhar (comum em tabelas), tenta pegar do Array
		if (Core.isNull(id)) {
			String[] ids = Core.getParamArray("p_id_tbl_fornecedor");
			if (ids != null && ids.length > 0) {
				id = ids[0];
			}
		}

		// Log de conferência no console do servidor
		System.out.println(">>> ACTION EDITAR - ID FINAL: [" + id + "]");

		if (Core.isNotNull(id) && !id.trim().isEmpty()) {

			// Criamos uma nova QueryString limpa para evitar conflitos
			this.addQueryString("p_id", id);
			this.addQueryString("isEdit", "true");

			// Redireciona para o Cadastro de Fornecedor
			return this.redirect("gestao_de_vendas", "Cadastro_de_fornecedor", "index", this.queryString());
		}

		Core.setMessageError("Erro: Não foi possível recuperar o ID do fornecedor.");
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas", "Pagina_fornecedor", "index");
	}


// funcinal
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#start-code(eliminar)----*/
		String id = Core.getParam("p_id_tbl_fornecedor");

		if(Core.isNull(id)){
			String[] ids = Core.getParamArray("p_id_tbl_fornecedor");
			if(ids != null && ids.length > 0) id = ids[0];
		}

		if (Core.isNotNull(id)) {
			Fornecedores f = new Fornecedores().findOne(Core.toInt(id));
			if (f != null && f.delete(Core.toInt(id))) {
				Core.setMessageSuccess();
			} else {
				Core.setMessageError("Não foi possível eliminar o registro.");
			}
		}
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas","Pagina_fornecedor","index");
	}
}