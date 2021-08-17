package testconfig;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestConfig {
    public static final String BASE_URL = "http://localhost:8090";
    public static final String BASE_URL_LOCAL = "http://localhost:8090/job";
    public static RequestSpecification REQUEST_SPECIFICATION;
    public static Response response;
    public static String jsonAsString;
    public static String XMLBody;

    static {
        try {
            XMLBody = new String(Files.readAllBytes
                    (Paths.get("src/test/resources/config.xml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
