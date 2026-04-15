package nosi.webapps.sistema_gestao_aluno.dao;

import javax.persistence.Id;
import jakarta.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import nosi.base.ActiveRecord.BaseActiveRecord;
import jakarta.validation.constraints.NotNull;
import javax.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import javax.persistence.NamedQuery;
import java.io.Serial;

/**
 * @author: Nositeste 2026-03-06
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "aluno", schema = "public")
@NamedQuery(name = "Aluno.findAll", query = "SELECT t FROM Aluno t")
public class Aluno extends BaseActiveRecord<Aluno> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "ALUNO";

    // Change Integer type to BigDecimal if the number is very large

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O Postgres assume o controle aqui
    @Column(name = "id_aluno", nullable = false, updatable = false)
    private Integer idAluno;
	@NotBlank
	@Size(max = 50)
	@Column(name = "nome")
	private String nome;
	@NotBlank
	@Size(max = 50)
	@Column(name = "apelido")
	private String apelido;
	@NotNull
	@Column(name = "idade")
	private Integer idade;
	@NotBlank
	@Size(max = 50)
	@Column(name = "curso")
	private String curso;
	@NotBlank
	@Size(max = 50)
	@Column(name = "email")
	private String email;

   public Integer getIdAluno() {
      return this.idAluno;
   }

   public void setIdAluno(Integer idAluno) {
      this.idAluno = idAluno;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getApelido() {
      return this.apelido;
   }

   public void setApelido(String apelido) {
      this.apelido = apelido;
   }

   public Integer getIdade() {
      return this.idade;
   }

   public void setIdade(Integer idade) {
      this.idade = idade;
   }

   public String getCurso() {
      return this.curso;
   }

   public void setCurso(String curso) {
      this.curso = curso;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }





    public static final class Field {
      public static final String ID_ALUNO = "idAluno";
      public static final String NOME = "nome";
      public static final String APELIDO = "apelido";
      public static final String IDADE = "idade";
      public static final String CURSO = "curso";
      public static final String EMAIL = "email";

	  private Field() {}
	}
}