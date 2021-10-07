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
        sh 'ls /'
      }
    }

  }
}