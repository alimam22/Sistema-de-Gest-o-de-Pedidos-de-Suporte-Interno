package nosi.webapps.gestao_de_vendas.dao;

import javax.persistence.Column;
import java.io.Serial;
import nosi.base.ActiveRecord.BaseActiveRecord;
import jakarta.validation.constraints.Size;
import javax.persistence.NamedQuery;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import javax.persistence.Entity;
import jakarta.validation.constraints.NotNull;

/**
 * @author: Nositeste 2026-03-17
 */

//@XmlRootElement // Can be used for REST / XML API

@Entity
@Table(name = "produtos", schema = "public")
@NamedQuery(name = "Produtos.findAll", query = "SELECT t FROM Produtos t")
public class Produtos extends BaseActiveRecord<Produtos> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "PRODUTOS";

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
	@NotNull
	@Column(name = "preco_compra")
	private Double precoCompra;
	@NotNull
	@Column(name = "preco_venda")
	private Double precoVenda;
	@NotNull
	@Column(name = "estoque")
	private Integer estoque;
	@NotNull
	@Column(name = "categoria_id")
	private Integer categoriaId;
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

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public Double getPrecoCompra() {
      return this.precoCompra;
   }

   public void setPrecoCompra(Double precoCompra) {
      this.precoCompra = precoCompra;
   }

   public Double getPrecoVenda() {
      return this.precoVenda;
   }

   public void setPrecoVenda(Double precoVenda) {
      this.precoVenda = precoVenda;
   }

   public Integer getEstoque() {
      return this.estoque;
   }

   public void setEstoque(Integer estoque) {
      this.estoque = estoque;
   }

   public Integer getCategoriaId() {
      return this.categoriaId;
   }

   public void setCategoriaId(Integer categoriaId) {
      this.categoriaId = categoriaId;
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
      public static final String DESCRICAO = "descricao";
      public static final String PRECO_COMPRA = "precoCompra";
      public static final String PRECO_VENDA = "precoVenda";
      public static final String ESTOQUE = "estoque";
      public static final String CATEGORIA_ID = "categoriaId";
      public static final String ATIVO = "ativo";

	  private Field() {}
	}
}