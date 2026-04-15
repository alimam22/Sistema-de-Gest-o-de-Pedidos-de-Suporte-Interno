package nosi.webapps.gestao_de_vendas.dao;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import nosi.base.ActiveRecord.BaseActiveRecord;

/**
 * Entidade Venda - Representa uma venda realizada no sistema.
 * @author Nositeste 2026-03-17
 */
@Entity
@Table(name = "vendas", schema = "public")
@NamedQuery(name = "Vendas.findAll", query = "SELECT t FROM Vendas t ORDER BY t.dataVenda DESC")
public class Vendas extends BaseActiveRecord<Vendas> {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String TABLE_NAME = "VENDAS";
    
    public static final String STATUS_ATIVO = "ATIVO";
    public static final String STATUS_CONCLUIDA = "CONCLUIDA";
    public static final String STATUS_CANCELADA = "CANCELADA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "cliente_id")
    private Integer clienteId;

    @NotNull
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @NotNull
    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @NotNull
    @Column(name = "total")
    private BigDecimal total;

    @NotBlank
    @Size(max = 20)
    @Column(name = "status", columnDefinition = "bpchar")
    private String status;

    // --- Construtores ---

    public Vendas() {
        this.dataVenda = LocalDateTime.now();
        this.status = STATUS_ATIVO;
    }

    // --- Getters e Setters ---

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getUsuarioId() {
        return this.usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getDataVenda() {
        return this.dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status != null ? status.toUpperCase().trim() : STATUS_ATIVO;
    }

    // --- Utilitários ---

    public boolean isAtiva() {
        return STATUS_ATIVO.equals(this.status);
    }

    public boolean isConcluida() {
        return STATUS_CONCLUIDA.equals(this.status);
    }

    public boolean isCancelada() {
        return STATUS_CANCELADA.equals(this.status);
    }

    public void concluir() {
        this.setStatus(STATUS_CONCLUIDA);
    }

    public void cancelar() {
        this.setStatus(STATUS_CANCELADA);
    }

    // --- toString, equals e hashCode ---

    @Override
    public String toString() {
        return "Vendas{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", usuarioId=" + usuarioId +
                ", dataVenda=" + dataVenda +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendas vendas = (Vendas) o;
        return Objects.equals(id, vendas.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- Constantes de campos ---

    public static final class Field {
        public static final String ID = "id";
        public static final String CLIENTE_ID = "clienteId";
        public static final String USUARIO_ID = "usuarioId";
        public static final String DATA_VENDA = "dataVenda";
        public static final String TOTAL = "total";
        public static final String STATUS = "status";

        private Field() {}
    }
}
