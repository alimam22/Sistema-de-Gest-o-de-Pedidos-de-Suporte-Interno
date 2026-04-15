package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.detalheshistorico;

import nosi.core.webapp.Controller;
import java.io.IOException;
import nosi.core.webapp.Core;
import nosi.core.webapp.Response;
/* Start-Code-Block (import) */
/* End-Code-Block */
/*----#start-code(packages_import)----*/

import nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper.PageHelperDetalheHistorico;
import nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte.PedidoSuporteService;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoComentario;

/*----#end-code----*/

public class DetalheshistoricoController extends Controller {
	public Response actionIndex() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Detalheshistorico();
		model.load();
		var view = new DetalheshistoricoView();
		view.historico_de_comentario.setParam(true);
		view.tbl_comentario_id.setParam(true);
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		model.loadTable_1(Core.query(null,"SELECT 'Iste rem stract lorem labore' as datahora,'Officia ipsum amet sed labore' as tecnico,'Laudantium dolor rem voluptate' as estado_anterior,'Amet adipiscing officia lorem' as novo_estado,'Omnis totam accusantium consec' as observacao,'hidden-9b80_34d7' as tbi_historico_id "));
		model.loadTable_2(Core.query(null,"SELECT 'Laudantium dolor amet sit sit' as historico_de_comentario,'' as hidden "));
		  ----#gen-example */
		/* Start-Code-Block (index) *//* End-Code-Block (index) */
		/*----#start-code(index)----*/
		// 1. Instancia o Helper
		PageHelperDetalheHistorico helper = new PageHelperDetalheHistorico();

