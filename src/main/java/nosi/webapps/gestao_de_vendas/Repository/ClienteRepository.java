package nosi.webapps.gestao_de_vendas.Repository;

import nosi.core.data.repository.IGRPCrudRepository;
import nosi.webapps.gestao_de_vendas.dao.Clientes;
import org.hibernate.Session;

/**
 * Repositório personalizado para entidade Clientes.
 * Extende IGRPCrudRepository para operações CRUD padrão.
 */
public class ClienteRepository extends IGRPCrudRepository<Clientes, Integer> {

    public ClienteRepository(Session session) {
        super(session);
    }

    // --- Métodos personalizados podem ser adicionados aqui no futuro ---
    // Exemplo:
    // public List<Clientes> findByNome(String nome) {
    //     return session.createQuery("FROM Clientes c WHERE LOWER(c.nome) LIKE LOWER(:nome)", Clientes.class)
    //                   .setParameter("nome", "%" + nome + "%")
    //                   .getResultList();
    // }
}
