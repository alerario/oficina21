pipeline {
  agent any
  stages {
    stage('Mensagem') {
      steps {
        echo 'Teminei o build... vamos ao teste'
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
        echo 'Comando enviado para o servidor'
      }
    }

    stage('timer') {
      steps {
        sleep 30
      }
    }

    stage('Parar banco') {
      steps {
        sh 'echo "sudo docker stop  post_test>/filas/fila.cmd"'
      }
    }

  }
}