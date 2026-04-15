package nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_de_listagem_de_pedidos;

import nosi.core.gui.components.IGRPTable;
import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;
import nosi.core.webapp.databse.helpers.BaseQueryInterface;
import java.util.ArrayList;
import java.util.List;

public class Pagina_de_listagem_de_pedidos extends Model {

    @RParam(rParamName = "p_n_do_pedido")
    private String n_do_pedido;

    @RParam(rParamName = "p_estado")
    private String estado;

    @RParam(rParamName = "p_departamento")
    private String departamento;

    @RParam(rParamName = "p_categoria")
    private String categoria;

    // ADICIONADO: Sincronização com o campo 'prioridade_filtro' da View
    @RParam(rParamName = "p_prioridade_filtro")
    private String prioridade_filtro;

    private List<Table_1> table_1 = new ArrayList<>();

    // --- GETTERS E SETTERS DO MODELO ---

    public void setTable_1(List<Table_1> table_1){
        this.table_1 = table_1;
    }
    public List<Table_1> getTable_1(){
        return this.table_1;
    }

    public void setN_do_pedido(String n_do_pedido){
        this.n_do_pedido = n_do_pedido;
    }
    public String getN_do_pedido(){
        return this.n_do_pedido;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }
    public String getEstado(){
        return this.estado;
    }

    public void setDepartamento(String departamento){
        this.departamento = departamento;
    }
    public String getDepartamento(){
        return this.departamento;
    }

    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public String getCategoria(){
        return this.categoria;
    }

    public void setPrioridade_filtro(String prioridade_filtro){
        this.prioridade_filtro = prioridade_filtro;
    }
    public String getPrioridade_filtro(){
        return this.prioridade_filtro;
    }

    /**
     * Classe interna da Tabela - Representa as colunas visíveis
     */
    public static class Table_1 extends IGRPTable.Table {
        private String n_do_pedido_tbl;
        private String assunto;
        private String solicitante_tbl;
        private String tecnico_tbl;
        private String data_criacao;
        private String prioridade_tbl;
        private String estado_tbl;
        private String tbl_listapedido_id; // ID oculto para ações (Editar/Ver)

        public void setN_do_pedido_tbl(String n_do_pedido_tbl){
            this.n_do_pedido_tbl = n_do_pedido_tbl;
        }
        public String getN_do_pedido_tbl(){
            return this.n_do_pedido_tbl;
        }

        public void setAssunto(String assunto){
            this.assunto = assunto;
        }
        public String getAssunto(){
            return this.assunto;
        }

        public void setSolicitante_tbl(String solicitante_tbl){
            this.solicitante_tbl = solicitante_tbl;
        }
        public String getSolicitante_tbl(){
            return this.solicitante_tbl;
        }

        public void setTecnico_tbl(String tecnico_tbl){
            this.tecnico_tbl = tecnico_tbl;
        }
        public String getTecnico_tbl(){
            return this.tecnico_tbl;
        }

        public void setData_criacao(String data_criacao){
            this.data_criacao = data_criacao;
        }
        public String getData_criacao(){
            return this.data_criacao;
        }

        public void setPrioridade_tbl(String prioridade_tbl){
            this.prioridade_tbl = prioridade_tbl;
        }
        public String getPrioridade_tbl(){
            return this.prioridade_tbl;
        }

        public void setEstado_tbl(String estado_tbl){
            this.estado_tbl = estado_tbl;
        }
        public String getEstado_tbl(){
            return this.estado_tbl;
        }

        public void setTbl_listapedido_id(String tbl_listapedido_id){
            this.tbl_listapedido_id = tbl_listapedido_id;
        }
        public String getTbl_listapedido_id(){
            return this.tbl_listapedido_id;
        }
    }

    public void loadTable_1(BaseQueryInterface query) {
        this.setTable_1(this.loadTable(query, Table_1.class));
    }
}