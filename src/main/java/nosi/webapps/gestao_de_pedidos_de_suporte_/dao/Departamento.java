package nosi.webapps.gestao_de_pedidos_de_suporte_.dao;

import java.io.Serial;
import java.time.LocalDateTime;
import javax.persistence.*; // Centralizando no javax para evitar conflitos
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import nosi.base.ActiveRecord.BaseActiveRecord;

/**
 * @author: Nositeste 2026-03-31
 */

@Entity
@Table(name = "departamento", schema = "public")
@NamedQuery(name = "Departamento.findAll", query = "SELECT t FROM Departamento t")
public class Departamento extends BaseActiveRecord<Departamento> {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Size(max = 10)
    @Column(name = "sigla")
    private String sigla;

    @Column(name = "activo", length = 1) // Alterado para String conforme pedido
    private String activo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

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
        this.nome = nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public static final class Field {
        public static final String ID = "id";
        public static final String NOME = "nome";
        public static final String SIGLA = "sigla";
        public static final String ACTIVO = "activo";
        public static final String DATA_CRIACAO = "dataCriacao";

        private Field() {}
    }
}