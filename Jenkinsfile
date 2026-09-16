pipeline {
    agent any

    parameters {
        booleanParam(
            name: 'EJECUTAR_ROLLBACK',
            defaultValue: false,
            description: 'Restaurar la version anterior desplegada'
        )
    }

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

        stage('Acceptance Test') {
            steps {
                bat 'mvn -B test -Dtest=AppAcceptanceTest'
            }
        }

        stage('Package') {
            when {
                expression {
                    return !params.EJECUTAR_ROLLBACK
                }
            }

            steps {
                bat 'mvn -B package -DskipTests'
            }
        }

        stage('Deploy Test') {
            when {
                expression {
                    return !params.EJECUTAR_ROLLBACK
                }
            }

            steps {
                bat '''
                if not exist deploy\\test mkdir deploy\\test

                if exist deploy\\test\\app.jar (
                    copy /Y deploy\\test\\app.jar deploy\\test\\app-backup.jar
                )

                copy /Y target\\taller-pruebas-1.0-SNAPSHOT.jar deploy\\test\\app.jar

                if not exist deploy\\test\\app-backup.jar (
                    copy /Y deploy\\test\\app.jar deploy\\test\\app-backup.jar
                )

                echo DEPLOY EN AMBIENTE DE PRUEBA EXITOSO
                '''
            }
        }

        stage('Rollback') {
            when {
                expression {
                    return params.EJECUTAR_ROLLBACK
                }
            }

            steps {
                bat '''
                if exist deploy\\test\\app-backup.jar (
                    copy /Y deploy\\test\\app-backup.jar deploy\\test\\app.jar
                    echo ROLLBACK EJECUTADO CORRECTAMENTE
                ) else (
                    echo NO EXISTE VERSION DE RESPALDO
                    exit /b 1
                )
                '''
            }
        }
    }
}