FROM bellsoft/liberica-openjdk-alpine:17.0.8

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources ./

# Environment Variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

# Run the tests
ENTRYPOINT java -cp 'libs/*' \
           -Dselenium.grid.enabled=true \
           -Dselenium.grid.hubHost=${HUB_HOST} \
           -Dbrowser=${BROWSER} \
           org.testng.TestNG \
           -threadcount ${THREAD_COUNT} \
           test-suites/${TEST_SUITE}
