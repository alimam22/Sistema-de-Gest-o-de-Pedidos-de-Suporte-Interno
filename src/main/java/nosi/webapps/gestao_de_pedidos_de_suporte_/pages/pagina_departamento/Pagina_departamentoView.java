package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_departamento;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Pagina_departamentoView extends View {

	public Field nome;
	public Field siglas;
	public Field activos;
	public Field data;

	// Campo Hidden para o Formulário (Crucial para a Edição)
	public Field p_id;

	public Field nome_departamento;
	public Field siglas_departamento;
	public Field activo_departamento;
	public Field activo_departamento_check;
	public Field data_departamento;
	public Field tbl_departamento_id;

	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_adicionar;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Pagina_departamentoView(){

		this.setPageTitle("Pagina departamento");

		form_1 = new IGRPForm("form_1","Formulário Departamento");
		table_1 = new IGRPTable("table_1","Lista de departamento");

		// --- CAMPOS DO FORMULÁRIO ---

		// Campo ID Oculto no Formulário
		p_id = new HiddenField(model,"p_id");
		p_id.propertie().add("name","p_id").add("type","hidden").add("maxlength","30").add("java-type","Integer").add("tag","p_id");

		nome = new TextField(model,"nome");
		nome.setLabel(gt("Nome"));
		nome.propertie().add("name","p_nome").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");

		siglas = new TextField(model,"siglas");
		siglas.setLabel(gt("Siglas"));
		siglas.propertie().add("name","p_siglas").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");

		activos = new RadioListField(model,"activos");
		activos.setLabel(gt("Activos"));
		activos.propertie().add("name","p_activos").add("type","radiolist").add("domain","").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("child_size","12").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");

		data = new TextField(model,"data");
		data.setLabel(gt("Data"));
		data.propertie().add("name","p_data").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","date").add("tooltip","false").add("disable_copy_paste","false");

		// --- CAMPOS DA TABELA ---

		nome_departamento = new TextField(model,"nome_departamento");
		nome_departamento.setLabel(gt("Nome"));
		nome_departamento.propertie().add("name","p_nome_departamento").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		siglas_departamento = new TextField(model,"siglas_departamento");
		siglas_departamento.setLabel(gt("Siglas"));
		siglas_departamento.propertie().add("name","p_siglas_departamento").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		activo_departamento = new TextField(model,"activo_departamento");
		activo_departamento.setLabel(gt("Activo"));
		activo_departamento.propertie().add("name","p_activo_departamento").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		activo_departamento_check = new RadioField(model,"activo_departamento_check");
		activo_departamento_check.propertie().add("name","p_activo_departamento").add("type","radio").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("check","true").add("desc","true");

		data_departamento = new TextField(model,"data_departamento");
		data_departamento.setLabel(gt("Data"));
		data_departamento.propertie().add("name","p_data_departamento").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		tbl_departamento_id = new HiddenField(model,"tbl_departamento_id");
		tbl_departamento_id.setLabel(gt(""));
		tbl_departamento_id.propertie().add("name","p_tbl_departamento_id").add("type","hidden").add("maxlength","30").add("showLabel","true").add("group_in","").add("java-type","").add("tag","tbl_departamento_id");


		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_adicionar = new IGRPButton("Adicionar","gestao_de_pedidos_de_suporte_","Pagina_departamento","adicionar","submit_form","success|fa-plus-square","","");
		btn_adicionar.propertie.add("id","button_5e4a_44f7").add("type","form").add("class","success").add("rel","adicionar").add("refresh_components","");

		btn_editar = new IGRPButton("Editar","gestao_de_pedidos_de_suporte_","Pagina_departamento","editar","mpsubmit|refresh","warning|fa-edit","","");
		btn_editar.propertie.add("id","button_bebf_aef0").add("type","specific").add("class","warning").add("rel","editar").add("refresh_components","");

		btn_eliminar = new IGRPButton("Eliminar","gestao_de_pedidos_de_suporte_","Pagina_departamento","eliminar","confirm","danger|fa-trash","","");
		btn_eliminar.propertie.add("id","button_323f_7a1d").add("type","specific").add("class","danger").add("rel","eliminar").add("refresh_components","").add("labelConfirm","Deseja realmente realizar esta operação?");
	}

	@Override
	public void render(){

		// Importante: Adicionando o p_id ao formulário
		form_1.addField(p_id);
		form_1.addField(nome);
		form_1.addField(siglas);
		form_1.addField(activos);
		form_1.addField(data);

		table_1.addField(nome_departamento);
		table_1.addField(siglas_departamento);
		table_1.addField(activo_departamento);
		table_1.addField(activo_departamento_check);
		table_1.addField(data_departamento);
		table_1.addField(tbl_departamento_id);

		form_1.addButton(btn_adicionar);
		table_1.addButton(btn_editar);
		table_1.addButton(btn_eliminar);

		this.addToPage(form_1);
		this.addToPage(table_1);
		this.addToPage(toolsbar_1);
	}

	@Override
	public void setModel(Model model) {

		// Setando o valor do campo hidden do formulário
		p_id.setValue(model);

		nome.setValue(model);
		siglas.setValue(model);
		activos.setValue(model);
		data.setValue(model);
		nome_departamento.setValue(model);
		siglas_departamento.setValue(model);
		activo_departamento.setValue(model);
		data_departamento.setValue(model);
		tbl_departamento_id.setValue(model);

		table_1.loadModel(((Pagina_departamento) model).getTable_1());
	}
}