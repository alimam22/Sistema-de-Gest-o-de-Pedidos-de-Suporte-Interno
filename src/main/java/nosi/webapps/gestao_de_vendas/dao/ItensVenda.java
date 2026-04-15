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
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidade ItensVenda - Representa os itens de uma venda.
 * @author Nositeste 2026-03-17
 */
@Entity
@Table(name = "itens_venda", schema = "public")
@NamedQuery(name = "ItensVenda.findAll", query = "SELECT t FROM ItensVenda t ORDER BY t.vendaId, t.id")
public class ItensVenda extends BaseActiveRecord<ItensVenda> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "ITENS_VENDA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "venda_id")
    private Integer vendaId;

    @NotNull
    @Column(name = "produto_id")
    private Integer produtoId;

    @NotNull
    @Column(name = "quantidade")
    private Integer quantidade;

    @NotNull
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    // --- Construtores ---

    public ItensVenda() {
        // Construtor padrão para JPA
    }

    // --- Getters e Setters ---

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVendaId() {
        return this.vendaId;
    }

    public void setVendaId(Integer vendaId) {
        this.vendaId = vendaId;
    }

    public Integer getProdutoId() {
        return this.produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return this.precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    // --- Utilitários ---

    public void calcularSubtotal() {
        if (this.quantidade != null && this.precoUnitario != null) {
            this.subtotal = this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade));
        }
    }

    // --- toString, equals e hashCode ---

    @Override
    public String toString() {
        return "ItensVenda{" +
                "id=" + id +
                ", vendaId=" + vendaId +
                ", produtoId=" + produtoId +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", subtotal=" + subtotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensVenda that = (ItensVenda) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- Constantes de campos ---

    public static final class Field {
        public static final String ID = "id";
        public static final String VENDA_ID = "vendaId";
        public static final String PRODUTO_ID = "produtoId";
        public static final String QUANTIDADE = "quantidade";
        public static final String PRECO_UNITARIO = "precoUnitario";
        public static final String SUBTOTAL = "subtotal";

        private Field() {}
    }
}
