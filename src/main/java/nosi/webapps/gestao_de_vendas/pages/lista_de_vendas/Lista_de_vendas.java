package nosi.webapps.gestao_de_vendas.pages.lista_de_vendas;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Modelo da lista de vendas.
 * Representa a estrutura de dados da página de listagem de vendas.
 */
public class Lista_de_vendas extends Model {

    private List<Table_1> table_1 = new ArrayList<>();

    public void setTable_1(List<Table_1> table_1) {
        this.table_1 = table_1;
    }

    public List<Table_1> getTable_1() {
        return this.table_1;
    }

    public static class Table_1 extends IGRPTable.Table {

        @RParam(rParamName = "p_cliente")
        private String cliente;

        @RParam(rParamName = "p_data")
        private String data;

        @RParam(rParamName = "p_total")
        private String total;

        @RParam(rParamName = "p_status")
        private String status;

        @RParam(rParamName = "p_id_tbl_historico_venda")
        private String id_tbl_historico_venda;

        // --- Getters e Setters ---

        public void setCliente(String cliente) {
            this.cliente = cliente != null ? cliente.trim() : null;
        }

        public String getCliente() {
            return this.cliente;
        }

        public void setData(String data) {
            this.data = data != null ? data.trim() : null;
        }

        public String getData() {
            return this.data;
        }

        public void setTotal(String total) {
            this.total = total != null ? total.trim() : null;
        }

        public String getTotal() {
            return this.total;
        }

        public void setStatus(String status) {
            this.status = status != null ? status.trim().toUpperCase() : null;
        }

        public String getStatus() {
            return this.status;
        }

        public void setId_tbl_historico_venda(String id_tbl_historico_venda) {
            this.id_tbl_historico_venda = id_tbl_historico_venda != null ? id_tbl_historico_venda.trim() : null;
        }

        public String getId_tbl_historico_venda() {
            return this.id_tbl_historico_venda;
        }

        // --- Utilitários ---

        public static String formatarData(java.time.LocalDateTime data) {
            if (data == null) return "";
            return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        }

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
            return Objects.equals(id_tbl_historico_venda, table1.id_tbl_historico_venda);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id_tbl_historico_venda);
        }

        @Override
        public String toString() {
            return "Table_1{" +
                    "cliente='" + cliente + '\'' +
                    ", data='" + data + '\'' +
                    ", total='" + total + '\'' +
                    ", status='" + status + '\'' +
                    ", id_tbl_historico_venda='" + id_tbl_historico_venda + '\'' +
                    '}';
        }
    }

    public void loadTable_1(BaseQueryInterface query) {
        this.setTable_1(this.loadTable(query, Table_1.class));
    }
}
