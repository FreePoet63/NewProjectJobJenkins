package testjenkins;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import resrservice.delete.DeleteService;
import resrservice.get.UrlBasePath;

import static io.restassured.RestAssured.given;

public class TestDeleteJob {
    RequestSpecification deleteSpec = DeleteService.deleteSpec();

    @Test(priority = 10)
    public void deleteJobJenkins() {
        given().
                spec(deleteSpec).
                basePath(String.valueOf(UrlBasePath.urlListBasePath().get(6))).
        when().
                delete().
        then().
                statusCode(204).
                log().body();
    }

    @Test(priority = 11)
    public void deleteNewJobJenkins() {
        given().
                spec(deleteSpec).
                basePath(String.valueOf(UrlBasePath.urlListBasePath().get(7))).
        when().
                delete().
        then().
                statusCode(204).
                log().body();
    }
}
