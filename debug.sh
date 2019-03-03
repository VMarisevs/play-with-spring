#!/bin/bash


(cd ./common-play-utils/; sbt publishLocal)

sbt -jvm-debug 9991 "~run 9001"
