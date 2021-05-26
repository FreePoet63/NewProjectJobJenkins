import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TestDeleteJob {

    @Test(priority = 10)
    public void deleteJobJenkins() {
        given().
                baseUri("http://localhost:8090/job/Job(name=newJobJenkins)/").
                header("Content-Type", "application/json; charset=UTF-8").
        when().
                delete().
        then().
                statusCode(204).
                log().body();
    }

    @Test(priority = 11)
    public void deleteNewJobJenkins() {
        given().
                baseUri("http://localhost:8090/job/NewJob/").
                header("Content-Type", "application/json; charset=UTF-8").
        when().
                delete().
        then().
                statusCode(204).
                log().body();
    }
}
