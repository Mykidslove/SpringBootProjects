pipeline {
agent any

```
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

    stage('Deploy') {
        steps {
            sh '''
            scp -o StrictHostKeyChecking=no target/*.jar ec2-user@13.212.91.129:/home/ec2-user/
            ssh -o StrictHostKeyChecking=no ec2-user@13.212.91.129 "
                pkill -f java || true
                nohup java -jar /home/ec2-user/*.jar > app.log 2>&1 &
            "
            '''
        }
    }
}
```

}
