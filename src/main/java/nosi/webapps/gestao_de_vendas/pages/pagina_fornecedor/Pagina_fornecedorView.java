package nosi.webapps.gestao_de_vendas.pages.pagina_fornecedor;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Pagina_fornecedorView extends View {

	public Field nome;
	public Field telefone;
	public Field email;
	public Field endereco;
	public Field id_tbl_fornecedor;
	public IGRPTable table_1;

	public IGRPToolsBar toolsbar_1;
	public IGRPButton btn_novo_fornecedor;
	public IGRPButton btn_editar;
	public IGRPButton btn_eliminar;

	public Pagina_fornecedorView() {

		this.setPageTitle("Pagina Fornecedor");

		table_1 = new IGRPTable("table_1", "Lista de Fornecedores");

		nome = new TextField(model, "nome");
		nome.setLabel(gt("Nome"));
		nome.propertie().add("name", "p_nome").add("type", "text").add("maxlength", "30").add("showLabel", "true").add("group_in", "");

		telefone = new NumberField(model, "telefone");
		telefone.setLabel(gt("Telefone"));
		telefone.propertie().add("name", "p_telefone").add("type", "number").add("min", "").add("max", "").add("calculation", "false").add("mathcal", "").add("numberformat", "").add("maxlength", "30").add("showLabel", "true").add("total_footer", "false").add("group_in", "").add("java-type", "integer");

		email = new TextField(model, "email");
		email.setLabel(gt("Email"));
		email.propertie().add("name", "p_email").add("type", "text").add("maxlength", "30").add("showLabel", "true").add("group_in", "");

		endereco = new TextField(model, "endereco");
		endereco.setLabel(gt("Endereco"));
		endereco.propertie().add("name", "p_endereco").add("type", "text").add("maxlength", "30").add("showLabel", "true").add("group_in", "");

		id_tbl_fornecedor = new HiddenField(model, "id_tbl_fornecedor");
		id_tbl_fornecedor.setLabel(gt(""));
		id_tbl_fornecedor.propertie().add("name", "p_id_tbl_fornecedor").add("type", "hidden").add("maxlength", "30").add("showLabel", "true").add("group_in", "").add("java-type", "integer").add("tag", "id_tbl_fornecedor");

		toolsbar_1 = new IGRPToolsBar("toolsbar_1");

		btn_novo_fornecedor = new IGRPButton("Novo Fornecedor", "gestao_de_vendas", "Pagina_fornecedor", "novo_fornecedor", "submit_form", "success|fa-plus-square", "", "");
		btn_novo_fornecedor.propertie.add("type", "specific").add("rel", "novo_fornecedor").add("refresh_components", "");

		// submit_row — envia os campos da linha correctamente
		btn_editar = new IGRPButton("Editar", "gestao_de_vendas", "Pagina_fornecedor", "editar", "submit_row", "warning|fa-edit", "", "");
		btn_editar.propertie.add("id", "button_2a63_b924").add("type", "specific").add("class", "warning").add("rel", "editar");

		// confirm_row — confirma e envia os campos da linha correctamente
		btn_eliminar = new IGRPButton("Eliminar", "gestao_de_vendas", "Pagina_fornecedor", "eliminar", "confirm_row", "danger|fa-trash", "", "");
		btn_eliminar.propertie.add("id", "button_dc01_82a0").add("type", "specific").add("class", "danger").add("rel", "eliminar").add("labelConfirm", "Deseja realmente realizar esta operação?");
	}

	@Override
	public void render() {
		table_1.addField(nome);
		table_1.addField(telefone);
		table_1.addField(email);
		table_1.addField(endereco);
		table_1.addField(id_tbl_fornecedor);

		toolsbar_1.addButton(btn_novo_fornecedor);
		table_1.addButton(btn_editar);
		table_1.addButton(btn_eliminar);
		this.addToPage(table_1);
		this.addToPage(toolsbar_1);
	}

	@Override
	public void setModel(Model model) {
		nome.setValue(model);
		telefone.setValue(model);
		email.setValue(model);
		endereco.setValue(model);
		id_tbl_fornecedor.setValue(model);

		table_1.loadModel(((Pagina_fornecedor) model).getTable_1());
	}
}