pipeline {
    agent any
    tools {
        // Jenkins에 구성된 Maven 툴 이름을 사용하세요. 예를 들면 'M3'일 수 있습니다.
        maven 'Maven'
    }
    environment {
        // 'docker-hub-credentials'는 Jenkins에서 구성한 Docker Hub 자격증명 ID입니다.
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
                sh 'docker build -t myapp .'
            }
        }
        stage('Docker Login and Push') {
            steps {
                script {
                    // 자격증명을 가져와서 Docker 로그인을 수행합니다.
                    withCredentials([usernamePassword(credentialsId: DOCKER_HUB_CREDENTIALS, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USER --password-stdin'
                    }
                    // 로그인 후 이미지를 푸시합니다.
                    sh 'docker push myapp:latest'
                }
            }
        }
    }
}
