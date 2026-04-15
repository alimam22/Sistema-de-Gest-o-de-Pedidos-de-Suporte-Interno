package nosi.webapps.gestao_de_vendas.Repository;

import nosi.core.data.repository.IGRPCrudRepository;
import nosi.webapps.gestao_de_vendas.dao.Produtos; // Certifica-te que a DAO se chama Produtos
import org.hibernate.Session;

public class ProdutoRepository extends IGRPCrudRepository<Produtos, Integer> {

    public ProdutoRepository(Session session) {
        super(session);
    }

}