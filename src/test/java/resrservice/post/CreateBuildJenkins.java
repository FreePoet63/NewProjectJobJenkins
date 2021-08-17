package resrservice.post;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import model.CreateJob;
import model.Job;
import testconfig.TestConfig;

import static testconfig.TestConfig.REQUEST_SPECIFICATION;

public class CreateBuildJenkins {
    public static RequestSpecification initSpecBuild() {
        Job newjob = CreateJob.createNewJob();
        return REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .setBaseUri(TestConfig.BASE_URL)
                .setBasePath("/job/" + newjob + "/build/api/json")
                .setContentType("ContentType.JSON")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }
}
