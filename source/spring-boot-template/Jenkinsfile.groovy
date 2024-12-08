pipeline {
    agent any

    environment {
        SOURCE_DIR = 'source/spring-boot-template'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout to master'
                checkout scmGit(
                    branches: [[name: '*/master']],
                    extensions: [],
                    userRemoteConfigs: [[url: 'https://github.com/ashwinpadmakumar/spring-boot-starter']]
                )
            }
        }
        stage('Build') {
            steps {
                dir("${SOURCE_DIR}") {
                    sh './gradlew --version'
                    sh './gradlew build'
                }
            }
        }
        stage('Package') {
            steps {
                echo 'Packing the application into docker image'
                dir("${SOURCE_DIR}") {
                    sh 'docker -v'
                    sh 'docker build -t spring-boot-starter .'
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerpwd')]) {
                        sh 'docker login -u ashwin2692 -p $dockerpwd'
                        sh 'docker push spring-boot-starter'
                    }
                }
            }
        }
    }
}
