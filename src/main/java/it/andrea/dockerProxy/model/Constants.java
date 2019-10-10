package it.andrea.dockerProxy.model;

public class Constants {
    public static final String PORTAINER_BASE_URL = "http://portainer:9000/";
    //public static final String PORTAINER_BASE_URL = "http://localhost:9000/";
    public static final String PORTAINER_AUTH_URL = PORTAINER_BASE_URL + "api/auth";
    public static final String PORTAINER_URL_CONTAINER_LIST = PORTAINER_BASE_URL + "api/endpoints/1/docker/containers/json";
    public static final String PORTAINER_USERNAME = "admin";
    public static final String PORTAINER_PASSWORD = "rootPassword";
}
