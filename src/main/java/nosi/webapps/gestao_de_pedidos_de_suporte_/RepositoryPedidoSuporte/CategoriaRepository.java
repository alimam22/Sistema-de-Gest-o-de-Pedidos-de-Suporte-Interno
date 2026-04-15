package nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte;

import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.CategoriaSuporte;
import nosi.webapps.gestao_de_pedidos_de_suporte_.pages.pagina_categoria.Pagina_categoria;
import java.util.List;
import java.util.ArrayList;

public class CategoriaRepository {

    // Substitua "db_pedidos" pelo nome da sua conexão no IGRP
    private static final String CONNECTION_NAME = "db_pedidos";

    public void loadTabela(Pagina_categoria model) {
        List<CategoriaSuporte> lista = new CategoriaSuporte()
                .find()
                .andWhere("activo", "=", 1) // Opcional: filtrar apenas ativos
                .all();

        List<Pagina_categoria.Table_1> tabela = new ArrayList<>();
        if (lista != null) {
            for (CategoriaSuporte c : lista) {
                Pagina_categoria.Table_1 row = new Pagina_categoria.Table_1();
                row.setNome_categoria(c.getNome());
                row.setDescricao_categoria(c.getDescricao());
                row.setActivo_categoria(c.getActivo() == 1 ? "Sim" : "Não");
                row.setData_categoria(c.getDataCriacao() != null ? c.getDataCriacao().toString() : "");
                row.setTbl_categoria_id("" + c.getId()); // ID para as ações de Edit/Delete
                tabela.add(row);
            }
        }
        model.setTable_1(tabela);
    }

    public List<CategoriaSuporte> findAll() {
        try {
            return new CategoriaSuporte().findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean existsByNome(String nome, Integer id) {
        // Se o nome for null ou vazio, não fazer a verificação
        if (Core.isNull(nome) || nome.trim().isEmpty()) {
            return false;
        }

        // HQL: CategoriaSuporte é o nome da CLASSE, não da tabela
        var query = new CategoriaSuporte()
                .find()
                .andWhere("nome", "=", nome.trim());

        if (Core.isNotNull(id)) {
            query.andWhere("id", "<>", id);
        }

        return query.getCount() > 0;
    }

    public CategoriaSuporte findById(Integer id) {
        return new CategoriaSuporte().findOne(id);
    }

    public CategoriaSuporte save(CategoriaSuporte categoria) {
        if (categoria.getId() != null) {
            return categoria.update();
        } else {
            return categoria.insert();
        }
    }

    public void delete(Integer id) {
        new CategoriaSuporte().delete(id);
    }
}
