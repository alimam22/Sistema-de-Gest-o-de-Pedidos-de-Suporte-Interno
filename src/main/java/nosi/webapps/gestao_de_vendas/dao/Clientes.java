package nosi.webapps.gestao_de_vendas.dao;

import nosi.base.ActiveRecord.BaseActiveRecord;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Cliente - Representa um cliente no sistema de gestão de vendas.
 * @author Nositeste 2026-03-17
 */
@Entity
@Table(name = "clientes", schema = "public")
@NamedQuery(name = "Clientes.findAll", query = "SELECT t FROM Clientes t ORDER BY t.nome ASC")
public class Clientes extends BaseActiveRecord<Clientes> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "nif", nullable = false, unique = true)
    private Integer nif;

    @Column(name = "endereco", columnDefinition = "text")
    private String endereco;

    @Column(name = "cidade", length = 150)
    private String cidade;

    // --- Construtores ---

    public Clientes() {
        // Construtor padrão para JPA
    }

    // --- Getters e Setters ---

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
        this.nome = nome != null ? nome.trim() : null;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.trim().toLowerCase() : null;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone != null ? telefone.trim().replaceAll("[^0-9+]", "") : null;
    }

    public Integer getNif() {
        return this.nif;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    // Método auxiliar para converter String do formulário para Integer do banco
    public void setNif(String nif) {
        if (nif != null && !nif.trim().isEmpty()) {
            try {
                this.nif = Integer.valueOf(nif.replaceAll("[^0-9]", ""));
            } catch (NumberFormatException e) {
                this.nif = null;
            }
        } else {
            this.nif = null;
        }
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco != null ? endereco.trim() : null;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade != null ? cidade.trim() : null;
    }

    // --- Utilitários ---

    public String getNomeCompleto() {
        return this.nome;
    }

    public String getContato() {
        return this.telefone != null && !this.telefone.isEmpty() ? this.telefone : this.email;
    }

    // --- toString, equals e hashCode para melhor depuração ---

    @Override
    public String toString() {
        return "Clientes{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nif=" + nif +
                ", cidade='" + cidade + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clientes clientes = (Clientes) o;
        return Objects.equals(id, clientes.id) && Objects.equals(email, clientes.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    // --- Constantes de campos para consultas ---

    public static final class Field {
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String EMAIL = "email";
        public static final String TELEFONE = "telefone";
        public static final String NIF = "nif";
        public static final String ENDERECO = "endereco";
        public static final String CIDADE = "cidade";

        private Field() {}
    }
}
