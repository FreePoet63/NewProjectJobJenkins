package resrservice.get;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static testconfig.TestConfig.BASE_URL_LOCAL;
import static testconfig.TestConfig.REQUEST_SPECIFICATION;

public class GetJobJenkins {
    public static RequestSpecification getSpec() {
        return REQUEST_SPECIFICATION = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri(BASE_URL_LOCAL).
                addFilter(new ResponseLoggingFilter()).
                addFilter(new RequestLoggingFilter()).
                build();
    }
}
