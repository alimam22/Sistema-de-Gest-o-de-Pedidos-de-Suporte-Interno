package nosi.webapps.gestao_de_pedidos_de_suporte_.PageHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte.CategoriaRepository;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.CategoriaSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_categoria.Pagina_categoria;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_categoria.Pagina_categoria.Table_1;

public class PageHelperPaginaCategoriaController {

    private final CategoriaRepository repository;

    public PageHelperPaginaCategoriaController() {
        this.repository = new CategoriaRepository();
    }

    /**
     * Validação de perfil: Verifica se o código contém "gestor"
     */
    public boolean isGestor() {
        String code = Core.getCurrentProfileCode();
        return code != null && code.toLowerCase().contains("gestor");
    }

    public void carregarTabela(Pagina_categoria model) {
        // Bloqueio de leitura para não gestores
        if (!isGestor()) return;

        List<Table_1> listaTabela = new ArrayList<>();
        List<CategoriaSuporte> categorias = new CategoriaSuporte().findAll();

        if (Core.isNotNull(categorias)) {
            for (CategoriaSuporte c : categorias) {
                Table_1 row = new Table_1();
                row.setTbl_categoria_id(c.getId().toString());
                row.setNome_categoria(c.getNome());
                row.setDescricao_categoria(c.getDescricao());
                row.setActivo_categoria(c.getActivo() != null && c.getActivo() == 1 ? "Ativado" : "Desativado");

                if (c.getDataCriacao() != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    row.setData_categoria(c.getDataCriacao().format(formatter));
                }
                listaTabela.add(row);
            }
        }
        model.setTable_1(listaTabela);
    }

    public void preencherFormulario(Pagina_categoria model, Integer id) {
        if (!isGestor() || Core.isNull(id)) return;

        CategoriaSuporte c = new CategoriaSuporte().findOne(id);
        if (Core.isNotNull(c)) {
            model.setP_id(c.getId());
            model.setNome(c.getNome());
            model.setDescricao(c.getDescricao());
            model.setActivo(c.getActivo() != null ? c.getActivo().toString() : "0");
        }
    }

    public boolean guardar(Pagina_categoria model) {
        // Bloqueio de gravação
        if (!isGestor()) {
            Core.setMessageError("Acesso negado.");
            return false;
        }

        Integer id = model.getP_id();
        if (repository.existsByNome(model.getNome().trim(), id)) {
            Core.setMessageError("A categoria '" + model.getNome() + "' já existe.");
            return false;
        }

        CategoriaSuporte c = (Core.isNotNull(id) && id > 0) ? new CategoriaSuporte().findOne(id) : new CategoriaSuporte();

        if (c.getId() == null) {
            c.setDataCriacao(java.time.LocalDateTime.now());
        }

        c.setNome(model.getNome().trim());
        c.setDescricao(model.getDescricao());
        c.setActivo(Core.isNotNull(model.getActivo()) ? Short.parseShort(model.getActivo()) : (short) 1);

        c = (c.getId() == null) ? c.insert() : c.update();

        if (Core.isNotNull(c) && !c.hasError()) {
            Core.setMessageSuccess();
            return true;
        }

        Core.setMessageError("Erro ao gravar dados.");
        return false;
    }

    public void eliminar(Integer id) {
        // Bloqueio de eliminação
        if (!isGestor() || Core.isNull(id)) {
            Core.setMessageError("Operação não permitida.");
            return;
        }

        CategoriaSuporte c = new CategoriaSuporte().findOne(id);
        if (Core.isNotNull(c) && c.delete(id)) {
            Core.setMessageSuccess();
        } else {
            Core.setMessageError("Não foi possível eliminar o registo.");
        }
    }
}