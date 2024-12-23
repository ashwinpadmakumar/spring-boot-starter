FROM jenkins/jenkins:lts

# Switch to root to install Docker
USER root

# Install prerequisites and Docker from Docker's official repository
RUN apt-get update && \
    apt-get install -y apt-transport-https ca-certificates curl software-properties-common && \
    curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add - && \
    echo "deb [arch=amd64] https://download.docker.com/linux/debian $(lsb_release -cs) stable" > /etc/apt/sources.list.d/docker.list && \
    apt-get update && \
    apt-get install -y docker.io && \
    rm -rf /var/lib/apt/lists/*

# Add Jenkins user to Docker group
RUN usermod -aG docker jenkins

# Switch back to the Jenkins user
USER jenkins
