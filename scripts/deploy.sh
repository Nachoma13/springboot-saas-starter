#!/bin/bash
# Production deployment script
set -e

echo "Deploying SaaS Starter Kit..."

# Build the project
echo "Building project..."
mvn package -DskipTests -B

# Build and start Docker containers
echo "Starting Docker containers..."
docker-compose -f docker/docker-compose.yml build
docker-compose -f docker/docker-compose.yml up -d

echo ""
echo "Deployment complete!"
echo "API: http://localhost:8080"
echo "Swagger: http://localhost:8080/swagger-ui.html"
echo ""
echo "To stop: docker-compose -f docker/docker-compose.yml down"
