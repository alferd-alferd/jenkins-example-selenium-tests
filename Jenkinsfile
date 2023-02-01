pipeline {
    agent any
    stages {
        stage('Verify browsers are installed') {
            steps {
                cmd_exec('powershell -command "(Get-Command "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe").Version.ToString()"')
                cmd_exec('"C:\Program Files (x86)\Mozilla Firefox\Firefox.exe" -v|more')
            }
        }
        stage('Run Test') {
            steps {
                cmd_exec('mvnw clean test')
            }
        }
    }
}