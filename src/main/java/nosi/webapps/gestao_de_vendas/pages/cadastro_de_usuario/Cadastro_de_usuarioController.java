package nosi.webapps.gestao_de_vendas.pages.cadastro_de_usuario;

import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
import nosi.webapps.gestao_de_vendas.dao.Usuarios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*----#start-code(packages_import)----*/
/*----#end-code----*/

@SuppressWarnings("unused") // Instanciado via reflexão pelo IGRP
public class Cadastro_de_usuarioController extends Controller {

	// ─────────────────────────────────────────────────────────────────────────
	// CONSTANTES
	// ─────────────────────────────────────────────────────────────────────────
	private static final String APP       = "gestao_de_vendas";
	private static final String PAGE      = "Cadastro_de_usuario";
	private static final String PARAM_ID  = "p_id_tbl_usuario";
	private static final String PARAM_EDIT = "p_id_edit";

	private static final String QUERY_ATIVO =
			"SELECT '1' AS ID, 'Sim' AS NAME " +
					"UNION SELECT '0' AS ID, 'Não' AS NAME";

	// ─────────────────────────────────────────────────────────────────────────
	// UTILITÁRIOS PRIVADOS
	// ─────────────────────────────────────────────────────────────────────────

	/**
	 * Resolve o ID enviado pela tabela do IGRP.
	 * O framework pode enviar o parâmetro como String simples ou como array;
	 * este método normaliza os dois casos.
	 */
	private String resolverIdTabela(String param) {
		String id = Core.getParam(param);

		if (Core.isNull(id)) {
			String[] ids = Core.getParamArray(param);
			if (ids != null && ids.length > 0) {
				id = ids[0];
			}
		}

		return (Core.isNotNull(id) && !id.trim().isEmpty()) ? id.trim() : null;
	}

	/**
	 * Converte um objeto {@link Usuarios} em uma linha da tabela da view.
	 */
	private Cadastro_de_usuario.Table_1 toLinhaTabela(Usuarios u) {
		Cadastro_de_usuario.Table_1 linha = new Cadastro_de_usuario.Table_1();
		int status = Boolean.TRUE.equals(u.getAtivo()) ? 1 : 0;

		linha.setNome(u.getNome());
		linha.setEmail(u.getEmail());
		linha.setPerfil(u.getPerfil());
		linha.setAtivo_usuario(status);
		linha.setAtivo_usuario_check(status);
		linha.setId_tbl_usuario(u.getId().toString());

		return linha;
	}

	/**
	 * Preenche {@code model} com os dados do usuário identificado por {@code idStr}.
	 * Retorna {@code true} se encontrou o registro; {@code false} caso contrário.
	 */
	private boolean preencherFormularioEdicao(Cadastro_de_usuario model, String idStr) {
		if (idStr == null) return false;

		Usuarios u = new Usuarios().findOne(Core.toInt(idStr));
		if (u == null) return false;

		model.setNome_de_usuario(u.getNome());
		model.setEmail_de_usuario(u.getEmail());
		model.setPerfil_de_usuario(u.getPerfil());
		model.setAtivo(Boolean.TRUE.equals(u.getAtivo()) ? 1 : 0);
		return true;
	}

	/** Redireciona para a action index do módulo, sem queryString extra. */
	private Response redirectIndex() throws IOException {
		return this.redirect(APP, PAGE, "index");
	}

	/** Redireciona para a action index do módulo, com queryString atual. */
	private Response forwardIndex() throws IOException {
		return this.forward(APP, PAGE, "index", this.queryString());
	}

	// =========================================================================
	// INDEX
	// =========================================================================
	public Response actionIndex()
			throws IOException, IllegalArgumentException, IllegalAccessException {

		var model = new Cadastro_de_usuario();
		model.load();

		var view = new Cadastro_de_usuarioView();
		view.id_tbl_usuario.setParam(true);

		/*----#start-code(index)----*/

		// 1. Opções do RadioList "Ativo"
		view.ativo.setQuery(Core.query(null, QUERY_ATIVO));

		// 2. Carregar lista de usuários para a tabela
		List<Usuarios> listaDb = new Usuarios().find().all();
		List<Cadastro_de_usuario.Table_1> listaTabela = new ArrayList<>();

		if (listaDb != null) {
			for (Usuarios u : listaDb) {
				listaTabela.add(toLinhaTabela(u));
			}
		}
		model.setTable_1(listaTabela);

		// 3. Preencher formulário quando um usuário é selecionado para edição.
		//    Prioridade: parâmetro da tabela → fallback do redirect.
		String idEdit = resolverIdTabela(PARAM_ID);
		if (idEdit == null) {
			idEdit = Core.getParam(PARAM_EDIT);
		}

		if (idEdit != null) {
			if (preencherFormularioEdicao(model, idEdit)) {
				this.addQueryString(PARAM_EDIT, idEdit);
			} else {
				Core.setMessageError("Usuário não encontrado para edição.");
			}
		}

		/*----#end-code----*/
		view.setModel(model);
		return this.renderView(view);
	}

