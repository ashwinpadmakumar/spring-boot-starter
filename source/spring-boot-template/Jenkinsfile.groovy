pipeline {
    agent any

    environment {
        SOURCE_DIR = 'source/spring-boot-template'
        DOCKER_REGISTRY = 'simpleimages'
        DOCKER_IMAGE = "${DOCKER_REGISTRY}/spring-boot-starter"
    }

    stages {
        stage('Checkout') {
            steps {
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
        stage('Package and Push') {
            steps {
                dir("${SOURCE_DIR}") {
                    script {
                        withCredentials([string(credentialsId: 'dockerhub-password', variable: 'dockerpwd')]) {
                            sh "docker login -u ${DOCKER_REGISTRY} -p $dockerpwd"
                            sh 'docker -v'
                            def commitHash = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                            sh "docker build -t ${DOCKER_IMAGE}:${commitHash} ."
                            sh "docker tag ${DOCKER_IMAGE}:${commitHash} ${DOCKER_IMAGE}:latest"
                            sh "docker push ${DOCKER_IMAGE}:${commitHash}"
                            sh "docker push ${DOCKER_IMAGE}:latest"
                        }
                    }
                }
            }
        }
    }
}
