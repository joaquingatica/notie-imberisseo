#!/usr/bin/env bash

JAR=$1 # "build/libs/notie-imberisseo-2.6.0-rc.jar"

gradle shadowJar
# sh signjar.sh $JAR
java -jar packr/packr.jar --classpath $JAR -- packr/config/mac.json
# java -jar packr/packr.jar --classpath $JAR --  packr/config/win-x86.json &
# java -jar packr/packr.jar --classpath $JAR --  packr/config/win-amd64.json &
# java -jar packr/packr.jar --classpath $JAR --  packr/config/linux-x86.json &
# java -jar packr/packr.jar --classpath $JAR --  packr/config/linux-amd64.json &
