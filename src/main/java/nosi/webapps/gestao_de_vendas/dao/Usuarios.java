package nosi.webapps.gestao_de_vendas.dao;

import javax.persistence.Id;
import javax.persistence.Column;
import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.io.Serial;
import javax.persistence.GeneratedValue;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import javax.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import javax.persistence.Table;

/**
 * @author: Nositeste 2026-03-17
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "usuarios", schema = "public")
@NamedQuery(name = "Usuarios.findAll", query = "SELECT t FROM Usuarios t")
public class Usuarios extends BaseActiveRecord<Usuarios> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "USUARIOS";

    // Change Integer type to BigDecimal if the number is very large

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, updatable = false)
 	private Integer id;
	@NotBlank
	@Size(max = 150)
	@Column(name = "nome")
	private String nome;
	@NotBlank
	@Size(max = 150)
	@Column(name = "email")
	private String email;
	@NotBlank
	@Size(max = 150)
	@Column(name = "senha")
	private String senha;
	@NotBlank
	@Size(max = 150)
	@Column(name = "perfil")
	private String perfil;
	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

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

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getSenha() {
      return this.senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public String getPerfil() {
      return this.perfil;
   }

   public void setPerfil(String perfil) {
      this.perfil = perfil;
   }

   public Boolean getAtivo() {
      return this.ativo;
   }

   public void setAtivo(Boolean ativo) {
      this.ativo = ativo;
   }

   public static final class Field {
      public static final String ID = "id";
      public static final String NOME = "nome";
      public static final String EMAIL = "email";
      public static final String SENHA = "senha";
      public static final String PERFIL = "perfil";
      public static final String ATIVO = "ativo";

	  private Field() {}
	}
}