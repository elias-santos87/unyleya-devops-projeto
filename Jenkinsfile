pipeline {
    agent any
    
    environment {
        // ⚠️ SUBSTITUA "JoaoSilva" PELO SEU NOME!
        PIPELINE_OWNER = 'EliasSantos'
        PROJECT_NAME = 'Unyleya_DevOps_Project'
        SONAR_PROJECT_KEY = 'unyleya-devops'
        WORKSPACE_DEV = '/workspace/dev'
        WORKSPACE_PROD = '/workspace/prod'
    }
    
    tools {
        maven 'Maven'
    }
    
    stages {
        stage('📋 Informações Iniciais') {
            steps {
                script {
                    echo '═══════════════════════════════════════════════════════════'
                    echo "🚀 PIPELINE CI/CD - ${PIPELINE_OWNER}"
                    echo '═══════════════════════════════════════════════════════════'
                    echo "📦 Projeto: ${PROJECT_NAME}"
                    echo "👤 Owner: ${PIPELINE_OWNER}"
                    echo "🏗️  Build: #${env.BUILD_NUMBER}"
                    echo "📅 Data: ${new Date().format('dd/MM/yyyy HH:mm:ss')}"
                    echo '═══════════════════════════════════════════════════════════'
                }
            }
        }
        
        stage('📥 1. Checkout') {
            steps {
                echo '➤ Obtendo código fonte...'
                checkout scm
                sh 'ls -la'
                echo '✓ Código obtido com sucesso!'
            }
        }
        
        stage('🧹 2. Limpeza') {
            steps {
                echo '➤ Limpando builds anteriores...'
                sh 'mvn clean'
                echo '✓ Limpeza concluída!'
            }
        }
        
        stage('🔨 3. Compilação') {
            steps {
                echo '➤ Compilando aplicação...'
                sh 'mvn compile'
                echo '✓ Compilação bem-sucedida!'
            }
        }
        
        stage('🧪 4. Testes Unitários') {
            steps {
                echo '➤ Executando testes automatizados...'
                sh 'mvn test'
                echo '✓ Todos os testes passaram!'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('📊 5. Análise SonarQube') {
            steps {
                echo '➤ Executando análise de código no SonarQube...'
                withSonarQubeEnv('SonarQube') {
                    sh """
                        mvn sonar:sonar \
                          -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                          -Dsonar.projectName="${PIPELINE_OWNER} - ${PROJECT_NAME}" \
                          -Dsonar.sources=src/main \
                          -Dsonar.tests=src/test \
                          -Dsonar.java.binaries=target/classes
                    """
                }
                echo '✓ Análise enviada ao SonarQube!'
            }
        }
        
        stage('✅ 6. Quality Gate') {
            timeout(time: 15, unit: 'MINUTES') {
        waitForQualityGate abortPipeline: true
    }
}
        
        stage('📦 7. Empacotamento') {
            steps {
                echo '➤ Gerando pacote WAR...'
                sh 'mvn package -DskipTests'
                sh 'ls -lh target/*.war'
                echo '✓ Pacote gerado com sucesso!'
            }
        }
        
        stage('🚀 8. Deploy DESENVOLVIMENTO') {
            steps {
                echo '➤ Fazendo deploy no ambiente de DESENVOLVIMENTO...'
                script {
                    sh """
                        echo '  → Preparando diretório DEV...'
                        rm -rf ${WORKSPACE_DEV}/*
                        mkdir -p ${WORKSPACE_DEV}
                        
                        echo '  → Extraindo aplicação...'
                        unzip -o target/app.war -d ${WORKSPACE_DEV}
                        
                        echo '  → Verificando arquivos...'
                        ls -la ${WORKSPACE_DEV}
                        
                        if [ -f "${WORKSPACE_DEV}/index.html" ]; then
                            echo '  ✓ Aplicação deployada com sucesso!'
                        else
                            echo '  ✗ ERRO: index.html não encontrado!'
                            exit 1
                        fi
                    """
                }
                echo '✓ Deploy em DESENVOLVIMENTO concluído!'
                echo '🌐 Acesse: http://localhost:8081'
            }
        }
        
        stage('🧪 9. Testes no DEV') {
            steps {
                echo '➤ Executando smoke tests em DEV...'
                script {
                    sh """
                        echo '  → Teste 1: Verificando index.html...'
                        if [ -f "${WORKSPACE_DEV}/index.html" ]; then
                            echo '    ✓ Arquivo encontrado'
                        else
                            exit 1
                        fi
                        
                        echo '  → Teste 2: Verificando conteúdo...'
                        if grep -q "Unyleya DevOps" "${WORKSPACE_DEV}/index.html"; then
                            echo '    ✓ Conteúdo válido'
                        else
                            exit 1
                        fi
                        
                        echo '  → Aguardando aplicação ficar disponível...'
                        sleep 3
                    """
                }
                echo '✓ Smoke tests APROVADOS!'
            }
        }
        
        stage('🔐 10. Aprovação para PRODUÇÃO') {
            steps {
                script {
                    echo '═══════════════════════════════════════════════════════════'
                    echo '⚠️  APROVAÇÃO MANUAL NECESSÁRIA'
                    echo '═══════════════════════════════════════════════════════════'
                    echo ''
                    echo '✓ Build completado com sucesso'
                    echo '✓ Testes aprovados'
                    echo '✓ Quality Gate OK'
                    echo '✓ Deploy em DEV realizado'
                    echo ''
                    echo '🌐 Verifique em: http://localhost:8081'
                    echo ''
                    echo '❓ Deseja fazer deploy em PRODUÇÃO?'
                    echo '═══════════════════════════════════════════════════════════'
                    
                    input(
                        message: '⚠️  Aprovar deploy em PRODUÇÃO?',
                        ok: '✅ SIM, fazer deploy em PRODUÇÃO',
                        submitter: 'admin'
                    )
                }
                echo '✓ Deploy em PRODUÇÃO APROVADO!'
            }
        }
        
        stage('🎯 11. Deploy PRODUÇÃO') {
            steps {
                echo '➤ Fazendo deploy no ambiente de PRODUÇÃO...'
                script {
                    sh """
                        echo '  → Criando backup...'
                        if [ -d "${WORKSPACE_PROD}" ]; then
                            tar -czf ${WORKSPACE_PROD}_backup_\$(date +%Y%m%d_%H%M%S).tar.gz ${WORKSPACE_PROD} 2>/dev/null || true
                        fi
                        
                        echo '  → Preparando diretório PROD...'
                        rm -rf ${WORKSPACE_PROD}/*
                        mkdir -p ${WORKSPACE_PROD}
                        
                        echo '  → Extraindo aplicação...'
                        unzip -o target/app.war -d ${WORKSPACE_PROD}
                        
                        echo '  → Verificando arquivos...'
                        ls -la ${WORKSPACE_PROD}
                        
                        if [ -f "${WORKSPACE_PROD}/index.html" ]; then
                            echo '  ✓ Aplicação deployada com sucesso!'
                        else
                            echo '  ✗ ERRO: index.html não encontrado!'
                            exit 1
                        fi
                    """
                }
                echo '✓ Deploy em PRODUÇÃO concluído!'
                echo '🌐 Acesse: http://localhost:8082'
            }
        }
        
        stage('🔍 12. Validação Final') {
            steps {
                echo '➤ Validando deploy em PRODUÇÃO...'
                script {
                    sh """
                        echo '  → Verificando integridade...'
                        if [ -f "${WORKSPACE_PROD}/index.html" ]; then
                            echo '    ✓ Aplicação encontrada'
                        else
                            exit 1
                        fi
                        
                        echo '  → Aguardando estabilização...'
                        sleep 3
                        
                        echo '  → Validando conteúdo...'
                        if grep -q "Unyleya DevOps" "${WORKSPACE_PROD}/index.html"; then
                            echo '    ✓ Conteúdo válido'
                        else
                            exit 1
                        fi
                    """
                }
                echo '✓ Validação final APROVADA!'
            }
        }
    }
    
    post {
        success {
            script {
                echo '''
                ═══════════════════════════════════════════════════════════
                ✅ ✅ ✅  PIPELINE EXECUTADA COM SUCESSO!  ✅ ✅ ✅
                ═══════════════════════════════════════════════════════════
                
                🎉 PARABÉNS! Deploy concluído em todos os ambientes!
                
                📱 Aplicações disponíveis:
                   🔧 DEV:  http://localhost:8081
                   ✅ PROD: http://localhost:8082
                
                ✓ Código compilado e testado
                ✓ Quality Gate aprovado (SonarQube)
                ✓ Deploy em desenvolvimento OK
                ✓ Deploy em produção OK
                ✓ Validações finais OK
                
                ═══════════════════════════════════════════════════════════
                '''
            }
        }
        failure {
            echo '''
            ═══════════════════════════════════════════════════════════
            ❌ PIPELINE FALHOU!
            ═══════════════════════════════════════════════════════════
            ⚠️  Verifique os logs acima para identificar o erro
            ═══════════════════════════════════════════════════════════
            '''
        }
        aborted {
            echo '''
            ═══════════════════════════════════════════════════════════
            ⚠️  PIPELINE ABORTADA
            ═══════════════════════════════════════════════════════════
            ℹ️  Deploy em produção foi cancelado
            ═══════════════════════════════════════════════════════════
            '''
        }
    }
}
