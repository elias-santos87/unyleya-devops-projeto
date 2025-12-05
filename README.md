🚀 Unyleya DevOps - Pipeline CI/CD com Docker
👨‍💻 Informações do Aluno
Nome: [SEU NOME COMPLETO]
RA: [SEU RA]
Curso: Engenheiro DevOps
Disciplina: Unidade 2
Instituição: Unyleya
Data: [DATA ATUAL]

📝 Descrição do Projeto
Este projeto implementa uma pipeline CI/CD completa utilizando Jenkins, SonarQube e Docker, conforme especificado na atividade prática da disciplina Engenheiro DevOps.

🎯 Objetivos Alcançados
✅ Pipeline automatizada de integração contínua (CI)
✅ Pipeline automatizada de entrega contínua (CD)
✅ Análise de qualidade de código com SonarQube
✅ Quality Gate implementado
✅ Testes automatizados (5 testes)
✅ Deploy automático em DESENVOLVIMENTO
✅ Aprovação manual para PRODUÇÃO
✅ Aplicação web responsiva
✅ Ambientes isolados (DEV e PROD)
✅ Infraestrutura completa com Docker
🏗️ Arquitetura da Solução
┌─────────────┐
│   GitHub    │  ← Repositório de Código
└──────┬──────┘
       │
       ↓
┌──────────────────────────────┐
│       JENKINS CI/CD          │
│  ┌────────────────────────┐ │
│  │  Pipeline Unificada    │ │
│  │  (12 Stages)           │ │
│  └───────┬────────────────┘ │
│          │                   │
│          ↓                   │
│    ┌──────────┐             │
│    │SonarQube │             │
│    │  Quality │             │
│    │   Gate   │             │
│    └──────────┘             │
└──────────────────────────────┘
       │
       ↓
┌────────────────────────────┐
│   DOCKER CONTAINERS        │
│  ┌──────────────────────┐ │
│  │  Nginx DEV:8081      │ │  ← http://localhost:8081
│  └──────────────────────┘ │
│  ┌──────────────────────┐ │
│  │  Nginx PROD:8082     │ │  ← http://localhost:8082
│  └──────────────────────┘ │
└────────────────────────────┘
🛠️ Tecnologias Utilizadas
Tecnologia	Versão	Função
Docker	Latest	Containerização
Jenkins	LTS	Automação CI/CD
SonarQube	Community	Análise de código
Maven	3.9+	Build e testes
Java	11	Linguagem
JUnit	4.13.2	Testes unitários
Nginx	Alpine	Servidor web
Git	Latest	Versionamento
📂 Estrutura do Projeto
unyleya-devops-projeto/
├── docker-compose.yml           # Infraestrutura Docker
├── Jenkinsfile                  # Pipeline CI/CD unificada
├── pom.xml                      # Configuração Maven
├── README.md                    # Este arquivo
├── COMANDOS-GIT.txt            # Documentação Git
├── workspace/
│   ├── dev/                    # Deploy desenvolvimento
│   └── prod/                   # Deploy produção
├── src/
│   ├── main/
│   │   └── webapp/
│   │       └── index.html      # Aplicação web
│   └── test/
│       └── java/
│           └── br/com/unyleya/
│               └── AppTest.java # Testes
└── target/                      # Build artifacts
🔄 Pipeline CI/CD
Stages da Pipeline
#	Stage	Descrição
1	Informações	Exibe dados da pipeline
2	Checkout	Obtém código do repositório
3	Limpeza	Limpa builds anteriores
4	Compilação	Compila o projeto
5	Testes	Executa testes unitários
6	SonarQube	Análise de qualidade
7	Quality Gate	Valida padrões
8	Empacotamento	Gera WAR
9	Deploy DEV	Deploy em desenvolvimento
10	Testes DEV	Smoke tests
11	Aprovação	⚠️ APROVAÇÃO MANUAL
12	Deploy PROD	Deploy em produção
13	Validação	Verifica integridade
🚀 Como Executar o Projeto
Pré-requisitos
Docker instalado
Docker Compose instalado
Git instalado
Portas disponíveis: 8080, 8081, 8082, 9000
1. Clonar Repositório
bash
git clone https://github.com/SEU-USUARIO/unyleya-devops-projeto.git
cd unyleya-devops-projeto
2. Subir Infraestrutura
bash
# Subir todos os containers
docker-compose up -d

# Aguardar inicialização (60 segundos)
sleep 60

# Verificar status
docker-compose ps
3. Configurar Jenkins
Acesse: http://localhost:8080
Obtenha senha:
bash
   docker exec jenkins-unyleya cat /var/jenkins_home/secrets/initialAdminPassword
