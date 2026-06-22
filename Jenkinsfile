pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        skipDefaultCheckout(true)
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Select browser for TestNG execution')
    }

    environment {
        CI = 'true'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                bat '''
                    @echo on
                    echo PATH=%PATH%
                    where java
                    java -version
                    where mvn
                    call mvn -version
                    if errorlevel 1 exit /b 1
                    echo Running tests on browser: %BROWSER%
                    call mvn clean test -Dbrowser=%BROWSER% -Dheadless=true
                    if errorlevel 1 exit /b 1
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/**, target/**, test-output/**, screenshots/**', allowEmptyArchive: true
            // publishHTML(target: [
            //     allowMissing: true,
            //     alwaysLinkToLastBuild: true,
            //     keepAll: true,
            //     reportDir: 'reports',
            //     reportFiles: 'extent-report.html',
            //     reportName: 'Extent HTML Report'
            // ])
        }
    }
}