		// 2. Carrega detalhes com regras de permissão integradas
		helper.carregarDetalhes(model, view);
		view.setModel(model);
		return this.renderView(view);
	}
	
	public Response actionAssumir_pedido() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Detalheshistorico();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_tbi_historico_id",Core.getParam("p_tbi_historico_id"));
		  this.addQueryString("p_tbl_comentario_id",Core.getParam("p_tbl_comentario_id"));
		  return this.forward("gestao_de_pedidos_de_suporte_","Criar_pedido","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (assumir_pedido)  *//* End-Code-Block  */
		/*----#start-code(assumir_pedido)----*/



		String idPedido = Core.getParam("p_form_1_hidden_1");

//		try {
//			PedidoSuporteService service = new PedidoSuporteService();
//			// O técnico assume o pedido (ele atribui a si próprio)
//			service.atribuirTecnico(
//					Core.toInt(idPedido),
//					Core.getCurrentUser().getId(),
//					Core.getCurrentUser().getId(),
//					Perfil.getNome()
//			);
//			Core.setMessageSuccess("Agora você é o responsável por este pedido.");
//		} catch (Exception e) {
//			Core.setMessageError(e.getMessage());
//		}

		this.addQueryString("p_pedido_id", idPedido);
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Detalheshistorico","index", this.queryString());	
	}
	
	public Response actionFinalizar_pedido() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Detalheshistorico();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_tbi_historico_id",Core.getParam("p_tbi_historico_id"));
		  this.addQueryString("p_tbl_comentario_id",Core.getParam("p_tbl_comentario_id"));
		  return this.forward("gestao_de_pedidos_de_suporte_","Criar_pedido","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (finalizar_pedido)  *//* End-Code-Block  */
		/*----#start-code(finalizar_pedido)----*/



		try {
			PageHelperDetalheHistorico helper = new PageHelperDetalheHistorico();
			helper.finalizarPedido(model);
			Core.setMessageSuccess("Pedido finalizado com sucesso!");
			return this.redirect("gestao_de_pedidos_de_suporte_", "Pagina_de_listagem_de_pedidos", "index");
		} catch (Exception e) {
			Core.setMessageError("Não foi possível finalizar: " + e.getMessage());
		}

		this.addQueryString("p_pedido_id", model.getForm_1_hidden_1());
		return this.redirect("gestao_de_pedidos_de_suporte_", "Detalheshistorico", "index", this.queryString());
		
		/*----#end-code----*/
		//return this.redirect("gestao_de_pedidos_de_suporte_","Criar_pedido","index", this.queryString());
	}
	
	public Response actionPublicar_comentario() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Detalheshistorico();
		model.load();
		/*----#start-code(publicar_comentario)----*/

		// Todos os perfis (GESTOR, TÉCNICO, SOLICITANTE) podem comentar
		String comentario = Core.getParam("p_adicionar_comentario");
		String idPedido = Core.getParam("p_form_1_hidden_1");
		if (Core.isNull(idPedido)) idPedido = Core.getParam("p_pedido_id");

		if (Core.isNull(comentario)) {
			Core.setMessageError("O comentário não pode estar vazio.");
			this.addQueryString("p_pedido_id", idPedido);
			return this.redirect("gestao_de_pedidos_de_suporte_", "Detalheshistorico", "index", this.queryString());
		}

		try {
			PageHelperDetalheHistorico helper = new PageHelperDetalheHistorico();
			helper.adicionarComentario(Core.toInt(idPedido), comentario);
			Core.setMessageSuccess("Comentário adicionado com sucesso!");
		} catch (Exception e) {
			Core.setMessageError("Erro ao adicionar comentário: " + e.getMessage());
		}

		this.addQueryString("p_pedido_id", idPedido);
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Detalheshistorico","index", this.queryString());
	}
	
	public Response actionEditar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Detalheshistorico();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_tbi_historico_id",Core.getParam("p_tbi_historico_id"));
		  this.addQueryString("p_tbl_comentario_id",Core.getParam("p_tbl_comentario_id"));
		  return this.forward("gestao_de_pedidos_de_suporte_","Detalheshistorico","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (editar)  *//* End-Code-Block  */
		/*----#start-code(editar)----*/

		// CRUD: Editar comentário
		// Regras: Utilizador pode editar os seus próprios comentários, Gestor pode editar todos
		String idComentario = Core.getParam("p_tbl_comentario_id");
		String novoTexto = Core.getParam("p_adicionar_comentario");
		String idPedido = Core.getParam("p_form_1_hidden_1");
		if (Core.isNull(idPedido)) idPedido = Core.getParam("p_pedido_id");

		if (Core.isNull(idComentario)) {
			Core.setMessageError("ID do comentário não especificado.");
			this.addQueryString("p_pedido_id", idPedido);
			return this.redirect("gestao_de_pedidos_de_suporte_", "Detalheshistorico", "index", this.queryString());
		}

		if (Core.isNull(novoTexto)) {
			Core.setMessageError("O texto do comentário não pode estar vazio.");
			this.addQueryString("p_pedido_id", idPedido);
			return this.redirect("gestao_de_pedidos_de_suporte_", "Detalheshistorico", "index", this.queryString());
		}

		try {
			PageHelperDetalheHistorico helper = new PageHelperDetalheHistorico();
			helper.editarComentario(Core.toInt(idComentario), novoTexto);
			Core.setMessageSuccess("Comentário atualizado com sucesso!");
		} catch (SecurityException e) {
			Core.setMessageError(e.getMessage());
		} catch (Exception e) {
			Core.setMessageError("Erro ao editar comentário: " + e.getMessage());
		}

		this.addQueryString("p_pedido_id", idPedido);
		
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Detalheshistorico","index", this.queryString());	
	}
	
	public Response actionEliminar() throws IOException, IllegalArgumentException, IllegalAccessException{
		var model = new Detalheshistorico();
		model.load();
		/*----#gen-example
		  EXAMPLES COPY/PASTE:
		  INFO: Core.query(null,... change 'null' to your db connection name, added in Application Builder.
		  this.addQueryString("p_id","12"); //to send a query string in the URL
		  this.addQueryString("p_tbi_historico_id",Core.getParam("p_tbi_historico_id"));
		  this.addQueryString("p_tbl_comentario_id",Core.getParam("p_tbl_comentario_id"));
		  return this.forward("gestao_de_pedidos_de_suporte_","Criar_pedido","index",this.queryString()); //if submit, loads the values
		  Use model.validate() to validate your model
		  ----#gen-example */
		/* Start-Code-Block (eliminar)  *//* End-Code-Block  */
		/*----#start-code(eliminar)----*/

		// VALIDAÇÃO DE PERMISSÃO: Apenas GESTOR pode eliminar
		if (!isGestor()) {
			Core.setMessageError("Apenas o Gestor pode eliminar comentários.");
			String idPedido = Core.getParam("p_tbl_listapedido_id");
			if (Core.isNull(idPedido)) idPedido = Core.getParam("p_pedido_id");
			this.addQueryString("p_tbl_listapedido_id", idPedido);
			return this.redirect("gestao_de_pedidos_de_suporte_", "Detalheshistorico", "index", this.queryString());
		}

		String idComentario = Core.getParam("p_tbl_comentario_id");

		PageHelperDetalheHistorico helper = new PageHelperDetalheHistorico();
		helper.eliminarComentario(idComentario);

		// Recuperar o ID do pedido
		String idPedido = Core.getParam("p_tbl_listapedido_id");
		if (Core.isNull(idPedido)) idPedido = Core.getParam("p_pedido_id");
		this.addQueryString("p_tbl_listapedido_id", idPedido);
		
		/*----#end-code----*/
		return this.redirect("gestao_de_pedidos_de_suporte_","Detalheshistorico","index", this.queryString());	
	}
	/* Start-Code-Block (custom-actions)  *//* End-Code-Block  */
/*----#start-code(custom_actions)----*/

	/**
	 * Verifica se o utilizador atual é Gestor
	 */
	private boolean isGestor() {
		String perfilCodigo = Core.getCurrentProfileCode();
		return perfilCodigo != null && perfilCodigo.toLowerCase().contains("gestor");
	}

/*----#end-code----*/
}
