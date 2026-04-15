package nosi.webapps.sistema_gestao_aluno.pages.pagina_matricula;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

import nosi.core.validator.constraints.*;

public class Pagina_matricula extends Model{

	// Dentro da classe Pagina_matricula
	@RParam(rParamName = "p_aluno")
	private String aluno;

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public String getAluno() {
		return this.aluno;
	}

	@NotNull()
	@RParam(rParamName = "p_ano_letivo")
	private String ano_letivo;

	@NotNull()
	@RParam(rParamName = "p_curso")
	private String curso;

	@RParam(rParamName = "p_estado")
	private String estado;
	
	public void setAno_letivo(String ano_letivo){
		this.ano_letivo = ano_letivo;
	}
	public String getAno_letivo(){
		return this.ano_letivo;
	}
	
	public void setCurso(String curso){
		this.curso = curso;
	}
	public String getCurso(){
		return this.curso;
	}
	
	public void setEstado(String estado){
		this.estado = estado;
	}
	public String getEstado(){
		return this.estado;
	}



}