package utilities;

import com.jayway.jsonpath.DocumentContext;
import stepDefs.BaseStep;

public class RequestBodyService extends BaseStep {
    public void setRequestBodyForComment(DocumentContext requestBody, String postId, String name, String email, String body){
        requestBody.set("postId", postId);
        requestBody.set("email", email);
        requestBody.set("name", name);
        requestBody.set("body", body);
        setRequestBodyJson(requestBody);
    }
}

