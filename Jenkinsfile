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
                    mvn -version
                    echo Running tests on browser: %BROWSER%
                    mvn clean test -Dbrowser=%BROWSER% -Dheadless=true
                '''
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/**, target/**, test-output/**, screenshots/**', allowEmptyArchive: true

        }
    }
}