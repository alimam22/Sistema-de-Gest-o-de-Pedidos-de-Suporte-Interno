package nosi.webapps.sistema_gestao_aluno.pages.pagina_matricula;

import nosi.core.webapp.Model;
import nosi.core.webapp.View;
import nosi.core.gui.components.*;
import nosi.core.gui.fields.*;
import static nosi.core.i18n.Translator.gt;

public class Pagina_matriculaView extends View {

	public Field aluno; // Novo campo ComboBox
	public Field ano_letivo;
	public Field curso;
	public Field estado;
	public IGRPForm form_1;

	public IGRPButton btn_salvar;

	public Pagina_matriculaView(){

		this.setPageTitle("Pagina_Matricula");

		form_1 = new IGRPForm("form_1","Matricula");

		// --- Campo Aluno (ComboBox) ---
// Dentro do seu construtor Pagina_matriculaView()

// 1. Instanciar como TextField (o Studio permite mudar o tipo nas propriedades)
		aluno = new TextField(model,"aluno");

// 2. Configurar o Label conforme a imagem
		aluno.setLabel(gt("Aluno"));

// 3. Adicionar as propriedades exatas da imagem
		aluno.propertie().add("name","p_aluno")
				.add("type","select") // TYPE: Select conforme o rodapé da imagem
				.add("multiple","false")
				.add("tags","false")
				.add("domain","") // Campo Domain vazio na imagem
				.add("maxlength","250") // Maxlength: 250 conforme imagem
				.add("required","true") // Required marcado na imagem
				.add("disabled","false")
				.add("readonly","false")
				.add("disablehtml","true")
				.add("placeholder",gt(""))
				.add("desclabel","false")
				.add("inputmask","")
				.add("tooltip","false")
				.add("disable_copy_paste","false")
				.add("java-type","String"); // Recomendado String para IDs de combo

		// --- Campo Ano Letivo ---
		ano_letivo = new TextField(model,"ano_letivo");
		ano_letivo.setLabel(gt("Ano Letivo"));
		ano_letivo.propertie().add("name","p_ano_letivo").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");

		// --- Campo Curso ---
		curso = new TextField(model,"curso");
		curso.setLabel(gt("Curso"));
		curso.propertie().add("name","p_curso").add("type","text").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("disablehtml","true").add("placeholder",gt("")).add("desclabel","false").add("inputmask","").add("tooltip","false").add("disable_copy_paste","false");

		// --- Campo Estado ---
		estado = new RadioListField(model,"estado");
		estado.setLabel(gt("Estado"));
		// Nota: Alterei java-type para String para evitar conflitos com o RadioList
		estado.propertie().add("name","p_estado").add("type","radiolist").add("domain","").add("maxlength","250").add("required","true").add("readonly","false").add("disabled","false").add("child_size","12").add("java-type","String").add("tooltip","false").add("disable_copy_paste","false");

		// --- Botão Salvar ---
		btn_salvar = new IGRPButton("Salvar","sistema_gestao_aluno","Pagina_matricula","salvar","submit_form","success|fa-save","","");
		btn_salvar.propertie.add("id","button_b12c_834a").add("type","form").add("class","success").add("rel","salvar").add("refresh_components","");
	}

	@Override
	public void render(){
		// Adicionando os campos ao formulário
		form_1.addField(aluno); // Adicionado o aluno aqui
		form_1.addField(ano_letivo);
		form_1.addField(curso);
		form_1.addField(estado);

		form_1.addButton(btn_salvar);
		this.addToPage(form_1);
	}

	@Override
	public void setModel(Model model) {
		// Fazendo o bind dos valores
		aluno.setValue(model);
		ano_letivo.setValue(model);
		curso.setValue(model);
		estado.setValue(model);
	}
}