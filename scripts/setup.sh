#!/bin/bash
# Development setup script
set -e

echo "Starting SaaS Starter Kit..."

# Start with H2 in-memory database (default profile)
echo "Using H2 in-memory database (development mode)"
echo "Starting application on port 8080..."
echo ""
echo "API Documentation: http://localhost:8080/swagger-ui.html"
echo "H2 Console: http://localhost:8080/h2-console"
echo "Health: http://localhost:8080/health"
echo ""

# Run with Maven
mvn spring-boot:run
