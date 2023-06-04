#!/bin/bash

file_env() {
  local var="$1"
  local fileVar="${var}_FILE"
  local def="${2:-}"

  if [ "${!var:-}" ] && [ "${!fileVar:-}" ]; then
    echo >&2 "error: both $var and $fileVar are set (but are exclusive)"
    exit 1
  fi
  local val="$def"
  if [ "${!var:-}" ]; then
    val="${!var}"
  elif [ "${!fileVar:-}" ]; then
    val="$(<"${!fileVar}")"
  fi
  if [ -n "$val" ]; then
    export "$var"="$val"
  fi
  unset "$fileVar"
}

file_env "DB_PASSWORD"

env
java -XX:MaxRAMPercentage=$MAX_RAM_PERCENTAGE $JAVA_OPTS -jar /backend.jar $@
