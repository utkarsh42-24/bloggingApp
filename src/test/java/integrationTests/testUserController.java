package integrationTests;


import com.blog.BlogSystem;
import com.blog.models.User;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { BlogSystem.class }, webEnvironment
        = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class testUserController {

    private static final String API_ROOT
            = "http://localhost:8080/api/user";

    private Random random = new Random();

    private User createRandomUser() {
        User user = new User();
        user.setUserID(random.nextInt(100));
        user.setName("user"+random.nextInt(10));
        return user;
    }

    private String createUserAsUri(User user){

        Response response = RestAssured.given()
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .body(user)
                            .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("userID");

    }


    @Test
    public void whenGetAllUsers_thenOK(){
        Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

}
