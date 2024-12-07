pipeline {
    agent any

    environment {
        SOURCE_DIR = 'source/spring-boot-template'
    }

    stages {
        stage('Prerequisites') {
            steps {
                script {
                    // Check if Docker is installed
                    boolean dockerInstalled = sh(
                        script: 'command -v docker',
                        returnStatus: true
                    ) == 0

                    // Install Docker if not installed
                    if (dockerInstalled) {
                        echo 'Docker is already installed.'
                    } else {
                        echo 'Docker is not installed. Installing Docker...'

                        // Update package information and install Docker
                        sh '''
                        # Update package information
                        sudo apt-get update

                        # Install Docker dependencies
                        sudo apt-get install -y apt-transport-https ca-certificates curl software-properties-common

                        # Add Docker's official GPG key
                        curl -fsSL https://download.docker.com/linux/ubuntu/gpg | \
                        sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg

                        # Add Docker's official APT repository
                        echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] " + \\
                        https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

                        # Update the package database
                        sudo apt-get update

                        # Install Docker CE
                        sudo apt-get install -y docker-ce docker-ce-cli containerd.io
                        '''

                        echo 'Docker installation completed.'
                    }
                }
            }
        }
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
    }
}
