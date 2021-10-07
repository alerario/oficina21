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
      steps {
        sh 'mvn test'
      }
    }

    stage('Parar o banco') {
      steps {
        sh '''echo "sudo docker stop  post_test">/filas/fila.cmd
'''
      }
    }

    stage('apagar container banco') {
      steps {
        sh '''
echo "echo docker rm ==="> /filas/fila.cmd;
echo "sudo docker rm post_test">/filas/fila.cmd'''
      }
    }

  }
}