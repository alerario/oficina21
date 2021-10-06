pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'mvn package'
          }
        }

        stage('error') {
          steps {
            timeout(time: 20)
          }
        }

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
        sh '''clear
ls -la
'''
      }
    }

  }
}