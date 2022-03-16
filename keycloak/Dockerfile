FROM jboss/keycloak:16.1.1

ENV KEYCLOAK_USER admin
ENV KEYCLOAK_PASSWORD admin
ENV PROXY_ADDRESS_FORWARDING true
ENV PORT 8080

COPY docker-entrypoint.sh /opt/jboss/tools

ENTRYPOINT [ "/opt/jboss/tools/docker-entrypoint.sh" ]

CMD ["-b", "0.0.0.0"]
