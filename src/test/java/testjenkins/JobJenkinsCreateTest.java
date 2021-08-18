package testjenkins;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import resrservice.post.CreateBuildJenkins;
import resrservice.post.CreateJobJenkins;
import resrservice.post.CreateNewJobJenkins;

import static io.restassured.RestAssured.given;
import static testconfig.TestConfig.*;

public class JobJenkinsCreateTest {

    @Test(priority = 1)
    public void createJob() {
        RequestSpecification spec = CreateJobJenkins.initSpec();
        given()
                .spec(spec)
                .body(XMLBody)
                .when()
                .post()
                .then()
                .log()
                .body()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void createBuild() {
        RequestSpecification specBuild = CreateBuildJenkins.initSpecBuild();
        given()
                .spec(specBuild)
                .when()
                .post()
                .then()
                .log()
                .body()
                .statusCode(201);
    }

    @Test(priority = 3)
    public void copyJob() {
        RequestSpecification newSpecJob = CreateNewJobJenkins.initNewSpec();
        given()
                .spec(newSpecJob)
                .when()
                .post()
                .then()
                .log()
                .all()
                .statusCode(302);
    }
}

