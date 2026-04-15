package nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte.DepartamentoRepository;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.Departamento;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_departamento.Pagina_departamento;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_departamento.Pagina_departamento.Table_1;

public class PageHelperPaginaDepartamentoController {

    private final DepartamentoRepository repository;

    public PageHelperPaginaDepartamentoController() {
        this.repository = new DepartamentoRepository();
    }

    /**
     * Valida o perfil do utilizador (Padrão: gestor.nome_app)
     */
    public boolean isGestor() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("gestor");
    }

    public List<Table_1> carregarTabela() {
        if (!isGestor()) return new ArrayList<>();

        List<Table_1> listaTabela = new ArrayList<>();
        List<Departamento> departamentos = repository.findAll();

        if (Core.isNotNull(departamentos)) {
            for (Departamento d : departamentos) {
                Table_1 row = new Table_1();
                row.setTbl_departamento_id(d.getId().toString());
                row.setNome_departamento(d.getNome());
                row.setSiglas_departamento(d.getSigla());

                row.setActivo_departamento("1".equals(d.getActivo()) ? "Ativado" : "Desativado");
                row.setActivo_departamento_check(d.getActivo());

                if (d.getDataCriacao() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    row.setData_departamento(d.getDataCriacao().format(formatter));
                }
                listaTabela.add(row);
            }
        }
        return listaTabela;
    }

    public void preencherFormulario(Pagina_departamento model, Integer id) {
        if (!isGestor() || Core.isNull(id)) return;

        Departamento d = repository.findById(id);
        if (Core.isNotNull(d)) {
            model.setP_id(d.getId());
            model.setNome(d.getNome());
            model.setSiglas(d.getSigla());
            model.setActivos(d.getActivo());
            
            // VALIDAÇÃO DE AVISO: Verifica se há pedidos ativos associados
            if (repository.temPedidosActivos(id)) {
                Core.setMessageWarning("Atenção: Este departamento possui pedidos em curso. " +
                        "Alterações podem afetar pedidos activos.");
            }
        }
    }

    public boolean guardar(Pagina_departamento model) {
        if (!isGestor()) return false;

        Integer id = model.getP_id();
        Departamento d;

        if (Core.isNotNull(id) && id > 0) {
            d = repository.findById(id);
            // Regra: Bloquear desativação se houver pedidos ativos
            if ("0".equals(model.getActivos()) && "1".equals(d.getActivo())) {
                if (repository.temPedidosActivos(id)) {
                    Core.setMessageError("Não pode desactivar: existem pedidos em curso neste departamento.");
                    return false;
                }
            }
        } else {
            d = new Departamento();
            d.setDataCriacao(java.time.LocalDateTime.now());
        }

        // Regra: Nome Único
        if (repository.existsByNome(model.getNome(), id)) {
            Core.setMessageError("O departamento '" + model.getNome() + "' já existe.");
            return false;
        }

        d.setNome(model.getNome());
        d.setSigla(model.getSiglas());
        d.setActivo(model.getActivos());

        return repository.save(d) != null;
    }

    public void eliminar(Integer id) {
        if (!isGestor() || Core.isNull(id)) return;

        // Regra: Bloquear eliminação física se houver histórico
        if (repository.temAlgumPedido(id)) {
            Core.setMessageError("Impossível eliminar: Este departamento já possui histórico de pedidos.");
            return;
        }

        if (repository.delete(id)) {
            Core.setMessageSuccess("Departamento eliminado com sucesso!");
        }
    }
}