package it.andrea.dockerProxy.model;

public class Constants {
    //url dastan da locale
    //public static final String PORTAINER_BASE_URL = "http://app.dastan.eng.it:9000/";
    //url dastan da dastan
    public static final String PORTAINER_BASE_URL = "http://portainer:9000/";
    //url locale
    //public static final String PORTAINER_BASE_URL = "http://localhost:9000/";
    public static final String PORTAINER_AUTH_URL = PORTAINER_BASE_URL + "api/auth";
    public static final String PORTAINER_URL_CONTAINER_LIST = PORTAINER_BASE_URL + "api/endpoints/1/docker/containers/json";
    public static final String PORTAINER_URL_START_CONTAINER = PORTAINER_BASE_URL + "api/endpoints/1/docker/containers/";
    //credenziali in locale
    //public static final String PORTAINER_USERNAME = "admin";
    //public static final String PORTAINER_PASSWORD = "rootPassword";
    //credenziali dastan
    public static final String PORTAINER_USERNAME = "admin";
    public static final String PORTAINER_PASSWORD = "Settembre2019";

    public static final String CONTAINER_FILTER_SEPARATOR = ",";
    public static final String FILTER_CONTAINER_ALL = "ALL";
    public static final String NULL_STR = "null";
}
