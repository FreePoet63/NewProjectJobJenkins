package testjenkins;

import com.jayway.awaitility.Duration;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import resrservice.get.GetJobJenkins;
import resrservice.get.UrlBasePath;

import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static testconfig.TestConfig.jsonAsString;
import static testconfig.TestConfig.response;
import static com.jayway.awaitility.Awaitility.await;

public class ResponseJobJenkinsTest {
    RequestSpecification getSpec = GetJobJenkins.getSpec();


    @Test(priority = 4)
    public void newJobJenkinsParam() {
        given().
                spec(getSpec).
                basePath(String.valueOf(UrlBasePath.urlListBasePath().get(0))).
        when().
                get().
        then().
                log().body().
                body("displayName", equalTo("Job(job=NewJenkins)"));
    }

    @Test(priority = 5)
    public void newJenkinsParam() {
        given().
                spec(getSpec).
                basePath(String.valueOf(UrlBasePath.urlListBasePath().get(1))).
        when().
                get().
        then().
                log().body().
                body("displayName", equalTo("Job(job=NewSuperJenkins)"));
    }

    @Test(priority = 6)
    public void paramInfoJob() {
        await().atMost(Duration.TEN_SECONDS).until(() -> {
            given().
                    spec(getSpec).
                    basePath(String.valueOf(UrlBasePath.urlListBasePath().get(2))).
                    when().
                    get().
                    then().
                    log().body().
                    body("displayName", equalTo("#1")).
                    body("id", is("1"));
        });
    }

    @Test(priority = 7)
    public void newJobJenkinsLogTXT() {
       response =
               given().
                       spec(getSpec).
                       basePath(String.valueOf(UrlBasePath.urlListBasePath().get(3))).
               when().
                       get().
               then().
                       log().body().
                       extract().response();
       jsonAsString = response.asString();
       Assert.assertTrue(jsonAsString.contains("Finished: SUCCESS"));
    }

    @Test(priority = 8)
    public void newJobJenkinsLogHTML() {
        response =
                given().
                        spec(getSpec).
                        basePath(String.valueOf(UrlBasePath.urlListBasePath().get(4))).
                when().
                        get().
                then().
                        log().body().
                        extract().response();
        jsonAsString = response.asString();
        Assert.assertTrue(jsonAsString.contains(".jenkins\\workspace\\Job(job=NewJenkins)"));
    }

    @Test(priority = 9)
    public void sumBuildJobJenkins() {
        response =
        given().
                spec(getSpec).
                basePath(String.valueOf(UrlBasePath.urlListBasePath().get(5))).
        when().
                get().
        then().
                log().body().
                extract().response();
        List<Integer> sumBuild = response.jsonPath().getList("builds.number");
        assertThat(sumBuild, hasItems(1));
    }
}
