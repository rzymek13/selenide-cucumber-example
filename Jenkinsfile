pipeline {
    agent any
    stages{
        stage('Run tests'){
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit '**/target/cucumber-reports.html'
                }
                }
        }
    }
}