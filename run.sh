#!/bin/bash
gradle build -x test
docker-compose build
docker-compose up --force-recreate