#!/usr/bin/env bash
JAR_FILE=target/rovers-1.0.jar

if [ ! -f "$JAR_FILE" ]
then
    echo "Run first build.sh."
    exit 1
fi

java -cp $JAR_FILE com.github.basoko.rovers.Application "$@"
