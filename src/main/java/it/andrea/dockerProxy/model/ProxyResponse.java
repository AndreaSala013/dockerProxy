package it.andrea.dockerProxy.model;

public class ProxyResponse {
    private int status;
    private String message;

    public ProxyResponse(int _status, String _message){
        status = _status;
        message = _message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
