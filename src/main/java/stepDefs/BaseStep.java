package stepDefs;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseStep {

    public String endpointPath;
    public String commentEndpoint;
    public Headers headers;
    public String serviceUrl;
    public String commentPayload;
    DocumentContext requestBodyJson;

    public BaseStep() {
        serviceUrl = "https://jsonplaceholder.typicode.com";
        commentEndpoint = serviceUrl + "/comments/";
        commentPayload ="/templates/commentsPayload.json";

    }


    protected URL getURL() {
        try {
            return new URL(endpointPath);
        } catch
        (MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    public void setHeaders(Headers value) {
        headers = null;
        headers = value;
    }

    public void setHeader_ContentTypeApplicationJson() {
        headers = new Headers(
                new Header("Content-Type", "application/json"));
        setHeaders(headers);
    }

    public void setEndpointPath(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public Response getCall() {
        Response response = RestAssured.given().log().all()
                .headers(headers).when().get(getURL()).then().log().all().extract().response();
        return response;

    }

    public Response postCall() {
        Response response = RestAssured.given().log().all()
                .headers(headers).body(requestBodyJson.jsonString()).
                when().post(getURL()).then().log().all().extract().response();
        return response;

    }

    public DocumentContext loadJsonTemplate(String path) {
        requestBodyJson = JsonPath.parse(this.getClass().getResourceAsStream(path));
        return requestBodyJson;
    }

    public void setRequestBodyJson(DocumentContext reqBody) {
        this.requestBodyJson = reqBody;
    }
}

