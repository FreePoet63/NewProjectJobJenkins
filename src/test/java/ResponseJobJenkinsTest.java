import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResponseJobJenkinsTest {
    private static RequestSpecification spec;
    public Response response;
    public static String jsonAsString;

    @BeforeClass
    public static void initSpec() {
        spec = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri("http://localhost:8090/job").
                addFilter(new ResponseLoggingFilter()).
                addFilter(new RequestLoggingFilter()).
                build();
    }

    @Test(priority = 4)
    public void newJobJenkinsParam() {
        given().
                spec(spec).
                basePath("/Job(name=newJobJenkins)/api/json").
        when().
                get().
        then().
                log().body().
                body("displayName", equalTo("Job(name=newJobJenkins)"));
    }

    @Test(priority = 5)
    public void newJenkinsParam() {
        given().
                spec(spec).
                basePath("/newjob/api/json").
        when().
                get().
        then().
                log().body().
                body("displayName", equalTo("NewJob"));
    }

    @Test(priority = 6)
    public void paramInfoJob() throws InterruptedException {
        Thread.sleep(8000);
        given().
                spec(spec).
                basePath("/job(name=newjobjenkins)/1/api/json").
        when().
                get().
        then().
                log().body().
                body("displayName", equalTo("#1")).
                body("id", is("1"));
    }

    @Test(priority = 7)
    public void newJobJenkinsLogTXT() {
       response =
               given().
                       spec(spec).
                       basePath("/job(name=newjobjenkins)/1/logText/progressiveText/api/json").
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
                        spec(spec).
                        basePath("job(name=newjobjenkins)/1/logText/progressiveHtml/api/json").
                when().
                        get().
                then().
                        log().body().
                        extract().response();
        jsonAsString = response.asString();
        Assert.assertTrue(jsonAsString.contains(".jenkins\\workspace\\Job(name=newJobJenkins)"));
    }

    @Test(priority = 9)
    public void sumBuildJobJenkins() {
        response =
        given().
                spec(spec).
                basePath("/job(name=newjobjenkins)/api/json/build").
        when().
                get().
        then().
                log().body().
                extract().response();
        List<Integer> sumBuild = response.jsonPath().getList("builds.number");
        assertThat(sumBuild, hasItems(1));
    }
}
