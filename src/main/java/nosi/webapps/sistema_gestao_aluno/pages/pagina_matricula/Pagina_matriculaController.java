package nosi.webapps.sistema_gestao_aluno.pages.pagina_matricula;

import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import java.io.IOException;
import java.util.Date;
import nosi.webapps.sistema_gestao_aluno.dao.Aluno;
import nosi.webapps.sistema_gestao_aluno.dao.TblMatricula;

/*----#start-code(packages_import)----*/
/*----#end-code----*/

public class Pagina_matriculaController extends Controller { // CORREÇÃO: Removido o <TblAluno>

	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Pagina_matricula();
		model.load();
		var view = new Pagina_matriculaView();

		/*----#start-code(index)----*/

		// A query deve usar os nomes exatos das colunas no seu Banco de Dados
		view.aluno.setQuery(Core.query(null, "SELECT id_aluno as ID, nome as NAME FROM tbl_aluno ORDER BY nome ASC"));

		view.curso.setQuery(Core.query(null, "SELECT 'Informatica' as ID, 'Engenharia Informática' as NAME "
				+ "UNION SELECT 'Gestao' as ID, 'Gestão de Empresas' as NAME "
				+ "UNION SELECT 'Direito' as ID, 'Direito' as NAME"));

		view.estado.setQuery(Core.query(null, "SELECT '1' as ID, 'Ativo' as NAME "
				+ "UNION SELECT '0' as ID, 'Inativo' as NAME"));

		// CAPTURA: Tenta pegar o ID que vem da Lista de Alunos
		String idParam = Core.getParam("p_aluno");
		if(Core.isNull(idParam)) idParam = Core.getParam("p_id_aluno");

		if(Core.isNotNull(idParam)){
			model.setAluno(idParam); // Injeta o ID no model

			// Opcional: Mostrar info do aluno
			Aluno alunoObj = new Aluno().findOne(Core.toInt(idParam));
			if(alunoObj != null) Core.setMessageInfo("Matriculando: " + alunoObj.getNome());
		}

		/*----#end-code----*/
		view.setModel(model); // Agora o model já vai com o aluno setado para a View
		return this.renderView(view);
	}

	public Response actionSalvar() throws IOException, IllegalArgumentException, IllegalAccessException {
		var model = new Pagina_matricula();
		model.load();

		/*----#start-code(salvar)----*/
		if (model.validate()) {
			TblMatricula matricula = new TblMatricula();

			matricula.setAnoLetivo(model.getAno_letivo());
			matricula.setCurso(model.getCurso());
			matricula.setEstadoMatricula(model.getEstado());
			matricula.setDataMatricula(new Date());

			// 1. Pegar o ID do Aluno diretamente do que foi selecionado no ComboBox
			String idSelecionado = model.getAluno();

			if (Core.isNotNull(idSelecionado)) {
				// 2. Tentar encontrar o Aluno no banco de dados
				Aluno alunoObj = new Aluno().findOne(Core.toInt(idSelecionado));

				if (alunoObj != null) {
					// 3. SE ENCONTROU: Associa o objeto aluno à matrícula
					matricula.setIdAluno(alunoObj);
				} else {
					// SE NÃO ENCONTROU NO BANCO: Para aqui e avisa
					Core.setMessageError("Erro: O aluno selecionado (ID: " + idSelecionado + ") não existe no sistema.");
					return this.forward("sistema_gestao_aluno", "Pagina_matricula", "index", this.queryString());
				}
			} else {
				// SE O COMBOBOX VIER VAZIO: Para aqui e avisa
				Core.setMessageError("Erro: Seleção de aluno é obrigatória. Por favor, escolha um aluno na lista.");
				return this.forward("sistema_gestao_aluno", "Pagina_matricula", "index", this.queryString());
			}

			// 4. Só tenta inserir se passou pelas verificações acima
			matricula = matricula.insert();

			if (matricula != null && !matricula.hasError()) {
				Core.setMessageSuccess("Matrícula realizada com sucesso!");
				return this.redirect("sistema_gestao_aluno", "Lista_de_alunos", "index");
			} else {
				// Pega a mensagem de erro específica do Hibernate/Banco
				String erroMsg = (matricula != null && matricula.getError() != null)
						? matricula.getError().toString()
						: "Erro desconhecido ao inserir matrícula.";
				Core.setMessageError("Erro ao salvar no banco: " + erroMsg);
			}
		} else {
			Core.setMessageError("Por favor, preencha os campos obrigatórios corretamente.");
		}
		/*----#end-code----*/

		return this.forward("sistema_gestao_aluno", "Pagina_matricula", "index", this.queryString());
	}
}