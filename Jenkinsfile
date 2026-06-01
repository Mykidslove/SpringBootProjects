pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Mykidslove/SpringBootProjects.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                scp -o StrictHostKeyChecking=no target/*.jar ec2-user@13.212.91.129:/home/ec2-user/app.jar

                ssh -o StrictHostKeyChecking=no ec2-user@13.212.91.129 "
                    pkill -f 'java -jar' || true
                    nohup java -jar /home/ec2-user/app.jar > app.log 2>&1 &
                "
                '''
            }
        }
    }
}
