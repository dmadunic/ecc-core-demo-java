#!/bin/sh

echo "The application will start in 3s..." && sleep 3
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "hr.ecc.corepoc.demo.EccCorePocDemoApplication"  "$@"
