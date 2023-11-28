FROM bellsoft/liberica-openjdk-alpine:17.0.8

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources ./

# Run the tests
