# Sistema de Gestão de Pedidos de Suporte Interno

Sistema de gestão de tickets de suporte técnico desenvolvido com o framework IGRP (Integrated Governance and Reporting Platform).

## 🎯 Funcionalidades Principais

### Gestão de Pedidos
- Criação de pedidos de suporte com prioridade (Alta, Média, Baixa)
- Workflow completo de estados do pedido
- Atribuição automática e manual de técnicos
- Histórico de todas as alterações de estado

### Sistema de Comentários
- Qualquer utilizador envolvido pode adicionar comentários
- Apenas Gestores podem eliminar comentários
- Visualização com data e autor

### Dashboard
- Estatísticas em tempo real
- Gráfico de distribuição por estado
- Cards de resumo (Total, Abertos, Em Andamento, Concluídos)

### Gestão de Entidades
- **Departamentos**: Criação e gestão com validação de pedidos ativos
- **Categorias**: Classificação dos pedidos

## 👥 Perfis de Utilizador

| Perfil | Permissões |
|--------|-----------|
| **Gestor** | Acesso total, pode eliminar comentários, gerir departamentos e categorias |
| **Técnico** | Atender pedidos atribuídos, adicionar comentários |
| **Solicitante** | Criar pedidos, visualizar estado, adicionar comentários |

## 🔄 Fluxo de Estados

```
ABERTO → ATRIBUIDO → EM_ANALISE → EM_EXECUCAO → CONCLUIDO
   ↓         ↓            ↓            ↓
CANCELADO  REJEITADO   REABERTO
```

### Transições Permitidas:
- **ABERTO** → ATRIBUIDO, CANCELADO
- **ATRIBUIDO** → EM_ANALISE, REJEITADO
- **EM_ANALISE** → EM_EXECUCAO, REJEITADO
- **EM_EXECUCAO** → CONCLUIDO, REJEITADO
- **REJEITADO** → REABERTO
- **REABERTO** → ATRIBUIDO

## 🛠️ Tecnologias

- **Backend**: Java 17
- **Framework**: IGRP Core
- **Persistência**: JPA/Hibernate
- **Base de Dados**: PostgreSQL
- **Servidor**: TomEE Plus 9.1.3
- **Build**: Maven

## 📁 Estrutura do Projeto

```
src/main/java/nosi/webapps/gestao_de_pedidos_de_suporte_/
├── dao/                    # Entidades JPA
├── pages/                  # Controllers e Views
├── PageHelper/            # Helpers de página
├── ServicoPedidoSuporte/  # Serviços de negócio
├── RepositoryPedidoSuporte/ # Repositórios de dados
└── enums/                 # Enums (Estados, etc.)
```

## 🚀 Como Executar

### Pré-requisitos
- Java 17
- Maven 3.8+
- PostgreSQL 13+
- TomEE Plus 9.1.3

### Configuração da Base de Dados
1. Criar base de dados PostgreSQL
2. Configurar credenciais no ficheiro de propriedades
3. Executar migrações Flyway

### Compilar e Executar
```bash
mvn clean install
cp target/IGRP.war [TOMEE]/webapps/
```

## 📝 Notas de Implementação

### Segurança
- Validação de permissões por perfil em todas as operações
- Solicitantes apenas visualizam os seus próprios pedidos
- Pedidos só podem ser eliminados no estado ABERTO

### Validações
- Departamentos com pedidos ativos mostram aviso na edição
- Comentários vazios não são permitidos
- Transições de estado seguem o fluxo definido

## 📄 Licença

Projeto desenvolvido para fins educacionais.

## 👤 Autor

**Alimamy Sesay** - [GitHub](https://github.com/alimam22)
