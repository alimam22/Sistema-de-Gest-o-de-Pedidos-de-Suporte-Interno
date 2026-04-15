package nosi.webapps.gestao_de_vendas.pages.painel_de_controle;

import nosi.core.webapp.Model;
import nosi.core.webapp.RParam;

public class Painel_de_controle extends Model {

    // ── Estatísticas do Dashboard ─────────────────────────────────────────────

    @RParam(rParamName = "p_total_clientes")
    private String total_clientes;

    @RParam(rParamName = "p_total_produtos")
    private String total_produtos;

    @RParam(rParamName = "p_vendas_hoje")
    private String vendas_hoje;

    @RParam(rParamName = "p_usuarios_ativos")
    private String usuarios_ativos;


    // ── Getters e Setters ─────────────────────────────────────────────────────

    public void setTotal_clientes(String total_clientes) {
        this.total_clientes = total_clientes;
    }
    public String getTotal_clientes() {
        return this.total_clientes;
    }

    public void setTotal_produtos(String total_produtos) {
        this.total_produtos = total_produtos;
    }
    public String getTotal_produtos() {
        return this.total_produtos;
    }

    public void setVendas_hoje(String vendas_hoje) {
        this.vendas_hoje = vendas_hoje;
    }
    public String getVendas_hoje() {
        return this.vendas_hoje;
    }

    public void setUsuarios_ativos(String usuarios_ativos) {
        this.usuarios_ativos = usuarios_ativos;
    }
    public String getUsuarios_ativos() {
        return this.usuarios_ativos;
    }

}