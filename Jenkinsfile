pipeline {
    agent any

    stages {

        stage('Build') {
            steps {
                bat 'mvn -B clean compile -DskipTests'
            }
        }

        stage('Pruebas Unitarias') {
            steps {
                bat 'mvn -B test -Dtest=AppTest'
            }
        }

        stage('Pruebas de Integracion') {
            steps {
                bat 'mvn -B failsafe:integration-test failsafe:verify -Dit.test=AppIntegrationIT'
            }
        }
    }
}