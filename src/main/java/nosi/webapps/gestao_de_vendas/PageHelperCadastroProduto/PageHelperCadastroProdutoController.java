package nosi.webapps.gestao_de_vendas.PageHelperCadastroProduto;

import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_vendas.dao.Produtos;
import nosi.webapps.gestao_de_vendas.pages.cadastro_de_produto.Cadastro_de_produto;
import nosi.webapps.gestao_de_vendas.pages.cadastro_de_produto.Cadastro_de_produtoView;

/**
 * Controller auxiliar responsável pelo fluxo de cadastro e edição de produtos.
 */
public class PageHelperCadastroProdutoController {

    private PageHelperCadastroProdutoController() {
        // Classe utilitária — não instanciável
    }

    // -------------------------------------------------------------------------
    // Público
    // -------------------------------------------------------------------------

    /**
     * Preenche o formulário com os dados do produto quando em modo de edição.
     * Não sobrescreve os valores do model caso já estejam preenchidos (ex.: após
     * erro de validação).
     */
    public static void handleIndex(Cadastro_de_produto model, Cadastro_de_produtoView view) {
        String id = Core.getParam("p_id");

        if (!Core.isNotNull(id) || Core.isNotNull(model.getNome())) {
            return;
        }

        Produtos produto = buscarProdutoPorId(id);
        if (produto == null) {
            Core.setMessageError("Produto não encontrado para o ID informado.");
            return;
        }

        preencherModelComProduto(model, produto);
    }

    /**
     * Valida e persiste o produto (inserção ou atualização).
     *
     * @return {@code true} se a operação foi bem-sucedida; {@code false} caso contrário.
     */
    public static boolean processarSalva(Cadastro_de_produto model) {
        if (!model.validate()) {
            Core.setMessageError("Por favor, preencha todos os campos obrigatórios.");
            return false;
        }

        try {
            String id = Core.getParam("p_id");
            boolean isEdit = Core.isNotNull(id);

            Produtos produto = isEdit ? buscarProdutoPorId(id) : new Produtos();

            if (produto == null) {
                Core.setMessageError("Produto não encontrado. Verifique o registro e tente novamente.");
                return false;
            }

            preencherProdutoComModel(produto, model);

            Produtos resultado = isEdit ? produto.update() : produto.insert();

            if (resultado != null && !resultado.hasError()) {
                Core.setMessageSuccess(isEdit ? "Produto atualizado com sucesso!" : "Produto cadastrado com sucesso!");
                return true;
            }

            String detalheErro = (resultado != null && resultado.getError() != null)
                    ? resultado.getError().toString()
                    : "Erro desconhecido";
            Core.setMessageError("Não foi possível salvar o produto: " + detalheErro);
            return false;

        } catch (Exception e) {
            Core.setMessageError("Erro inesperado ao salvar o produto: " + e.getMessage());
            return false;
        }
    }

    // -------------------------------------------------------------------------
    // Privado — auxiliares de mapeamento
    // -------------------------------------------------------------------------

    /**
     * Busca um produto pelo ID recebido como String.
     * Retorna {@code null} se o ID for inválido ou o produto não existir.
     */
    private static Produtos buscarProdutoPorId(String id) {
        int idInt = Core.toInt(id);
        if (idInt <= 0) {
            return null;
        }
        return new Produtos().findOne(idInt);
    }

    /**
     * Preenche o {@link Cadastro_de_produto} (model de tela) com os dados do {@link Produtos} (DAO).
     */
    private static void preencherModelComProduto(Cadastro_de_produto model, Produtos produto) {
        model.setNome(produto.getNome());
        model.setEstoque(produto.getEstoque());

        if (produto.getPrecoVenda() != null) {
            model.setPreco_venda(produto.getPrecoVenda().intValue());
        }
        if (produto.getPrecoCompra() != null) {
            model.setPreco_compra(produto.getPrecoCompra().intValue());
        }

        model.setCategoria(produto.getCategoriaId() != null
                ? String.valueOf(produto.getCategoriaId())
                : "");
    }

    /**
     * Preenche o {@link Produtos} (DAO) com os dados do {@link Cadastro_de_produto} (model de tela).
     */
    private static void preencherProdutoComModel(Produtos produto, Cadastro_de_produto model) {
        produto.setNome(model.getNome());
        produto.setEstoque(model.getEstoque());
        produto.setPrecoVenda(toDouble(model.getPreco_venda()));
        produto.setPrecoCompra(toDouble(model.getPreco_compra()));
        produto.setAtivo(true);

        // Descrição é obrigatória na entidade; mantida vazia até que o formulário a suporte
        if (produto.getDescricao() == null) {
            produto.setDescricao("");
        }

        if (Core.isNotNull(model.getCategoria())) {
            produto.setCategoriaId(Core.toInt(model.getCategoria()));
        }
    }

    /**
     * Converte um {@link Integer} nullable para {@code double}, retornando {@code 0.0} se nulo.
     */
    private static double toDouble(Integer value) {
        return value != null ? value.doubleValue() : 0.0;
    }
}
