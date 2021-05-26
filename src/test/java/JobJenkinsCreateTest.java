import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class JobJenkinsCreateTest {
    String BaseUrl = "http://localhost:8090";

    Job job = Job.builder().
            name("newJobJenkins").
            build();

    @Test(priority = 1)
    public void createJob() throws IOException {
        String XMLBody = new String(Files.readAllBytes
                (Paths.get("src/test/resources/config.xml")));

        given().baseUri(BaseUrl + "/createItem").
                queryParam("name", String.valueOf(job)).
                header("Content-Type","application/xml; charset=ISO-8859-1").
                body(XMLBody).
        when().
                post().
        then().
                log().body().
                statusCode(200);
    }

    @Test(priority = 2)
    public void createBuild() {
        given().baseUri(BaseUrl + "/job/" + job + "/build/api/json").
                contentType(ContentType.JSON).
        when().
                post().
        then().
                log().body().
                statusCode(201);
    }

    @Test(priority = 3)
    public void copyJob() {
        given().
                baseUri(BaseUrl + "/createItem").
                queryParam("name","NewJob").
                queryParam("mode", "copy").
                queryParam("from", String.valueOf(job)).
                header("ContentType", "application/x-www-form-urlencoded; charset=ISO-8859-1").
        when().
                post().
        then().
                log().all().
                statusCode(302);
    }
}
