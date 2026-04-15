package nosi.webapps.gestao_de_vendas.PageHelperPaginaCliente;

import java.util.Optional; // Import necessário para o findById
import nosi.core.webapp.Core;
import nosi.core.webapp.Controller;
import nosi.webapps.gestao_de_vendas.dao.Clientes;
import nosi.webapps.gestao_de_vendas.Repository.ClienteRepository;
import org.hibernate.Session;

public class PageHelperPaginaClienteController {

    /**
     * Valida se o ID existe e prepara a URL de redirecionamento
     */
    public static boolean prepararEdicao(Controller ctrl) {
        // 1. Resolve o ID que veio do clique na tabela (p_id_tbl_cliente)
        String id = resolverIdTabela("p_id_tbl_cliente");

        if (Core.isNull(id)) {
            Core.setMessageError("Selecione um cliente na tabela primeiro.");
            return false;
        }

        // 2. Abre a sessão do Hibernate para consultar o Repositório
        try (Session session = Core.getSession(Core.defaultConnection())) {
            ClienteRepository repository = new ClienteRepository(session);

            // O findById do IGRPCrudRepository retorna um Optional
            Optional<Clientes> clienteOptional = repository.findById(Core.toInt(id));

            if (clienteOptional.isPresent()) {
                // Adiciona o ID na QueryString para que o redirect saiba quem editar
                ctrl.addQueryString("p_id", id);
                // "p_id" é o parâmetro que o seu Cadastro_de_clienteController espera no actionIndex
                return true;
            } else {
                Core.setMessageError("O cliente selecionado não existe ou foi removido.");
            }
        } catch (Exception e) {
            Core.setMessageError("Erro técnico ao validar edição: " + e.getMessage());
        }

        return false;
    }

    /**
     * Captura o ID da linha da tabela de forma segura
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
}