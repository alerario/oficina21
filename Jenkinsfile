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

        stage('') {
          steps {
            timeout(time: 20)
          }
        }

      }
    }

  }
}