@echo off
echo Starting Eventify Application...
echo ====================================

echo.
echo Step 1: Killing any processes using port 8080...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080 ^| findstr LISTENING') do (
    echo Killing process %%a
    taskkill /PID %%a /F >nul 2>&1
)

echo.
echo Step 2: Waiting for port to be released...
timeout /t 2 /nobreak >nul

echo.
echo Step 3: Starting Spring Boot application...
./mvnw spring-boot:run

pause
