FROM postgres:15.2
RUN localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.utf8

RUN apt-get update && apt-get install -y curl netcat

EXPOSE 5432

COPY *.sql /docker-entrypoint-initdb.d/

CMD ["postgres", "-c", "max_prepared_transactions=100", "-c", "max_connections=500"]
