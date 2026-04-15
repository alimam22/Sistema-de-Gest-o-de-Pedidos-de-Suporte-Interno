package nosi.webapps.sistema_gestao_aluno.pages.formulario_de_aluno;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.validator.constraints.*;

public class Formulario_de_aluno extends Model {

	@RParam(rParamName = "p_id")
	private Integer id;

	@NotNull()
	@RParam(rParamName = "p_nome")
	private String nome;

	@NotNull()
	@RParam(rParamName = "p_apelido")
	private String apelido;

	@NotNull()
	@RParam(rParamName = "p_idade")
	private Integer idade;

	@NotNull()
	@RParam(rParamName = "p_curso")
	private String curso;

	@RParam(rParamName = "p_email")
	private String email;

	public void setId(Integer id) { this.id = id; }
	public Integer getId() { return this.id; }

	public void setNome(String nome) { this.nome = nome; }
	public String getNome() { return this.nome; }

	public void setApelido(String apelido) { this.apelido = apelido; }
	public String getApelido() { return this.apelido; }

	public void setIdade(Integer idade) { this.idade = idade; }
	public Integer getIdade() { return this.idade; }

	public void setCurso(String curso) { this.curso = curso; }
	public String getCurso() { return this.curso; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }
}