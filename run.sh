#!/bin/sh

mvn install
rm -rf ~/.groovy/grapes/com.goodercode
groovy ./src/test/groovy/sample/Annotated.groovy
