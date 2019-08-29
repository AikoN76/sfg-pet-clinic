pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'echo \'Initiating maven build\''
        sh 'mvn clean install'
        echo 'echo \'Maven build complete\''
      }
    }
  }
}