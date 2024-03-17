pipeline {
    agent any
    tools {
      
        maven 'Maven'
    }
    environment {
     
        DOCKER_HUB_CREDENTIALS = 'docker-hub-credentials'
    }
    stages {
        stage('Check out') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Code coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Docker Build') {
            steps {
              
                sh '/usr/local/bin/docker build -t myapp .'
            }
        }
        stage('Docker Login and Push') {
            steps {
                script {
                  
                   withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                sh 'echo $DOCKER_PASSWORD | /usr/local/bin/docker login -u $DOCKER_USER --password-stdin'
                sh '/usr/local/bin/docker push myapp:latest'
                    }
                  
                    sh 'docker push myapp:latest'
                }
            }
        }
    }
}
