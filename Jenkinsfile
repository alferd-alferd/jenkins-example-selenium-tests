pipeline {
    agent any
    stages {
        stage('Verify browsers are installed') {
            steps {
                powershell -command "(Get-Command 'C:\Program Files (x86)\Google\Chrome\Application\chrome.exe').Version.ToString()"
                'C:\Program Files (x86)\Mozilla Firefox\Firefox.exe' -v|more
            }
        }
        stage('Run Test') {
            steps {
                sh './mvnw clean test'
            }
        }
    }
}