pipeline {
    agent any

    environment {
        SOURCE_DIR = 'source/spring-boot-template'
        DOCKER_IMAGE = 'simpleimages/spring-boot-starter'
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
                    sh "docker build -t ${DOCKER_IMAGE}:latest ."
                }
            }
        }
        stage('Tag') {
            steps {
                script {
                    env.COMMIT_HASH = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                    sh "docker tag ${DOCKER_IMAGE}:latest ${DOCKER_IMAGE}:${env.COMMIT_HASH}"
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerpwd')]) {
                        sh 'docker login -u simpleimages -p $dockerpwd'
                        sh "docker push ${DOCKER_IMAGE}:latest"
                        sh "docker push ${DOCKER_IMAGE}:${env.COMMIT_HASH}"
                    }
                }
            }
        }
    }
}
