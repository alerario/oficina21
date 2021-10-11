pipeline {
  agent any
  stages {
    stage('Mensagem') {
      when {
        branch 'main'
      }
      steps {
        echo 'Teminei o build... vamos ao teste'
        sh 'ls -la; pwd;'
        sh 'echo "sudo docker stop  post_test">/filas/fila.cmd'
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
        sh 'echo $(basename ${WORKSPACE})'
      }
    }

    stage('Criar tabelas') {
      steps {
        sh '''echo "psql -U postgres -p 5432 -h localhost -d teste -f /home/utfpr/volumes/jenkins_test/workspace/$(basename ${WORKSPACE})/script/database/ddl.sql
">/filas/fila.cmd'''
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('Teste') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Deploy') {
      steps {
        sh 'echo "cd /home/utfpr/volumes/jenkins_test/workspace/$(basename ${WORKSPACE})">/filas/fila.cmd; echo "pwd">/filas/fila.cmd'
        sh 'echo "sudo docker build -t edu.utfpr/appwork .">/filas/fila.cmd;'
        sh 'echo "sudo docker rm -f appwork || true && sudo docker run -d -p 443:9080 -p 9443:9443 --name appwork edu.utfpr/appwork">/filas/fila.cmd;'
      }
    }

    stage('') {
      steps {
        echo 'Concluido'
      }
    }

  }
  triggers {
    githubPush()
  }
}