package nosi.webapps.gestao_de_vendas.pages.cadastro_de_usuario;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Cadastro_de_usuarioView extends View {

	public Field nome_de_usuario;
	public Field ativo;
	public Field perfil_de_usuario;
	public Field email_de_usuario;
	public Field senha_de_usuario;
	public Field nome;
	public Field email;
	public Field perfil;
	public Field ativo_usuario;
	public Field id_tbl_usuario;

	public IGRPForm form_1;
	public IGRPTable table_1;
	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_guardar;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Cadastro_de_usuarioView(){

		this.setPageTitle("Gestão de Usuários");

		form_1 = new IGRPForm("form_1","Formulário de Cadastro");
		table_1 = new IGRPTable("table_1","Lista de Usuários");
		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		nome_de_usuario = new TextField(model,"nome_de_usuario");
		nome_de_usuario.setLabel(gt("Nome completo"));
		nome_de_usuario.propertie().add("name","p_nome_de_usuario")
				.add("type","text")
				.add("maxlength","250")
				.add("required","true");

		ativo = new RadioListField(model,"ativo");
		ativo.setLabel(gt("Status"));
		ativo.propertie().add("name","p_ativo")
				.add("type","radiolist")
				.add("required","true")
				.add("java-type","int");

		perfil_de_usuario = new TextField(model,"perfil_de_usuario");
		perfil_de_usuario.setLabel(gt("Perfil"));
		perfil_de_usuario.propertie().add("name","p_perfil_de_usuario")
				.add("type","text")
				.add("maxlength","250")
				.add("required","true");

		email_de_usuario = new TextField(model,"email_de_usuario");
		email_de_usuario.setLabel(gt("E-mail (Login)"));
		email_de_usuario.propertie().add("name","p_email_de_usuario")
				.add("type","text")
				.add("maxlength","250")
				.add("required","true");

		senha_de_usuario = new TextField(model,"senha_de_usuario");
		senha_de_usuario.setLabel(gt("Senha"));
		senha_de_usuario.propertie().add("name","p_senha_de_usuario")
				.add("type","password")  // ← Corrigido: era "text", agora "password"
				.add("maxlength","250")
				.add("required","true");

		// Campos da Tabela
		nome = new TextField(model,"nome");
		nome.setLabel(gt("Nome"));

		email = new TextField(model,"email");
		email.setLabel(gt("Email"));

		perfil = new PlainTextField(model,"perfil");
		perfil.setLabel(gt("Perfil"));

		ativo_usuario = new CheckBoxField(model,"ativo_usuario");
		ativo_usuario.setLabel(gt("Ativo"));
		ativo_usuario.propertie().add("name","p_ativo_usuario")
				.add("type","checkbox")
				.add("java-type","int")
				.add("check","true")
				.add("desc","true");

		id_tbl_usuario = new HiddenField(model,"id_tbl_usuario");
		id_tbl_usuario.propertie().add("name","p_id_tbl_usuario")
				.add("type","hidden");

		// ─── BOTÃO GUARDAR (fica na toolsbar — para criar novo registo) ───────────
		btn_guardar = new IGRPButton(
				"Guardar",
				"gestao_de_vendas",
				"Cadastro_de_usuario",
				"guardar",
				"submit",
				"success|fa-plus-square",
				"",
				""
		);
		btn_guardar.propertie.add("id","button_guardar")
				.add("type","specific")
				.add("rel","guardar")
				.add("refresh_components","form_1,table_1");  // ← Atualiza form e tabela após guardar

		// ─── BOTÃO EDITAR (fica na tabela — actua sobre a linha seleccionada) ─────
		btn_editar = new IGRPButton(
				"Editar",
				"gestao_de_vendas",
				"Cadastro_de_usuario",
				"editar",
				"submit",
				"warning|fa-edit",
				"",
				""
		);
		btn_editar.propertie.add("id","button_editar")
				.add("type","specific")
				.add("class","warning")
				.add("rel","editar")
				.add("refresh_components","form_1,table_1");  // ← Carrega dados no form e atualiza tabela

		// ─── BOTÃO ELIMINAR (fica na tabela — actua sobre a linha seleccionada) ───
		btn_eliminar = new IGRPButton(
				"Eliminar",
				"gestao_de_vendas",
				"Cadastro_de_usuario",
				"eliminar",
				"submit",              // ← Corrigido: "confirm" impede renderização na tabela; usar "submit"
				"danger|fa-trash",
				"",
				""
		);
		btn_eliminar.propertie.add("id","button_eliminar")
				.add("type","specific")
				.add("class","danger")
				.add("rel","eliminar")
				.add("flg_transaction","false")               // ← "false" para não bloquear a ação
				.add("row_action","true")                     // ← ESSENCIAL: activa o botão por linha
				.add("refresh_components","table_1")
				.add("labelConfirm","Deseja realmente realizar esta operação?");
	}

	@Override
	public void render(){
		// Campos do Formulário
		form_1.addField(nome_de_usuario);
		form_1.addField(email_de_usuario);
		form_1.addField(senha_de_usuario);
		form_1.addField(perfil_de_usuario);
		form_1.addField(ativo);

		// Campos da Tabela
		table_1.addField(nome);
		table_1.addField(email);
		table_1.addField(perfil);
		table_1.addField(ativo_usuario);
		table_1.addField(id_tbl_usuario);

		// ── Botões ──────────────────────────────────────────────────────────────
		// btn_guardar → toolsbar (acção global, não depende de linha seleccionada)
		toolsbar_1.addButton(btn_guardar);

		// btn_editar e btn_eliminar → tabela (acções de linha)
		table_1.addButton(btn_editar);
		table_1.addButton(btn_eliminar);

		// Adicionando componentes à página
		this.addToPage(form_1);
		this.addToPage(toolsbar_1);   // ← toolsbar logo após o form para melhor UX
		this.addToPage(table_1);
	}

	@Override
	public void setModel(Model model) {
		nome_de_usuario.setValue(model);
		ativo.setValue(model);
		perfil_de_usuario.setValue(model);
		email_de_usuario.setValue(model);
		senha_de_usuario.setValue(model);

		nome.setValue(model);
		email.setValue(model);
		perfil.setValue(model);
		ativo_usuario.setValue(model);
		id_tbl_usuario.setValue(model);

		table_1.loadModel(((Cadastro_de_usuario) model).getTable_1());
	}
}