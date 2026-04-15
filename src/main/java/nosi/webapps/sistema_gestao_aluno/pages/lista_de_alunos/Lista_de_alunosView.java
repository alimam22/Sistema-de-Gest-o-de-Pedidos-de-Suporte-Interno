package nosi.webapps.sistema_gestao_aluno.pages.lista_de_alunos;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Lista_de_alunosView extends View {

	// 1. Campo ID declarado para ser usado na tabela
	public Field p_id_aluno;
	public Field nome;
	public Field apelido;
	public Field idade;
	public Field curso;
	public Field email;
	public IGRPForm form_1;
	public IGRPTable table_1;

	public IGRPButton btn_adicionar;
	public IGRPButton btn_table_1_button_1;
	public IGRPButton btn_eliminar;

	public Lista_de_alunosView(){

		this.setPageTitle("Lista de alunos");

		form_1 = new IGRPForm("form_1","");

		table_1 = new IGRPTable("table_1","");

		// 2. Definição do campo Hidden que guardará o ID de cada aluno na linha
		p_id_aluno = new HiddenField(model,"p_id_aluno");
		p_id_aluno.propertie().add("name","p_id_aluno").add("type","hidden").add("maxlength","30").add("java-type","").add("tag","id_aluno");

		nome = new TextField(model,"nome");
		nome.setLabel(gt("Nome"));
		nome.propertie().add("name","p_nome").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		apelido = new TextField(model,"apelido");
		apelido.setLabel(gt("Apelido"));
		apelido.propertie().add("name","p_apelido").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		idade = new NumberField(model,"idade");
		idade.setLabel(gt("Idade"));
		idade.propertie().add("name","p_idade").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","30").add("showLabel","true").add("total_footer","false").add("group_in","").add("java-type","");

		curso = new TextField(model,"curso");
		curso.setLabel(gt("Curso"));
		curso.propertie().add("name","p_curso").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");

		email = new TextField(model,"email");
		email.setLabel(gt("Email"));
		email.propertie().add("name","p_email").add("type","text").add("maxlength","30").add("showLabel","true").add("group_in","");


		btn_adicionar = new IGRPButton("Adicionar","sistema_gestao_aluno","Lista_de_alunos","adicionar","submit","success|fa-address-book","","");
		btn_adicionar.propertie.add("id","button_fa4c_59a1").add("type","form").add("class","success").add("rel","adicionar").add("refresh_components","");

		// Ajustado para "Editar"
		btn_table_1_button_1 = new IGRPButton("Editar","sistema_gestao_aluno","Lista_de_alunos","table_1_button_1","mpsubmit|refresh","warning|fa-edit","","");
		btn_table_1_button_1.propertie.add("id","button_4017_5f1d").add("type","specific").add("class","warning").add("rel","table_1_button_1").add("refresh_components","");

		// Botão Eliminar com target "confirm" para segurança
		btn_eliminar = new IGRPButton("Eliminar", "sistema_gestao_aluno", "Lista_de_alunos", "eliminar", "confirm", "danger|fa-trash", "", "");
		btn_eliminar.propertie.add("id","button_b5f1_d3b9")
				.add("type","specific")
				.add("class","danger")
				.add("rel","eliminar")
				.add("refresh_components","");

	}

	@Override
	public void render(){
		// 3. ADICIONAR O CAMPO HIDDEN À TABELA
		// Sem isso, os botões 'specific' não conseguem encontrar o ID na linha
		table_1.addField(p_id_aluno);
		table_1.addField(nome);
		table_1.addField(apelido);
		table_1.addField(idade);
		table_1.addField(curso);
		table_1.addField(email);

		form_1.addButton(btn_adicionar);
		table_1.addButton(btn_table_1_button_1);
		table_1.addButton(btn_eliminar);

		this.addToPage(form_1);
		this.addToPage(table_1);
	}

	@Override
	public void setModel(Model model) {
		// 4. Mapear o valor do ID no model
		p_id_aluno.setValue(model);
		nome.setValue(model);
		apelido.setValue(model);
		idade.setValue(model);
		curso.setValue(model);
		email.setValue(model);

		table_1.loadModel(((Lista_de_alunos) model).getTable_1());
	}
}