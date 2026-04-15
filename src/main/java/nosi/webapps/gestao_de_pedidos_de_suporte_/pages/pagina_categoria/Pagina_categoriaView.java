package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_categoria;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Pagina_categoriaView extends View {

	public Field nome;
	public Field descricao;
	public Field activo;
	public Field data;

	// NOVO: Campo Hidden para o formulário (o segredo da edição)
	public Field p_id;

	public Field nome_categoria;
	public Field descricao_categoria;
	public Field activo_categoria;
	public Field data_categoria;
	public Field tbl_categoria_id;

	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_adicionar;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Pagina_categoriaView(){

		this.setPageTitle("Pagina categoria");

		form_1 = new IGRPForm("form_1","Formulário de categoria");
		table_1 = new IGRPTable("table_1","Lista de categoria");

		// --- CAMPOS DO FORMULÁRIO ---

		// Instanciando o campo hidden do formulário
		p_id = new HiddenField(model,"p_id");
		p_id.propertie().add("name","p_id").add("type","hidden").add("maxlength","30").add("java-type","Integer").add("tag","p_id");

		nome = new TextField(model,"nome");
		nome.setLabel(gt("Nome"));
		nome.propertie().add("name","p_nome").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");

		descricao = new TextField(model,"descricao");
		descricao.setLabel(gt("Descrição"));
		descricao.propertie().add("name","p_descricao").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");

		activo = new RadioListField(model,"activo");
		activo.setLabel(gt("Activo"));
		activo.propertie().add("name","p_activo").add("type","radiolist").add("domain","").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("child_size","12").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");

		data = new TextField(model,"data");
		data.setLabel(gt("Data"));
		data.propertie().add("name","p_data").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");

		// --- CAMPOS DA TABELA ---

		nome_categoria = new TextField(model,"nome_categoria");
		nome_categoria.setLabel(gt("Nome"));
		nome_categoria.propertie().add("name","p_nome_categoria").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		descricao_categoria = new TextField(model,"descricao_categoria");
		descricao_categoria.setLabel(gt("Descrição"));
		descricao_categoria.propertie().add("name","p_descricao_categoria").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		activo_categoria = new TextField(model,"activo_categoria");
		activo_categoria.setLabel(gt("Activo"));
		activo_categoria.propertie().add("name","p_activo_categoria").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		data_categoria = new TextField(model,"data_categoria");
		data_categoria.setLabel(gt("Data"));
		data_categoria.propertie().add("name","p_data_categoria").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		tbl_categoria_id = new HiddenField(model,"tbl_categoria_id");
		tbl_categoria_id.setLabel(gt(""));
		tbl_categoria_id.propertie().add("name","p_tbl_categoria_id").add("type","hidden").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("tag","tbl_categoria_id");


		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_adicionar = new IGRPButton("Adicionar","gestao_de_pedidos_de_suporte_","Pagina_categoria","adicionar","submit_form","success|fa-plus-square","","");
		btn_adicionar.propertie.add("id","button_ee6f_09c7").add("type","form").add("class","success").add("rel","adicionar").add("refresh_components","");

		btn_editar = new IGRPButton("Editar","gestao_de_pedidos_de_suporte_","Pagina_categoria","editar","mpsubmit|refresh","warning|fa-edit","","");
		btn_editar.propertie.add("id","button_ca81_d202").add("type","specific").add("class","warning").add("rel","editar").add("refresh_components","");

		btn_eliminar = new IGRPButton("Eliminar","gestao_de_pedidos_de_suporte_","Pagina_categoria","eliminar","submit_form","danger|fa-trash","","");
		btn_eliminar.propertie.add("id","button_4519_8166").add("type","specific").add("class","danger").add("rel","eliminar").add("refresh_components","").add("labelConfirm","Deseja realmente realizar esta operação?");
	}

	@Override
	public void render(){

		// Adicionando o p_id ao formulário
		form_1.addField(p_id);

		form_1.addField(nome);
		form_1.addField(descricao);
		form_1.addField(activo);
		// form_1.addField(data);  <-- removido do formulário

		table_1.addField(nome_categoria);
		table_1.addField(descricao_categoria);
		table_1.addField(activo_categoria);
		table_1.addField(data_categoria);
		table_1.addField(tbl_categoria_id);

		form_1.addButton(btn_adicionar);
		table_1.addButton(btn_editar);
		table_1.addButton(btn_eliminar);

		this.addToPage(form_1);
		this.addToPage(table_1);
		this.addToPage(toolsbar_1);
	}

	@Override
	public void setModel(Model model) {

		// Setando o valor do p_id vindo do model
		p_id.setValue(model);

		nome.setValue(model);
		descricao.setValue(model);
		activo.setValue(model);
		data.setValue(model);
		nome_categoria.setValue(model);
		descricao_categoria.setValue(model);
		activo_categoria.setValue(model);
		data_categoria.setValue(model);
		tbl_categoria_id.setValue(model);

		table_1.loadModel(((Pagina_categoria) model).getTable_1());
	}
}