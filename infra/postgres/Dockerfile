FROM postgres:15.2
RUN localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
ENV LANG en_US.utf8

RUN apt-get update && apt-get install -y curl netcat
RUN curl https://dl.min.io/client/mc/release/linux-amd64/mc --output /usr/local/bin/mc && chmod +x /usr/local/bin/mc

EXPOSE 5432

COPY *.sql /docker-entrypoint-initdb.d/
#COPY postgresql.conf /etc/postgresql/postgresql.conf

CMD ["postgres", "-c", "max_prepared_transactions=100", "-c", "max_connections=500"]
#CMD ["postgres", "-c", "max_prepared_transactions=100", "-c", "max_connections=500", "-c", "config_file=/etc/postgresql/postgresql.conf"]
