FROM eclipse-temurin:17-jre-focal

EXPOSE 8080

ARG DB_URL
ENV DB_URL ${DB_URL}
ARG DB_USERNAME
ENV DB_USERNAME ${DB_USERNAME}
ARG DB_PASSWORD
ENV DB_PASSWORD ${DB_PASSWORD}
ARG REDIS_HOST
ENV REDIS_HOST ${REDIS_HOST}
ARG REDIS_PORT
ENV REDIS_PORT ${REDIS_PORT}
ARG REDIS_PASSWORD
ENV REDIS_PASSWORD ${REDIS_PASSWORD}
ARG JWT_SECRET_KEY
ENV JWT_SECRET_KEY ${JWT_SECRET_KEY}
ARG JWT_ACCESS_EXP
ENV JWT_ACCESS_EXP ${JWT_ACCESS_EXP}
ARG JWT_REFRESH_EXP
ENV JWT_REFRESH_EXP ${JWT_REFRESH_EXP}
ARG COOLSMS_API_KEY
ENV COOLSMS_API_KEY ${COOLSMS_API_KEY}
ARG COOLSMS_API_SECRET
ENV COOLSMS_API_SECRET ${COOLSMS_API_SECRET}
ARG COOLSMS_SENDER
ENV COOLSMS_SENDER ${COOLSMS_SENDER}

ADD build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
