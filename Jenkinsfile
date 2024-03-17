pipeline {
    agent any
    tools { 
        maven "Maven" // 환경 설정에 정의된 Maven 툴 이름
    }
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials') // Jenkins 자격증명 ID
    }
    stages {
        stage('Check out') {
            steps {
                checkout scm // Git 리포지토리에서 코드 체크아웃
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package' // Maven 빌드 및 패키징
            }
        }
        stage('Code coverage') {
            steps {
                sh 'mvn jacoco:report' // Jacoco를 사용한 코드 커버리지 리포트 생성
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t myapp .' // Docker 이미지 빌드
            }
        }
        stage('Docker Login') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_HUB_CREDENTIALS) {
                        sh 'docker login -u ${DOCKER_HUB_CREDENTIALS_USR} -p ${DOCKER_HUB_CREDENTIALS_PSW}'
                    }
                }
            }
        }
        stage('Docker Push') {
            steps {
                sh 'docker push myapp:latest' // Docker 이미지 푸시
            }
        }
    }
}
