pipeline {
  agent any

  stages {

    stage('Checkout') {
      steps {
        git branch: 'main',
          url: 'https://github.com/thiruyogi/saucedemo-automation-framework.git'
      }
    }

    stage('Build & Test') {
      steps {
        sh 'mvn clean test'
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'target/**, test-results/**', allowEmptyArchive: true
      publishHTML(target: [
        allowMissing: true,
        alwaysLinkToLastBuild: true,
        keepAll: true,
        reportDir: 'target',
        reportFiles: 'index.html',
        reportName: 'Maven HTML Report'
      ])
    }
  }
}