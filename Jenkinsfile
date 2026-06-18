pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Select browser for TestNG execution')
    }

    environment {
        CI = 'true'
        PATH = '/opt/homebrew/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                    echo "PATH=$PATH"
                    which java
                    java -version
                    which mvn
                    mvn -version
                    echo "Running tests on browser: ${BROWSER}"
                    mvn clean test -Dbrowser=${BROWSER} -Dheadless=true
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/**, target/**, test-output/**, screenshots/**', allowEmptyArchive: true
            publishHTML(target: [
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'extent-report.html',
                reportName: 'Extent HTML Report'
            ])
        }
    }
}