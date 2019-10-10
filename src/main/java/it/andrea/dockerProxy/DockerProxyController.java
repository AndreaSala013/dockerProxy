package it.andrea.dockerProxy;

import it.andrea.dockerProxy.model.Constants;
import it.andrea.dockerProxy.model.ProxyResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

@RestController
public class DockerProxyController {
    @CrossOrigin
    @GetMapping("/loginPortainer")
    public ProxyResponse loginPortainer() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(Constants.PORTAINER_AUTH_URL);
        httppost.setHeader("Content-type", "application/json");
        String json = "{\"Username\":\""+Constants.PORTAINER_USERNAME+"\", \"Password\":\""+Constants.PORTAINER_PASSWORD+"\"}";
        StringEntity params = new StringEntity(json.toString());
        httppost.setEntity(params);
        HttpResponse response = httpclient.execute(httppost);
        ProxyResponse servletResponse = new ProxyResponse(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
        return servletResponse;
    }

    @CrossOrigin
    @GetMapping("/listContainers")
    public ProxyResponse listContainers(@RequestParam String jwtToken) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(Constants.PORTAINER_URL_CONTAINER_LIST);
        httpGet.setHeader("Authorization", "Bearer " + jwtToken);
        HttpResponse response = httpclient.execute(httpGet);
        ProxyResponse servletResponse = new ProxyResponse(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
        return servletResponse;
    }

}
