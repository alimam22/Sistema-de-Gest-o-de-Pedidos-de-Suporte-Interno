package nosi.webapps.sistema_gestao_aluno.dao;

import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serial;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.GeneratedValue;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Column;
import jakarta.validation.constraints.NotNull;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.ManyToOne;

/**
 * @author: Nositeste 2026-03-09
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "tbl_matricula", schema = "public")
@NamedQuery(name = "TblMatricula.findAll", query = "SELECT t FROM TblMatricula t")
public class TblMatricula extends BaseActiveRecord<TblMatricula> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "TBL_MATRICULA";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_matricula", nullable = false, updatable = false)
 	private Integer idMatricula;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_aluno", foreignKey = @ForeignKey(name = "Id_aluno_fk"), referencedColumnName="id_aluno")
	private Aluno idAluno;
	@NotBlank
	@Size(max = 50)
	@Column(name = "ano_letivo")
	private String anoLetivo;
	@NotBlank
	@Size(max = 50)
	@Column(name = "curso")
	private String curso;
	@Column(name = "data_matricula")
	private Date dataMatricula;
	@Column(name = "estado_matricula")
	private String estadoMatricula;

   public Integer getIdMatricula() {
      return this.idMatricula;
   }

   public void setIdMatricula(Integer idMatricula) {
      this.idMatricula = idMatricula;
   }

   public Aluno getIdAluno() {
      return this.idAluno;
   }

   public void setIdAluno(Aluno idAluno) {
      this.idAluno = idAluno;
   }

   public String getAnoLetivo() {
      return this.anoLetivo;
   }

   public void setAnoLetivo(String anoLetivo) {
      this.anoLetivo = anoLetivo;
   }

   public String getCurso() {
      return this.curso;
   }

   public void setCurso(String curso) {
      this.curso = curso;
   }

   public Date getDataMatricula() {
      return this.dataMatricula;
   }

   public void setDataMatricula(Date dataMatricula) {
      this.dataMatricula = dataMatricula;
   }

   public String getEstadoMatricula() {
      return this.estadoMatricula;
   }

   public void setEstadoMatricula(String estadoMatricula) {
      this.estadoMatricula = estadoMatricula;
   }

   public static final class Field {
      public static final String ID_MATRICULA = "idMatricula";
      public static final String ID_ALUNO = "idAluno";
      public static final String ANO_LETIVO = "anoLetivo";
      public static final String CURSO = "curso";
      public static final String DATA_MATRICULA = "dataMatricula";
      public static final String ESTADO_MATRICULA = "estadoMatricula";

	  private Field() {}
	}
}