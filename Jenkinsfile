pipeline {
  agent any
  stages {
    stage('Mensagem') {
      steps {
        echo 'Teminei o build... vamos ao teste'
        sh 'ls -la; pwd;'
      }
    }

    stage('Script') {
      parallel {
        stage('Script') {
          steps {
            sh 'ls -la'
          }
        }

        stage('parar banco') {
          steps {
            sh 'echo "parar banco";echo "sudo docker stop  post_test>/filas/fila.cmd"'
          }
        }

      }
    }

    stage('Docker Postgres') {
      steps {
        sh '''echo "sudo docker run --name post_test --rm -d -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_HOST_AUTH_METHOD=trust postgres:latest">/filas/fila.cmd
'''
        echo 'Comando enviado para o servidor'
      }
    }

    stage('timer') {
      steps {
        sleep 10
      }
    }

    stage('Criar Banco') {
      steps {
        sh '''echo "criando banco...">\\filas\\fila.cmd; 
echo "psql -c \'create database teste;\' -U postgres -p 5432 -h localhost">/filas/fila.cmd
'''
      }
    }

    stage('timer2') {
      steps {
        sleep 10
        sh 'echo ${WORKSPACE} '
      }
    }

    stage('Criar tabelas') {
      steps {
        sh '''echo "echo \'criar tabelas\'">/filas/fila.cmd; echo "psql -U postgres -p 5432 -h localhost -d teste -f /${WORKSPACE}/script/database/ddl.sql
">/filas/fila.cmd
'''
      }
    }

  }
}