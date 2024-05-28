FROM hanzhenyun.com:30080/hzwl/skywalking-agent-jdk21:1.1

MAINTAINER 1983160303@qq.com

ADD iot-server/build/libs/iot-server.jar /app.jar

ENTRYPOINT ["java","-javaagent:/skywalking/agent/skywalking-agent.jar","-Dskywalking.agent.service_name=hz-iot-prod::server","-Dskywalking.collector.backend_service=hanzhenyun.com:11800","-Dskywalking.trace.ignore_path=Jackson/**,PostgreSQL/**","-Dspring.profiles.active=prod","-jar","/app.jar"]

