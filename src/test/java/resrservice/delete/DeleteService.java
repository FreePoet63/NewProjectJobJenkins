package resrservice.delete;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import static testconfig.TestConfig.BASE_URL_LOCAL;
import static testconfig.TestConfig.REQUEST_SPECIFICATION;

public class DeleteService {
    public static RequestSpecification deleteSpec() {
        return REQUEST_SPECIFICATION = new RequestSpecBuilder().
                setContentType("application/json; charset=UTF-8").
                setBaseUri(BASE_URL_LOCAL).
                addFilter(new ResponseLoggingFilter()).
                addFilter(new RequestLoggingFilter()).
                build();
    }
}
