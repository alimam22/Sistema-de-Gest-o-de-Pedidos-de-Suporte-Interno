package nosi.webapps.gestao_de_vendas.pages.detalhe_da_venda;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Modelo do detalhe da venda.
 * Representa a estrutura de dados da página de detalhes da venda.
 */
public class Detalhe_da_venda extends Model {

    private List<Table_1> table_1 = new ArrayList<>();

    public void setTable_1(List<Table_1> table_1) {
        this.table_1 = table_1;
    }

    public List<Table_1> getTable_1() {
        return this.table_1;
    }

    public static class Table_1 extends IGRPTable.Table {

        @RParam(rParamName = "p_produtos_comprados")
        private String produtos_comprados;

        @RParam(rParamName = "p_quantidade")
        private String quantidade;

        @RParam(rParamName = "p_preco")
        private String preco;

        @RParam(rParamName = "p_id_tbl_detalha")
        private String id_tbl_detalha;

        // --- Getters e Setters ---

        public void setProdutos_comprados(String produtos_comprados) {
            this.produtos_comprados = produtos_comprados != null ? produtos_comprados.trim() : null;
        }

        public String getProdutos_comprados() {
            return this.produtos_comprados;
        }

        public void setQuantidade(String quantidade) {
            this.quantidade = quantidade != null ? quantidade.trim() : null;
        }

        public String getQuantidade() {
            return this.quantidade;
        }

        public void setPreco(String preco) {
            this.preco = preco != null ? preco.trim() : null;
        }

        public String getPreco() {
            return this.preco;
        }

        public void setId_tbl_detalha(String id_tbl_detalha) {
            this.id_tbl_detalha = id_tbl_detalha != null ? id_tbl_detalha.trim() : null;
        }

        public String getId_tbl_detalha() {
            return this.id_tbl_detalha;
        }

        // --- Utilitários ---

        public static String formatarValor(BigDecimal valor) {
            if (valor == null) return "R$ 0,00";
            return String.format("R$ %.2f", valor);
        }

        // --- equals e hashCode ---

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Table_1 table1 = (Table_1) o;
            return Objects.equals(id_tbl_detalha, table1.id_tbl_detalha);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id_tbl_detalha);
        }

        @Override
        public String toString() {
            return "Table_1{" +
                    "produtos_comprados='" + produtos_comprados + '\'' +
                    ", quantidade='" + quantidade + '\'' +
                    ", preco='" + preco + '\'' +
                    ", id_tbl_detalha='" + id_tbl_detalha + '\'' +
                    '}';
        }
    }

    public void loadTable_1(BaseQueryInterface query) {
        this.setTable_1(this.loadTable(query, Table_1.class));
    }
}