	// =========================================================================
	// GUARDAR — INSERT ou UPDATE
	// =========================================================================
	@SuppressWarnings("unused")
	public Response actionGuardar()
			throws IOException, IllegalArgumentException, IllegalAccessException {

		var model = new Cadastro_de_usuario();
		model.load();

		/*----#start-code(guardar)----*/

		if (!model.validate()) {
			Core.setMessageError("Por favor, preencha os campos obrigatórios corretamente.");
			return forwardIndex();
		}

		String idEdit = Core.getParam(PARAM_EDIT);
		boolean isUpdate = Core.isNotNull(idEdit);

		try {
			Usuarios usuario = isUpdate
					? buscarUsuarioParaEdicao(idEdit)
					: new Usuarios();

			if (usuario == null) {
				// buscarUsuarioParaEdicao já definiu a mensagem de erro
				return forwardIndex();
			}

			aplicarDadosDoModel(usuario, model);

			usuario = isUpdate ? usuario.update() : usuario.insert();

			if (usuario != null && !usuario.hasError()) {
				Core.setMessageSuccess("Usuário gravado com sucesso!");
				return redirectIndex();
			}

			String detalheErro = (usuario != null)
					? usuario.getError().toString()
					: "Erro desconhecido";
			Core.setMessageError("Erro ao persistir dados: " + detalheErro);

		} catch (Exception e) {
			Core.setMessageError("Erro técnico ao guardar: " + e.getMessage());
		}

		/*----#end-code----*/
		return forwardIndex();
	}

	// =========================================================================
	// EDITAR — redireciona para index com o ID na queryString
	// =========================================================================
	@SuppressWarnings("unused")
	public Response actionEditar() throws IOException {

		/*----#start-code(editar)----*/

		String id = resolverIdTabela(PARAM_ID);

		if (id == null) {
			Core.setMessageError("Nenhum usuário selecionado para editar.");
			return redirectIndex();
		}

		this.addQueryString(PARAM_ID, id);
		return this.redirect(APP, PAGE, "index", this.queryString());

		/*----#end-code----*/
	}

	// =========================================================================
	// ELIMINAR
	// =========================================================================
	@SuppressWarnings("unused")
	public Response actionEliminar() throws IOException {

		/*----#start-code(eliminar)----*/

		String id = resolverIdTabela(PARAM_ID);

		if (id == null) {
			Core.setMessageError("Nenhum usuário selecionado para eliminar.");
			return redirectIndex();
		}

		Integer idInt = Core.toInt(id);

		if (idInt == null || idInt <= 0) {
			Core.setMessageError("ID inválido para eliminação.");
			return redirectIndex();
		}

		try {
			Usuarios u = new Usuarios().findOne(idInt);

			if (u == null) {
				Core.setMessageError("Usuário não encontrado ou já foi eliminado.");
				return redirectIndex();
			}

			if (u.delete()) {
				Core.setMessageSuccess("Usuário eliminado com sucesso.");
			} else {
				Core.setMessageError(
						"Não foi possível eliminar o usuário. Verifique se há dependências.");
			}

		} catch (Exception e) {
			Core.setMessageError("Erro técnico ao eliminar: " + e.getMessage());
		}

		/*----#end-code----*/
		return redirectIndex();
	}

	// ─────────────────────────────────────────────────────────────────────────
	// HELPERS PRIVADOS DO actionGuardar
	// ─────────────────────────────────────────────────────────────────────────

	/** Busca o usuário para edição, definindo mensagem de erro se não encontrar. */
	private Usuarios buscarUsuarioParaEdicao(String idEdit) {
		Usuarios u = new Usuarios().findOne(Core.toInt(idEdit));
		if (u == null) {
			Core.setMessageError("Usuário não encontrado para edição.");
		}
		return u;
	}

	/** Copia os dados do model para a entidade {@link Usuarios}. */
	private void aplicarDadosDoModel(Usuarios usuario, Cadastro_de_usuario model) {
		usuario.setNome(model.getNome_de_usuario());
		usuario.setEmail(model.getEmail_de_usuario());
		usuario.setPerfil(model.getPerfil_de_usuario());
		usuario.setAtivo(Integer.valueOf(1).equals(model.getAtivo()));

		if (Core.isNotNull(model.getSenha_de_usuario())) {
			usuario.setSenha(model.getSenha_de_usuario());
		}
	}

	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
	/*----#start-code(custom_actions)----*/
	/*----#end-code----*/
}