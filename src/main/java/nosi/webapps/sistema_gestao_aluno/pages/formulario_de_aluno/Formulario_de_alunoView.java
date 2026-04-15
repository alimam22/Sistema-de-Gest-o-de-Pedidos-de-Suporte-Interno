package nosi.webapps.sistema_gestao_aluno.pages.formulario_de_aluno;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Formulario_de_alunoView extends View {

	public Field nome;
	public Field apelido;
	public Field idade;
	public Field curso;
	public Field email;
	public IGRPForm form_1;

	public IGRPButton btn_guardar;

	public Formulario_de_alunoView(){

		this.setPageTitle("Formulário de aluno");
			
		form_1 = new IGRPForm("form_1","Registro de Aluno");

		nome = new TextField(model,"nome");
		nome.setLabel(gt("Nome"));
		nome.propertie().add("name","p_nome").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		apelido = new TextField(model,"apelido");
		apelido.setLabel(gt("Apelido"));
		apelido.propertie().add("name","p_apelido").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		idade = new NumberField(model,"idade");
		idade.setLabel(gt("Idade"));
		idade.propertie().add("name","p_idade").add("type","number").add("min","").add("max","").add("calculation","false").add("mathcal","").add("numberformat","").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("placeholder",gt("")).add("desclabel","false").add("java-type","").add("tooltip","false").add("disable_copy_paste","false");
		
		curso = new TextField(model,"curso");
		curso.setLabel(gt("Curso"));
		curso.propertie().add("name","p_curso").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		
		email = new TextField(model,"email");
		email.setLabel(gt("Email"));
		email.propertie().add("name","p_email").add("type","text").add("maxlength","250").add("required","false").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");
		


		btn_guardar = new IGRPButton("Guardar","sistema_gestao_aluno","Formulario_de_aluno","guardar","submit_form","primary|fa-save","","");
		btn_guardar.propertie.add("id","button_ead5_d09c").add("type","form").add("class","primary").add("rel","guardar").add("refresh_components","");

		
	}
		
	@Override
	public void render(){
		
		form_1.addField(nome);
		form_1.addField(apelido);
		form_1.addField(idade);
		form_1.addField(curso);
		form_1.addField(email);

		form_1.addButton(btn_guardar);
		this.addToPage(form_1);
	}
		
	@Override
	public void setModel(Model model) {
		
		nome.setValue(model);
		apelido.setValue(model);
		idade.setValue(model);
		curso.setValue(model);
		email.setValue(model);	

		}
}