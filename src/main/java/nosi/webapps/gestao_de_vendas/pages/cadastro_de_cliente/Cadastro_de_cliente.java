package nosi.webapps.gestao_de_vendas.pages.cadastro_de_cliente;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.validator.constraints.*;
import java.util.Objects;

/**
 * Modelo de Cadastro de Cliente.
 * Utilizado para validar e processar dados do formulário de cadastro/edição.
 */
public class Cadastro_de_cliente extends Model {

    // 1. Adicionado o campo ID com RParam (essencial para edição e para o loadData não crashar)
    @RParam(rParamName = "p_id")
    private Integer id;

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 150, message = "O nome deve ter entre 3 e 150 caracteres")
    @RParam(rParamName = "p_nome")
    private String nome;

    @NotNull(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @RParam(rParamName = "p_email")
    private String email;

    @NotNull(message = "O telefone é obrigatório")
    @Size(min = 8, max = 20, message = "O telefone deve ter entre 8 e 20 caracteres")
    @RParam(rParamName = "p_telefone")
    private String telefone;

    @NotNull(message = "O endereço é obrigatório")
    @Size(min = 5, max = 500, message = "O endereço deve ter entre 5 e 500 caracteres")
    @RParam(rParamName = "p_endereco")
    private String endereco;

    @NotNull(message = "O NIF é obrigatório")
    @Size(min = 9, max = 9, message = "O NIF deve ter exatamente 9 dígitos")
    @RParam(rParamName = "p_nif")
    private Integer nif;

    // 2. CORREÇÃO CRUCIAL: Adicionado @RParam aqui para evitar o NullPointerException
    @NotNull(message = "A cidade é obrigatória")
    @Size(min = 2, max = 150, message = "A cidade deve ter entre 2 e 150 caracteres")
    @RParam(rParamName = "p_cidade")
    private String cidade;

    // --- Getters e Setters ---

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setNome(String nome) {
        this.nome = nome != null ? nome.trim() : null;
    }

    public String getNome() {
        return this.nome;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.trim().toLowerCase() : null;
    }

    public String getEmail() {
        return this.email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone != null ? telefone.trim().replaceAll("[^0-9+]", "") : null;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco != null ? endereco.trim() : null;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setNif(Integer nif) {
        this.nif = nif;
    }

    public Integer getNif() {
        return this.nif;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade != null ? cidade.trim() : null;
    }

    public String getCidade() {
        return this.cidade;
    }

    // --- Utilitários ---

    public String getNomeCompleto() {
        return this.nome;
    }

    public String getContato() {
        return this.telefone != null && !this.telefone.isEmpty() ? this.telefone : this.email;
    }

    // --- equals e hashCode para melhor depuração ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cadastro_de_cliente that = (Cadastro_de_cliente) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "Cadastro_de_cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", nif=" + nif +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
