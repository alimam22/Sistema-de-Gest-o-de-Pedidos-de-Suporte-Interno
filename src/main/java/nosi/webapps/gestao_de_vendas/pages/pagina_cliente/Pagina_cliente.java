package nosi.webapps.gestao_de_vendas.pages.pagina_cliente;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Modelo da lista de clientes.
 * Representa a estrutura de dados da página de listagem de clientes.
 */
public class Pagina_cliente extends Model {

    // 1. ID Principal da Página (necessário para o loadData não crashar no Java 17)
    @RParam(rParamName = "p_id")
    private String id;

    private List<Table_1> table_1 = new ArrayList<>();

    public void setTable_1(List<Table_1> table_1) {
        this.table_1 = table_1;
    }

    public List<Table_1> getTable_1() {
        return this.table_1;
    }

    // Getters e Setters do ID principal
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static class Table_1 extends IGRPTable.Table {

        @RParam(rParamName = "p_nome_cliente")
        private String nome_cliente;

        @RParam(rParamName = "p_email_cliente")
        private String email_cliente;

        @RParam(rParamName = "p_telefone_cliente")
        private String telefone_cliente;

        @RParam(rParamName = "p_endereco_cliente")
        private String endereco_cliente;

        @RParam(rParamName = "p_nif_cliente")
        private String nif_cliente;

        @RParam(rParamName = "p_cidade_cliente")
        private String cidade_cliente;

        @RParam(rParamName = "p_id_tbl_cliente")
        private String id_tbl_cliente;

        // --- Getters e Setters da Tabela ---

        public void setNome_cliente(String nome_cliente) {
            this.nome_cliente = nome_cliente != null ? nome_cliente.trim() : null;
        }

        public String getNome_cliente() {
            return this.nome_cliente;
        }

        public void setEmail_cliente(String email_cliente) {
            this.email_cliente = email_cliente != null ? email_cliente.trim().toLowerCase() : null;
        }

        public String getEmail_cliente() {
            return this.email_cliente;
        }

        public void setTelefone_cliente(String telefone_cliente) {
            this.telefone_cliente = telefone_cliente != null ? telefone_cliente.trim() : null;
        }

        public String getTelefone_cliente() {
            return this.telefone_cliente;
        }

        public void setEndereco_cliente(String endereco_cliente) {
            this.endereco_cliente = endereco_cliente != null ? endereco_cliente.trim() : null;
        }

        public String getEndereco_cliente() {
            return this.endereco_cliente;
        }

        public void setNif_cliente(Integer nif_cliente) {
            this.nif_cliente = nif_cliente != null ? String.valueOf(nif_cliente) : null;
        }

        public String getNif_cliente() {
            return this.nif_cliente;
        }

        public void setCidade_cliente(String cidade_cliente) {
            this.cidade_cliente = cidade_cliente != null ? cidade_cliente.trim() : null;
        }

        public String getCidade_cliente() {
            return this.cidade_cliente;
        }

        public void setId_tbl_cliente(String id_tbl_cliente) {
            this.id_tbl_cliente = id_tbl_cliente != null ? id_tbl_cliente.trim() : null;
        }

        public String getId_tbl_cliente() {
            return this.id_tbl_cliente;
        }

        // --- equals e hashCode para melhor depuração ---

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Table_1 table1 = (Table_1) o;
            return Objects.equals(id_tbl_cliente, table1.id_tbl_cliente);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id_tbl_cliente);
        }

        @Override
        public String toString() {
            return "Table_1{" +
                    "nome_cliente='" + nome_cliente + '\'' +
                    ", email_cliente='" + email_cliente + '\'' +
                    ", telefone_cliente='" + telefone_cliente + '\'' +
                    ", nif_cliente='" + nif_cliente + '\'' +
                    ", cidade_cliente='" + cidade_cliente + '\'' +
                    ", id_tbl_cliente='" + id_tbl_cliente + '\'' +
                    '}';
        }
    }

    public void loadTable_1(BaseQueryInterface query) {
        this.setTable_1(this.loadTable(query, Table_1.class));
    }
}
