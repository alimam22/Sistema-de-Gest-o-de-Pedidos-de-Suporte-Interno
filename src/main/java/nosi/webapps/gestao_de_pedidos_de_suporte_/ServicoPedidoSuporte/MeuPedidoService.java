package nosi.webapps.gestao_de_pedidos_de_suporte_.ServicoPedidoSuporte;

import nosi.core.webapp.Core;
import nosi.webapps.gestao_de_pedidos_de_suporte_.RepositoryPedidoSuporte.MeuPedidoRepository;
import nosi.webapps.gestao_de_pedidos_de_suporte_.dao.PedidoSuporte;

public class MeuPedidoService {

    private final MeuPedidoRepository repository;

    public MeuPedidoService() {
        this.repository = new MeuPedidoRepository();
    }

    /**
     * Busca pedido pelo ID de forma segura.
     */
    public PedidoSuporte buscarPorId(Integer id) {
        if (id == null) return null;
        return repository.findById(id);
    }

    /**
     * Lógica de Aceitação/Progresso:
     * ATRIBUIDO  -> EM_ANALISE
     * EM_ANALISE -> EM_EXECUCAO
     * REABERTO   -> ATRIBUIDO
     */
    public boolean aceitar(Integer pedidoId) {
        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) {
            Core.setMessageError("Pedido não encontrado.");
            return false;
        }

        // Normalização do estado para comparação segura
        String estado = (pedido.getEstado() != null) ? pedido.getEstado().trim().toUpperCase() : "";

        if ("ATRIBUIDO".equals(estado)) {
            return repository.atualizarEstado(pedido, "EM_ANALISE");
        }

        if ("EM_ANALISE".equals(estado)) {
            return repository.atualizarEstado(pedido, "EM_EXECUCAO");
        }

        if ("REABERTO".equals(estado)) {
            return repository.atualizarEstado(pedido, "ATRIBUIDO");
        }

        Core.setMessageWarning("O pedido já está em processamento ou num estado que não permite 'Aceitar'. Estado: " + estado);
        return false;
    }

    /**
     * Finalização do Pedido:
     * EM_EXECUCAO -> CONCLUIDO
     */
    public boolean resolver(Integer pedidoId, String parecerTecnico) {
        if (Core.isNull(parecerTecnico)) {
            Core.setMessageError("O parecer técnico (observação) é obrigatório para concluir o pedido.");
            return false;
        }

        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) return false;

        String estado = (pedido.getEstado() != null) ? pedido.getEstado().trim().toUpperCase() : "";

        // Opcional: Validar se só pode resolver se estiver EM_EXECUCAO
        if (!"EM_EXECUCAO".equals(estado)) {
            Core.setMessageWarning("Apenas pedidos 'Em Execução' podem ser resolvidos diretamente.");
            // Se quiser permitir resolver de qualquer estado, comente o 'return false' abaixo
            // return false;
        }

        return repository.atualizarEstadoComResolucao(pedido, "CONCLUIDO", parecerTecnico);
    }

    /**
     * Rejeição do Pedido:
     * Qualquer estado ativo -> REJEITADO
     */
    public boolean rejeitar(Integer pedidoId, String observacao) {
        if (Core.isNull(observacao)) {
            Core.setMessageError("Deve indicar o motivo da rejeição nas observações.");
            return false;
        }

        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) return false;

        String estado = (pedido.getEstado() != null) ? pedido.getEstado().trim().toUpperCase() : "";

        // Impede rejeitar algo que já foi finalizado
        if ("CONCLUIDO".equals(estado) || "CANCELADO".equals(estado)) {
            Core.setMessageError("Não é possível rejeitar um pedido que já foi " + estado);
            return false;
        }

        return repository.atualizarEstadoComObservacao(pedido, "REJEITADO", observacao);
    }

    /**
     * Cancelamento do Pedido:
     * ABERTO | ATRIBUIDO | REABERTO -> CANCELADO
     */
    public boolean cancelar(Integer pedidoId) {
        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) return false;

        String estado = (pedido.getEstado() != null) ? pedido.getEstado().trim().toUpperCase() : "";

        if ("ABERTO".equals(estado) || "ATRIBUIDO".equals(estado) || "REABERTO".equals(estado)) {
            return repository.atualizarEstado(pedido, "CANCELADO");
        }

        Core.setMessageError("Este pedido não pode ser cancelado no estado atual: " + estado);
        return false;
    }

    /**
     * Método genérico para atualizações administrativas de estado.
     */
    public boolean atualizarEstadoManual(Integer pedidoId, String novoEstado) {
        if (Core.isNull(novoEstado)) return false;

        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) return false;

        return repository.atualizarEstado(pedido, novoEstado.trim().toUpperCase());
    }

    public boolean reabrir(Integer pedidoId) {
        PedidoSuporte pedido = repository.findById(pedidoId);
        if (pedido == null) return false;

        // Regra: Ao reabrir, limpa o técnico para nova atribuição
        pedido.setTecnicoId(null);
        return repository.atualizarEstado(pedido, "REABERTO");
    }
}