package com.noroff.mefit.config;

import java.net.URI;

public class ConfigSettings {
    public static class HTTP {
        public static final URI ROOT_LOCATION = URI.create("https://case-se-java-mefit.herokuapp.com/api/");
        public static URI location(String endpoint) {
            return ROOT_LOCATION.resolve(endpoint);
        }
    }
}
