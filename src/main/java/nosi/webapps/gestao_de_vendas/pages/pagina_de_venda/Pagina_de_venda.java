package nosi.webapps.gestao_de_vendas.pages.pagina_de_venda;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Modelo da página de venda.
 * Representa a estrutura de dados da página de nova venda.
 */
public class Pagina_de_venda extends Model {

    // ID da venda (para edição)
    @RParam(rParamName = "p_id")
    private Integer id;

    @RParam(rParamName = "p_cliente")
    private String cliente;

    @RParam(rParamName = "p_data")
    private String data;

    @RParam(rParamName = "p_status")
    private String status;

    @RParam(rParamName = "p_total_venda")
    private String total_venda;

    private List<Table_1> table_1 = new ArrayList<>();

    public void setTable_1(List<Table_1> table_1) {
        this.table_1 = table_1;
    }

    public List<Table_1> getTable_1() {
        return this.table_1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_venda() {
        return total_venda;
    }

    public void setTotal_venda(String total_venda) {
        this.total_venda = total_venda;
    }

    public static class Table_1 extends IGRPTable.Table {

        @RParam(rParamName = "p_produto_id")
        private String produtoId;

        @RParam(rParamName = "p_produto_nome")
        private String produtoNome;

        @RParam(rParamName = "p_quantidade")
        private String quantidade;

        @RParam(rParamName = "p_preco_unitario")
        private String precoUnitario;

        @RParam(rParamName = "p_subtotal")
        private String subtotal;

        @RParam(rParamName = "p_id_tbl_item")
        private String id_tbl_item;

        // --- Getters e Setters ---

        public void setProdutoId(String produtoId) {
            this.produtoId = produtoId != null ? produtoId.trim() : null;
        }

        public String getProdutoId() {
            return this.produtoId;
        }

        public void setProdutoNome(String produtoNome) {
            this.produtoNome = produtoNome != null ? produtoNome.trim() : null;
        }

        public String getProdutoNome() {
            return this.produtoNome;
        }

        public void setQuantidade(String quantidade) {
            this.quantidade = quantidade != null ? quantidade.trim() : null;
        }

        public String getQuantidade() {
            return this.quantidade;
        }

        public void setPrecoUnitario(String precoUnitario) {
            this.precoUnitario = precoUnitario != null ? precoUnitario.trim() : null;
        }

        public String getPrecoUnitario() {
            return this.precoUnitario;
        }

        public void setSubtotal(String subtotal) {
            this.subtotal = subtotal != null ? subtotal.trim() : null;
        }

        public String getSubtotal() {
            return this.subtotal;
        }

        public void setId_tbl_item(String id_tbl_item) {
            this.id_tbl_item = id_tbl_item != null ? id_tbl_item.trim() : null;
        }

        public String getId_tbl_item() {
            return this.id_tbl_item;
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
            return Objects.equals(id_tbl_item, table1.id_tbl_item);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id_tbl_item);
        }

        @Override
        public String toString() {
            return "Table_1{" +
                    "produtoId='" + produtoId + '\'' +
                    ", produtoNome='" + produtoNome + '\'' +
                    ", quantidade='" + quantidade + '\'' +
                    ", precoUnitario='" + precoUnitario + '\'' +
                    ", subtotal='" + subtotal + '\'' +
                    ", id_tbl_item='" + id_tbl_item + '\'' +
                    '}';
        }
    }

    public void loadTable_1(BaseQueryInterface query) {
        this.setTable_1(this.loadTable(query, Table_1.class));
    }
}
