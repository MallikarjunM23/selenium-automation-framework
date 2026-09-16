pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test -DsuiteXmlFile=testng.xml'
            }
        }
    }

    post {

        always {
            archiveArtifacts artifacts: 'reports/**',
                             allowEmptyArchive: true

            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'index.html',
                reportName: 'Extent Report'
            ])
        }

        success {
            echo 'Selenium automation execution completed successfully.'
        }

        failure {
            echo 'Selenium automation execution failed. Check the console output.'
        }
    }
}