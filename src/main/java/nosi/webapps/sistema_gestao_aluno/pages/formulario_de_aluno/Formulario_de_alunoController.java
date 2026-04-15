package nosi.webapps.sistema_gestao_aluno.pages.formulario_de_aluno;

import nosi.core.webapp.Controller;//
import java.io.IOException;//
import nosi.core.webapp.Core;//
import nosi.core.webapp.Response;//
import nosi.webapps.sistema_gestao_aluno.dao.Aluno;

/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/


/*----#end-code----*/

public class Formulario_de_alunoController extends Controller {

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Formulario_de_aluno();
		model.load();
		var view = new Formulario_de_alunoView();

		/*----#start-code(index)----*/

		// 1. Lógica de Edição (Busca no Banco via DAO/Entity)
		try {
			String isEdit = Core.getParam("isEdit");
			int idAluno = Core.getParamInt("p_id_aluno"); // Certifique-se que o nome do parâmetro na página é p_id_aluno

			if (Core.isNotNull(isEdit) && idAluno != 0) {
				// Uso do DAO/ActiveRecord para buscar o Aluno
				Aluno aluno = new Aluno().findOne(idAluno);

				if (aluno != null && !aluno.hasError()) {
					// Mapeamento: Banco de Dados (Entity) -> Tela (Model)
					model.setNome(aluno.getNome());
					model.setApelido(aluno.getApelido());
					model.setIdade(aluno.getIdade()); // Se o campo no model for Integer
					model.setCurso(aluno.getCurso());
					model.setEmail(aluno.getEmail());


				}
			}
		} catch (Exception e) {
			Core.setMessageError("Erro ao carregar dados do aluno: " + e.getMessage());
			e.printStackTrace();
		}

		/*----#end-code----*/

		view.setModel(model);
		return this.renderView(view);
	}



	public Response actionGuardar() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Formulario_de_aluno();
		model.load();

		/*----#start-code(guardar)----*/

		Aluno aluno = new Aluno();

		// 1. Verificar se é Edição ou Novo
		int idAluno = Core.getParamInt("p_id_aluno");
		boolean isEdit = Core.isNotNull(Core.getParam("isEdit")) && idAluno != 0;

		if (isEdit) {
			aluno = new Aluno().findOne(idAluno);
		}

		// 2. Mapear dados do Model para a Entity/DAO
		aluno.setNome(model.getNome());
		aluno.setApelido(model.getApelido());
		aluno.setIdade(model.getIdade());
		aluno.setCurso(model.getCurso());
		aluno.setEmail(model.getEmail());

		// 3. Persistir usando os métodos nativos do BaseActiveRecord
		if (isEdit) {
			aluno = aluno.update(); // Se já existe, atualiza
		} else {
			aluno = aluno.insert(); // Se é novo, insere
		}

		// 4. Validar resultado
		if (aluno != null && !aluno.hasError()) {
			Core.setMessageSuccess("Operação realizada com sucesso!");
		} else {
			String erro = (aluno != null && aluno.getError() != null) ? String.valueOf(aluno.getError()) : "Erro ao aceder à base de dados.";
			Core.setMessageError("Erro: " + erro);
			return this.forward("sistema_gestao_aluno", "Formulario_de_aluno", "index", this.queryString());
		}

		/*----#end-code----*/

		return this.redirect("sistema_gestao_aluno", "Formulario_de_aluno", "index");
	}
}
