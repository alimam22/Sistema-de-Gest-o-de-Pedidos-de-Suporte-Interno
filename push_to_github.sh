#!/bin/bash

# Script para enviar o projeto IGRP para o GitHub
# Execute este script no terminal

# Configurações do repositório
GITHUB_URL="https://github.com/alimam22/Sistema-de-Gest-o-de-Pedidos-de-Suporte-Interno.git"

echo "=== Enviando projeto IGRP para GitHub ==="
echo ""

# Verificar se estamos na pasta correta
if [ ! -f "pom.xml" ]; then
    echo "ERRO: Execute este script na pasta raiz do projeto (onde está o pom.xml)"
    exit 1
fi

# Inicializar repositório Git (se não existir)
if [ ! -d ".git" ]; then
    echo "1. Inicializando repositório Git..."
    git init
else
    echo "1. Repositório Git já existe"
fi

# Configurar .gitignore (se não existir)
if [ ! -f ".gitignore" ]; then
    echo "2. Criando .gitignore..."
    cat > .gitignore << 'EOF'
# Compiled class files
*.class

# Log files
*.log

# Package Files
*.jar
*.war
*.ear

# Maven
target/
!.mvn/wrapper/maven-wrapper.jar
!**/src/main/**/target/
!**/src/test/**/target/

# IDE - IntelliJ IDEA
.idea/
*.iws
*.iml
*.ipr

# IDE - Eclipse
.apt_generated
.classpath
.factorypath
.project
.settings/
.springBeans
.sts4-cache

# IDE - NetBeans
/nbproject/private/
/nbbuild/
/dist/
/nbdist/
/.nb-gradle/

# VS Code
.vscode/

# OS
.DS_Store
Thumbs.db

# Application specific
*.tmp
*.bak
*.swp
*~

# Environment
.env
.env.local

# Node (se houver)
node_modules/
npm-debug.log*
yarn-debug.log*
yarn-error.log*
EOF
else
    echo "2. .gitignore já existe"
fi

# Adicionar todos os arquivos
echo "3. Adicionando arquivos ao Git..."
git add .

# Commit inicial
echo "4. Criando commit..."
git commit -m "Initial commit: IGRP Support Ticket Management System

Features implemented:
- State machine workflow (ABERTO, ATRIBUIDO, EM_ANALISE, EM_EXECUCAO, CONCLUIDO, CANCELADO, REJEITADO)
- User roles (GESTOR, TECNICO, SOLICITANTE)
- Ticket CRUD operations
- Comment system with CRUD
- Department and Category management
- Dashboard with statistics
- Technician assignment workflow"

# Adicionar remote do GitHub
echo "5. Configurando remote do GitHub..."
git remote remove origin 2>/dev/null
git remote add origin $GITHUB_URL

# Enviar para GitHub
echo "6. Enviando para GitHub..."
echo "URL: $GITHUB_URL"
git branch -M main
git push -u origin main

echo ""
echo "=== Concluído! ==="
echo "Verifique seu repositório em: https://github.com/$GITHUB_USERNAME/$GITHUB_REPO"
