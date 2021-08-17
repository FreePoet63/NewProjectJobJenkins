package resrservice.post;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import model.CreateJob;
import model.Job;
import testconfig.TestConfig;

import static testconfig.TestConfig.REQUEST_SPECIFICATION;

public class CreateNewJobJenkins {
    public static RequestSpecification initNewSpec() {
        Job newjenkinsjob = CreateJob.createNewJob();
        Job newjobjenkins = CreateJob.createNewJobDuplicate();
        return REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .setBaseUri(TestConfig.BASE_URL)
                .setBasePath("/createItem")
                .addQueryParam("name",String.valueOf(newjobjenkins))
                .addQueryParam("mode","copy")
                .addQueryParam("from",String.valueOf(newjenkinsjob))
                .setContentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }
}
