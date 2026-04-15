package nosi.webapps.gestao_de_vendas.PageHelperPaginaProduto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import nosi.core.webapp.Controller;
import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_vendas.Repository.ProdutoRepository;
import nosi.webapps.gestao_de_vendas.dao.Produtos;
import nosi.webapps.gestao_de_vendas.pages.lista_de_produtos.Lista_de_produtos;
import nosi.webapps.gestao_de_vendas.pages.lista_de_produtos.Lista_de_produtosView;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Controller auxiliar responsável pelo fluxo da listagem de produtos.
 */
public class PageHelperPaginaProdutoController {

    private PageHelperPaginaProdutoController() {
        // Classe utilitária — não instanciável
    }

    // -------------------------------------------------------------------------
    // Público
    // -------------------------------------------------------------------------

    /**
     * Carrega a lista de produtos na tabela da view.
     * Abre sessão + transação Hibernate antes de usar o repositório.
     */
    public static void handleIndex(Lista_de_produtos model, Lista_de_produtosView view) {
        List<Lista_de_produtos.Table_1_produto> listaTabela = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = Core.getSession(Core.defaultConnection());
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            ProdutoRepository repository = new ProdutoRepository(session);
            Iterable<Produtos> produtosDb = repository.findAll();

            if (produtosDb != null) {
                for (Produtos p : produtosDb) {
                    listaTabela.add(mapearLinhaTabela(p));
                }
            }

            transaction.commit();

        } catch (Exception e) {
            rollbackSilencioso(transaction);
            Core.setMessageError("Erro ao carregar a lista de produtos: " + e.getMessage());
        } finally {
            fecharSessao(session);
        }

        model.setTable_1_produto(listaTabela);
    }

    /**
     * Valida a existência do produto selecionado e prepara os parâmetros para edição.
     */
    public static boolean prepararEditar(Controller ctrl) {
        String id = resolverIdTabela("p_id_tbl_produto");

        if (Core.isNull(id)) {
            Core.setMessageError("Selecione um produto para editar.");
            return false;
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = Core.getSession(Core.defaultConnection());
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            ProdutoRepository repository = new ProdutoRepository(session);
            Optional<Produtos> produto = repository.findById(Core.toInt(id));

            transaction.commit();

            if (produto.isPresent()) {
                ctrl.addQueryString("p_id", id);
                ctrl.addQueryString("isEdit", "true");
                return true;
            }

            Core.setMessageError("Produto não encontrado.");

        } catch (Exception e) {
            rollbackSilencioso(transaction);
            Core.setMessageError("Erro ao preparar edição: " + e.getMessage());
        } finally {
            fecharSessao(session);
        }

        return false;
    }

    /**
     * Elimina o produto selecionado na tabela.
     */
    public static boolean eliminar() {
        String id = resolverIdTabela("p_id_tbl_produto");

        if (Core.isNull(id)) {
            Core.setMessageError("Selecione um produto para eliminar.");
            return false;
        }

        Session session = null;
        Transaction transaction = null;
        try {
            session = Core.getSession(Core.defaultConnection());
            transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            ProdutoRepository repository = new ProdutoRepository(session);
            Optional<Produtos> produto = repository.findById(Core.toInt(id));

            if (produto.isPresent()) {
                repository.delete(produto.get());
                transaction.commit();
                Core.setMessageSuccess("Produto eliminado com sucesso!");
                return true;
            }

            transaction.commit();
            Core.setMessageError("O produto já não existe na base de dados.");

        } catch (Exception e) {
            rollbackSilencioso(transaction);
            Core.setMessageError("Não foi possível eliminar: verifique se existem vendas associadas a este produto.");
        } finally {
            fecharSessao(session);
        }

        return false;
    }

    /**
     * Resolve o ID da linha selecionada na tabela, suportando param simples e array.
     */
    public static String resolverIdTabela(String param) {
        String id = Core.getParam(param);
        if (Core.isNull(id)) {
            String[] ids = Core.getParamArray(param);
            if (ids != null && ids.length > 0) {
                id = ids[0];
            }
        }
        return (id != null && !id.trim().isEmpty()) ? id.trim() : null;
    }

    // -------------------------------------------------------------------------
    // Privado — auxiliares
    // -------------------------------------------------------------------------

    /**
     * Mapeia uma entidade {@link Produtos} para uma linha da tabela da view.
     */
    private static Lista_de_produtos.Table_1_produto mapearLinhaTabela(Produtos p) {
        Lista_de_produtos.Table_1_produto linha = new Lista_de_produtos.Table_1_produto();
        linha.setNome_produto(p.getNome());
        linha.setPreco_produto(p.getPrecoVenda() != null ? p.getPrecoVenda().intValue() : 0);
        linha.setEstoque_produto(p.getEstoque() != null ? p.getEstoque() : 0);
        linha.setCategoria_produto(p.getCategoriaId() != null ? String.valueOf(p.getCategoriaId()) : "Sem Categoria");
        linha.setId_tbl_produto(String.valueOf(p.getId()));
        return linha;
    }

    /**
     * Faz rollback sem lançar exceção secundária.
     */
    private static void rollbackSilencioso(Transaction transaction) {
        try {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        } catch (Exception ignored) {
            // rollback falhou — não há o que fazer
        }
    }

    /**
     * Fecha a sessão Hibernate sem lançar exceção secundária.
     */
    private static void fecharSessao(Session session) {
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }
        } catch (Exception ignored) {
            // sessão já fechada
        }
    }
}
