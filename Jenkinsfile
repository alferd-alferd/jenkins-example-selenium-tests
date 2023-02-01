pipeline {
    agent any
    stages {
        stage('Run Test') {
            steps {
                cmd_exec('mvnw clean test')
            }
        }
    }
}