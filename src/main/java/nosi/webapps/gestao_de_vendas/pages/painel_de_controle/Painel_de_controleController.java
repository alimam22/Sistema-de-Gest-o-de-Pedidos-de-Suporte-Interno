package nosi.webapps.gestao_de_vendas.pages.painel_de_controle;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;

/*----#start-code(packages_import)----*/
import nosi.webapps.gestao_de_vendas.dao.Clientes;
// Importe outras DAOs conforme for criando (ex: Produtos, Vendas, Usuarios)
/*----#end-code----*/

public class Painel_de_controleController extends Controller {

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Painel_de_controle();
		model.load();
		var view = new Painel_de_controleView();

		/*----#start-code(index)----*/

		try {
			// 1. Contagem de Clientes usando a DAO
			// O find().getCount() é o método padrão do IGRP para contar registros
			long totalClientes = new Clientes().find().getCount();
			model.setTotal_clientes(String.valueOf(totalClientes));

			// 2. Exemplo para outros contadores (ajuste conforme suas DAOs existirem)
			// model.setTotal_produtos("150");
			// model.setVendas_hoje("2.500$");
			// model.setUsuarios_ativos(String.valueOf(Core.query("db_vendas", "SELECT count(*) FROM tbl_usuarios").getSingleResult()));

		} catch (Exception e) {
			// Se o banco falhar, define 0 para não quebrar a página
			model.setTotal_clientes("0");
			Core.setMessageError("Erro ao carregar dados: " + e.getMessage());
		}

		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}

	public Response actionAdicionar_cliente() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#start-code(adicionar_cliente)----*/
		this.restartQueryString(); // Limpa IDs de edições anteriores
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas","Cadastro_de_cliente","index");
	}

	public Response actionAdicionar_produto() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#start-code(adicionar_produto)----*/
		this.restartQueryString();
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas","Cadastro_de_produto","index");
	}

	public Response actionAdicionar_fornecedor() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#start-code(adicionar_fornecedor)----*/
		this.restartQueryString();
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas","Cadastro_de_fornecedor","index");
	}

	public Response actionAdicionar_usuario() throws IOException, IllegalArgumentException, IllegalAccessException{
		/*----#start-code(adicionar_usuario)----*/
		this.restartQueryString();
		/*----#end-code----*/
		return this.redirect("gestao_de_vendas","Cadastro_de_usuario","index");
	}

	/*----#start-code(custom_actions)----*/
	// Se você criou botões de "Ver Lista" na View, implemente aqui:
	public Response actionVer_clientes() throws IOException {
		return this.redirect("gestao_de_vendas", "Pagina_cliente", "index");
	}
	/*----#end-code----*/
}