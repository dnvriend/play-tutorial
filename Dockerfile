FROM java:8

ADD target/universal/play-tutorial.tgz /

RUN rm -rf /play-tutorial/bin/*.bat
RUN chown 1000:1000 /play-tutorial/bin/start
RUN chmod +x /play-tutorial/bin/start

WORKDIR /play-tutorial/bin
CMD [ "./start" ]