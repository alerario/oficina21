pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn package'
      }
    }

    stage('Mensagem') {
      steps {
        echo 'Teminei o build... vamos ao teste'
      }
    }

    stage('Teste') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Script') {
      steps {
        sh 'ls -la'
      }
    }

    stage('Docker Postgres') {
      steps {
        sh '''echo "sudo docker run --name post_test --rm -d -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_HOST_AUTH_METHOD=trust postgres:latest">/filas/fila.cmd
'''
      }
    }

    stage('Teste com o BD') {
      parallel {
        stage('Teste com o BD') {
          steps {
            sh 'mvn test'
          }
        }

        stage('criar o banco') {
          steps {
            sh 'echo "psql -c \'create database teste;\' -U postgres -p 5432 -h localhost">/filas/fila.cmd'
          }
        }

      }
    }

    stage('Criar tabelas') {
      steps {
        sh '''echo "criar tabelas">/filas/fila.cmd; echo "psql -U postgres -p 5432 -h localhost -d teste -f script/database/ddl.sql
">/filas/fila.cmd
'''
      }
    }

    stage('parar o banco') {
      steps {
        sh '''
echo "sudo docker stop  post_test">/filas/fila.cmd
'''
      }
    }

  }
}