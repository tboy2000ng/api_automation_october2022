package usingRestAssured;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class SocialNetworkingComment {
    @Test
    public void getSpecificCommentForSocialNetworking(){
        given().contentType(ContentType.JSON).log().all().
                when().get("https://jsonplaceholder.typicode.com/comments/1").
                then().statusCode(200).log().all().
                body("name", is ("id labore ex et quam laborum")).
                body("email", is ("Eliseo@gardner.biz")).
                body("body", is ("laudantium enim quasi est quidem magnam voluptate ipsam eos\ntempora quo necessitatibus\ndolor quam autem quasi\nreiciendis et nam sapiente accusantium"));
    }

    @Test
    public void postACommentForSocialNetworking(){

        HashMap<String, String> RequestBodyForPost = new HashMap<>();
        RequestBodyForPost.put("PostId", "1");
        RequestBodyForPost.put("name", "Lateef Abdulsalam");
        RequestBodyForPost.put("email", "Abdulsalam1220@gardner.biz");
        RequestBodyForPost.put("body", "I like this post");

        given().contentType(ContentType.JSON).log().all().with().body(RequestBodyForPost).
                when().post("https://jsonplaceholder.typicode.com/comments").
                then().statusCode(201).log().all().
                body("name", is("Lateef Abdulsalam")).
                body("email", is ("Abdulsalam1220@gardner.biz")).
                body("body", is ("I like this post"));

    }

}
