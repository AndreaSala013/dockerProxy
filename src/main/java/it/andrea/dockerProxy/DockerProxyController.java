package it.andrea.dockerProxy;

import it.andrea.dockerProxy.model.Constants;
import it.andrea.dockerProxy.model.ProxyResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
        ProxyResponse proxyResponse = new ProxyResponse(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
        return proxyResponse;
    }

    @CrossOrigin
    @PostMapping("/listContainers")
    public ProxyResponse listContainers(@RequestParam String jwtToken, @RequestParam String filter) throws IOException, URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(Constants.PORTAINER_URL_CONTAINER_LIST);
        URIBuilder uriBuilder = new URIBuilder(httpGet.getURI());
        uriBuilder.addParameter("all","true");
        if(filter == null || filter.equals(Constants.NULL_STR) || filter.isEmpty()){
            ProxyResponse proxyResponse = new ProxyResponse(400, "{'err':'Filtro lista conatiner non impostato'}");
            return proxyResponse;
        }else{
            if(!filter.equals(Constants.FILTER_CONTAINER_ALL)){
                List<String> listFilter = Arrays.asList(filter.split(Constants.CONTAINER_FILTER_SEPARATOR));
                StringBuilder value = new StringBuilder();
                Iterator<String> iterator = listFilter.iterator();
                while (iterator.hasNext()){
                    value.append("\""+iterator.next()+"\"");
                    if(iterator.hasNext()){
                        value.append(Constants.CONTAINER_FILTER_SEPARATOR);
                    }
                }
                uriBuilder.addParameter("filters","{\"name\":["+value+"]}");
            }
            URI uri = uriBuilder.build();
            httpGet.setURI(uri);
            httpGet.setHeader("Authorization", "Bearer " + jwtToken);
            HttpResponse response = httpclient.execute(httpGet);
            ProxyResponse proxyResponse = new ProxyResponse(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
            return proxyResponse;
        }
    }

    @CrossOrigin
    @PostMapping("/startContainer")
    public ProxyResponse startContainer(@RequestParam String jwtToken, @RequestParam String idContainer) throws IOException, URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(Constants.PORTAINER_URL_START_CONTAINER+idContainer+"/start");
        httppost.setHeader("Authorization", "Bearer " + jwtToken);
        HttpResponse response = httpclient.execute(httppost);
        ProxyResponse proxyResponse;
        if(response.getStatusLine().getStatusCode() == 204){

            proxyResponse = new ProxyResponse(response.getStatusLine().getStatusCode(),"");
        }else{
            proxyResponse = new ProxyResponse(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
        }
        return proxyResponse;
    }

    @CrossOrigin
    @PostMapping("/stopContainer")
    public ProxyResponse stopContainer(@RequestParam String jwtToken, @RequestParam String idContainer) throws IOException, URISyntaxException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(Constants.PORTAINER_URL_START_CONTAINER+idContainer+"/stop");
        httppost.setHeader("Authorization", "Bearer " + jwtToken);
        HttpResponse response = httpclient.execute(httppost);
        ProxyResponse proxyResponse;
        if(response.getStatusLine().getStatusCode() == 204){
            proxyResponse = new ProxyResponse(response.getStatusLine().getStatusCode(),"");
        }else{
            proxyResponse = new ProxyResponse(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity()));
        }
        return proxyResponse;
    }
}
