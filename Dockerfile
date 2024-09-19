# Base image
FROM maven:3.6.3-jdk-8
# Argument with default value
ARG suiteFile=testng.xml
# Copy code from local to image
COPY ./ /app
# Specify working directory in image
WORKDIR /app
# Execute command at creation of image
RUN mvn clean compile
# Command to be executed at start of container/instance-of-image
CMD ["mvn", "test", "-Dsurefire.suiteXmlFiles=$suiteFile", "-e"]