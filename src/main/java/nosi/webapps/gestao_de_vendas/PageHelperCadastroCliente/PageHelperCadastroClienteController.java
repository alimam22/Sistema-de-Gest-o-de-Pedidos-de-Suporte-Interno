package nosi.webapps.gestao_de_vendas.PageHelperCadastroCliente;

import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_vendas.dao.Clientes;
import nosi.webapps.gestao_de_vendas.pages.cadastro_de_cliente.Cadastro_de_cliente;
import nosi.webapps.gestao_de_vendas.pages.cadastro_de_cliente.Cadastro_de_clienteView;

/**
 * Helper para processar lógica de negócio do cadastro de clientes.
 * Separa responsabilidades entre controller e modelo de dados.
 */
public class PageHelperCadastroClienteController {

    // Prepara o formulário para edição ou novo registro
    public static void handleIndex(Cadastro_de_cliente model, Cadastro_de_clienteView view) {
        String id = Core.getParam("p_id");

        if (Core.isNotNull(id)) {
            Clientes cliente = new Clientes().findOne(Core.toInt(id));
            if (cliente != null && Core.isNull(model.getNome())) {
                model.setNome(cliente.getNome());
                model.setEmail(cliente.getEmail());
                model.setTelefone(cliente.getTelefone());
                model.setEndereco(cliente.getEndereco());
                model.setNif(cliente.getNif());
                model.setCidade(cliente.getCidade());
            }
        }
    }

    // Processa o salvamento e retorna TRUE se deu certo
    public static boolean processarSalva(Cadastro_de_cliente model) {
        if (!model.validate()) {
            Core.setMessageError("Por favor, preencha os campos obrigatórios.");
            return false;
        }

        try {
            String id = Core.getParam("p_id");
            Clientes cliente = new Clientes();

            if (Core.isNotNull(id)) {
                cliente = new Clientes().findOne(Core.toInt(id));
                if (cliente == null) {
                    Core.setMessageError("Registro não encontrado.");
                    return false;
                }
            }

            // Mapeamento
            cliente.setNome(model.getNome());
            cliente.setEmail(model.getEmail());
            cliente.setTelefone(model.getTelefone());
            cliente.setEndereco(model.getEndereco());
            cliente.setNif(model.getNif());
            cliente.setCidade(model.getCidade());

            // Persistência
            cliente = Core.isNotNull(id) ? cliente.update() : cliente.insert();

            if (cliente != null && !cliente.hasError()) {
                Core.setMessageSuccess(Core.isNotNull(id) ? "Atualizado com sucesso!" : "Cadastrado com sucesso!");
                return true;
            } else {
                Core.setMessageError("Erro: " + (cliente != null ? cliente.getError() : "Erro na base de dados"));
                return false;
            }
        } catch (Exception e) {
            Core.setMessageError("Erro técnico: " + e.getMessage());
            return false;
        }
    }
}
