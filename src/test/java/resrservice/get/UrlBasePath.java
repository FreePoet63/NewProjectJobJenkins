package resrservice.get;

import java.util.ArrayList;
import java.util.List;

public class UrlBasePath {
    public static List<String> newPath = new ArrayList<>();
    public static List<String> urlListBasePath() {
        newPath.add("/Job(job=newJenkins)/api/json");
        newPath.add("/Job(job=NewSuperJenkins)/api/json");
        newPath.add("/Job(job=NewJenkins)/1/api/json");
        newPath.add("/Job(job=NewJenkins)/1/logText/progressiveText/api/json");
        newPath.add("/Job(job=NewJenkins)/1/logText/progressiveHtml/api/json");
        newPath.add("/Job(job=NewJenkins)/api/json/build");
        newPath.add("/Job(job=NewJenkins)/");
        newPath.add("/Job(job=NewSuperJenkins)/");
        return newPath;
    }
}
