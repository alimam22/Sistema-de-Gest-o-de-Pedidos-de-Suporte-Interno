package nosi.webapps.sistema_gestao_aluno.pages.lista_de_alunos;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import java.util.ArrayList;
import java.util.List;

import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
import nosi.webapps.sistema_de_carros.dao.TblCarro;
import nosi.webapps.sistema_gestao_aluno.dao.Aluno;
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/
		
public class Lista_de_alunosController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Lista_de_alunos();
		model.load();
		var view = new Lista_de_alunosView();

		/*----#start-code(index)----*/

		try {
			// 1. Usar o DAO (Active Record) para buscar todos os alunos do banco
			// O método all() retorna uma List<Aluno>
			List<Aluno> listaAlunos = new Aluno().find().all();

			// 2. Criar uma lista para armazenar as linhas da tabela da View
			List<Lista_de_alunos.Table_1> tabelaDados = new ArrayList<>();

			if (listaAlunos != null) {
				for (Aluno alunoDb : listaAlunos) {
					// Criar uma nova linha da tabela
					Lista_de_alunos.Table_1 linha = new Lista_de_alunos.Table_1();

					// Mapeamento DAO -> Model da Tabela
					linha.setNome(alunoDb.getNome());
					linha.setApelido(alunoDb.getApelido());
					linha.setIdade(Integer.valueOf(alunoDb.getIdade().toString()));
					linha.setCurso(alunoDb.getCurso());
					linha.setEmail(alunoDb.getEmail());

					// IMPORTANTE: Setar o ID oculto da linha para as ações (Editar/Eliminar)
					// Certifique-se que o nome do campo no model da tabela é p_id_aluno


					tabelaDados.add(linha);
				}
			}

			// 3. Carregar a lista formatada no model principal
			model.setTable_1(tabelaDados);

		} catch (Exception e) {
			Core.setMessageError("Erro ao listar alunos: " + e.getMessage());
			e.printStackTrace();
		}

		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}
	
	public Response actionAdicionar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_alunos();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_gestao_aluno","Lista_de_alunos","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (adicionar)  *//* End-Code-Block  */
		/*----#start-code(adicionar)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_gestao_aluno","Formulario_de_aluno","index", this.queryString());
	}
	
	public Response actionTable_1_button_1() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Lista_de_alunos();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  return this.forward("sistema_gestao_aluno","Lista_de_alunos","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (table_1_button_1)  *//* End-Code-Block  */
		/*----#start-code(table_1_button_1)----*/
		
		
		/*----#end-code----*/
		return this.redirect("sistema_gestao_aluno","Lista_de_alunos","index", this.queryString());	
	}

	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Lista_de_alunos();
		model.load();

		/*----#start-code(eliminar)----*/

		// 1. Capturar o ID do aluno da linha selecionada
		// O IGRP envia automaticamente o nome do campo da tabela (p_id_aluno)
		/*int idAluno = Core.getParamInt("p_id_aluno");

		if (idAluno != 0) {
			// 2. Instanciar o DAO e buscar o registro
			Aluno aluno = new Aluno().findOne(idAluno);

			// 3. Verificar se o registro existe e tentar eliminar
			if (aluno != null && !aluno.hasError()) {
				boolean sucesso = aluno.delete(idAluno);

				if (sucesso) {
					Core.setMessageSuccess("Aluno eliminado com sucesso!");
				} else {
					Core.setMessageError("Não foi possível eliminar o aluno.");
				}
			} else {
				Core.setMessageError("Aluno não encontrado no sistema.");
			}
		} else {
			Core.setMessageWarning("ID de aluno inválido.");
		}/*

		 */
		Aluno aluno = new Aluno().findOne(Core.getParamInt("p_id_aluno"));
		if (aluno != null && !aluno.hasError()) {
			boolean del = aluno.delete(Core.getParamInt("p_id_aluno"));
			if (del)
				Core.setMessageSuccess();
			else
				Core.setMessageError();
		}

		/*----#end-code----*/

		// Redireciona de volta para a listagem para atualizar a tabela
		return this.redirect("sistema_gestao_aluno", "Lista_de_alunos", "index");
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/


/*----#end-code----*/
}