Instale plugins sugeridos
Crie usuário admin / admin123
Configure Maven em Tools
Configure SonarQube em System
4. Configurar SonarQube
Acesse: http://localhost:9000
Login: admin / admin
Troque senha para: admin123
Gere token em Security
Configure no Jenkins
5. Criar Job no Jenkins
New Item > Pipeline
Nome: [SEUNOME]_Pipeline_DevOps
Pipeline from SCM > Git
URL: seu repositório GitHub
Script Path: Jenkinsfile
Save
6. Executar Pipeline
Clique em "Build Now"
Aguarde execução
Aprove deploy em produção quando solicitado
Verifique aplicações:
DEV: http://localhost:8081
PROD: http://localhost:8082
🧪 Testes Implementados
Suite de Testes (AppTest.java)
java
1. testAppName()         // Valida nome da aplicação
2. testVersion()         // Verifica formato da versão
3. testEnvironment()     // Testa ambientes
4. testPipeline()        // Valida stages da pipeline
5. testTechnologies()    // Verifica tecnologias
Resultado: Todos os 5 testes devem passar ✅

📊 Quality Gate (SonarQube)
Métricas Avaliadas
Bugs: 0 tolerados
Vulnerabilities: 0 toleradas
Code Smells: Mínimo
Coverage: > 0%
Duplications: < 3%
🔐 Aprovação Manual
A pipeline requer aprovação manual antes do deploy em produção:

⚠️ Stage "Aprovação para PRODUÇÃO" aguarda input
✅ Somente usuário admin pode aprovar
🛡️ Garante controle sobre releases
📋 Validação de critérios de negócio
🌐 Acessando as Aplicações
Ambiente de DESENVOLVIMENTO
URL: http://localhost:8081
Badge: 🔧 DESENVOLVIMENTO (amarelo)
Deploy: Automático após testes
Objetivo: Validação e testes
Ambiente de PRODUÇÃO
URL: http://localhost:8082
Badge: ✅ PRODUÇÃO (verde)
Deploy: Manual com aprovação
Objetivo: Ambiente estável
📸 Evidências Necessárias
Checklist de Prints
 1. Pipeline com nome do aluno visível
 2. Todos stages em verde (sucesso)
 3. Logs do SonarQube na execução
 4. Quality Gate aprovado
 5. Dashboard SonarQube
 6. Tela de aprovação manual
 7. Log final mostrando SUCCESS
 8. App em DEV (porta 8081)
 9. App em PROD (porta 8082)
 10. Arquivo COMANDOS-GIT.txt
📝 Comandos Git Utilizados
Todos os comandos estão documentados em COMANDOS-GIT.txt

Principais Comandos
bash
# Inicialização
git init
git config user.name "Seu Nome"
git config user.email "seu.email@example.com"

# Adicionar arquivos
git add .
git status

# Commit
git commit -m "feat: implementa pipeline CI/CD completa"

# Conectar ao GitHub
git remote add origin https://github.com/SEU-USUARIO/unyleya-devops-projeto.git
git branch -M main

# Push
git push -u origin main

# Verificar
git log --oneline
🐛 Troubleshooting
Jenkins não inicia
bash
docker-compose restart jenkins
docker logs -f jenkins-unyleya
SonarQube não responde
bash
# Aguardar mais tempo (pode levar 2-3 minutos)
docker logs -f sonarqube-unyleya
Aplicação não aparece
bash
# Verificar se deploy foi feito
ls -la workspace/dev/
ls -la workspace/prod/

# Reiniciar containers web
docker-compose restart webapp-dev webapp-prod
Porta em uso
bash
# Verificar portas
sudo lsof -i :8080
sudo lsof -i :8081
sudo lsof -i :8082
sudo lsof -i :9000

# Parar containers
docker-compose down
🛑 Parar Tudo
bash
# Parar containers
docker-compose down

# Parar e remover volumes (CUIDADO: apaga dados)
docker-compose down -v

# Limpar tudo
docker system prune -a
📚 Referências
Jenkins Documentation
SonarQube Docs
Docker Documentation
Maven Guide
👨‍💼 Autor
[SEU NOME COMPLETO]

GitHub: @SEU-USUARIO
Email: seu.email@example.com
RA: [SEU RA]
📄 Licença
Projeto desenvolvido para fins educacionais - Unyleya 2024

✅ Status do Projeto
Status: ✅ CONCLUÍDO
Versão: 1.0.0
Data: [DATA ATUAL]

🎓 Agradecimentos
Professor(a) da disciplina
Unyleya - Centro Universitário
Comunidade DevOps
Desenvolvido com ❤️ para a disciplina de Engenheiro DevOps

