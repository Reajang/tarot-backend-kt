FROM mongo:latest

ENV MONGO_INITDB_ROOT_USERNAME root
ENV MONGO_INITDB_ROOT_PASSWORD mongopw
ENV MONGO_INITDB_DATABASE tarot

ADD mongo-init.js /docker-entrypoint-initdb.d/