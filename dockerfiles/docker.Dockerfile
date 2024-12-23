FROM docker:dind

# Switch to root to install Docker
USER root

# Add Jenkins user to Docker group
RUN usermod -aG docker jenkins