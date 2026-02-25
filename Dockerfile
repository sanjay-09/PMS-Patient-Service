# ---- Stage 1: Build ----
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# Copy gradle files first for better layer caching
COPY build.gradle settings.gradle ./
COPY gradle gradle

# Download dependencies (cached if build.gradle unchanged)
RUN gradle dependencies --no-daemon || true

# Copy source and build
COPY src src
RUN gradle bootJar --no-daemon -x test

# ---- Stage 2: Run ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring

# Copy the built JAR from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Fix the timezone issue you had!
ENV TZ=Asia/Kolkata
ENV JAVA_OPTS="-Duser.timezone=Asia/Kolkata"

USER spring

EXPOSE 3000

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

