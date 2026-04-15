package nosi.webapps.gestao_de_vendas.dao;

import javax.persistence.Id;
import javax.persistence.Column;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.io.Serial;
import javax.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import javax.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import javax.persistence.Table;

/**
 * @author: Nositeste 2026-03-17
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "categorias", schema = "public")
@NamedQuery(name = "Categorias.findAll", query = "SELECT t FROM Categorias t")
public class Categorias extends BaseActiveRecord<Categorias> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "CATEGORIAS";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotBlank
	@Size(max = 100)
	@Column(name = "nome")
	private String nome;
	@NotBlank
	@Column(name = "descricao", columnDefinition = "text")
	private String descricao;

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String NOME = "nome";
      public static final String DESCRICAO = "descricao";

	  private Field() {}
	}
}