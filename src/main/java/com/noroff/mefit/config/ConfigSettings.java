package com.noroff.mefit.config;

public class ConfigSettings {
    protected static class CORS {
        protected static final String ALLOW_ORIGIN = "*";
        protected static final String ALLOWED_METHODS = "GET, POST, DELETE, PUT, PATCH, HEAD, OPTIONS";
        protected static final String ALLOWED_HEADERS = "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers";
        protected static final String EXPOSED_HEADERS = "Access-Control-Allow-Origin, Access-Control-Allow-Credentials";
        protected static final Boolean ALLOW_CREDENTIALS = true;
        protected static final Integer MAX_AGE = 10;
    }
}
