package model;

public class CreateJob {
    public static Job createNewJob() {
        return Job.builder()
                .job("NewJenkins")
                .build();
    }

    public static Job createNewJobDuplicate() {
        return Job.builder()
                .job("NewSuperJenkins")
                .build();
    }
}
