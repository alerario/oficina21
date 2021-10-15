pipeline {
  agent any
  stages {
    stage('Conf. inicial') {
      when {
        branch 'main'
      }
      steps {
        echo 'Iniciando pipeline'
        sh 'ls -la; pwd;'
        sh '''
echo "parando mongo_test" >/filas/fila.cmd;
echo "sudo docker stop mongo_test">/filas/fila.cmd'''
      }
    }

    stage('Builder/BD') {
      parallel {
        stage('Check folder') {
          steps {
            sh 'ls -la'
          }
        }

        stage('Build ') {
          steps {
            sh 'echo "Realizando build"; mvn clean package'
          }
        }

        stage('Start MongoDB') {
          steps {
            sh '''echo "sudo docker run --name mongo_test --rm -d -p 27017:27017 mongo:latest">/filas/fila.cmd
'''
          }
        }

      }
    }

    stage('Criar Collection') {
      steps {
        echo 'Aguardar banco'
        sleep 3
        sh '''echo "criando banco...">\\filas\\fila.cmd; 
'''
        sh '''echo "mongo  mongodb://localhost:27017/testeDB /home/utfpr/volumes/jenkins_test/workspace/$(basename ${WORKSPACE})/script/database/ddl.js">/filas/fila.cmd
'''
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
        sh 'echo "sudo docker rm -f appwork || true && sudo docker run -d -p 443:9080 -p 9443:9443 -e DATABASE_URL=alerario.cp.utfpr.edu.br     --name appwork edu.utfpr/appwork">/filas/fila.cmd;'
      }
    }

    stage('Fim') {
      steps {
        echo 'Pipeline concluido'
      }
    }

  }
  triggers {
    githubPush()
  }
}