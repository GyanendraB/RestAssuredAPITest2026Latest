pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/reqres-api-automation.git'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean test -Denv=qa'
            }
        }
        stage('Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}
