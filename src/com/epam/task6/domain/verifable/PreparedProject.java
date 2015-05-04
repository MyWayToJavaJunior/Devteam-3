package com.epam.task6.domain.verifable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * Created by olga on 21.04.15.
 */
public class PreparedProject {
    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 1)
    @Valid
    private ArrayList<PreparedJob> jobs;

    @NotNull
    @Min(value = 1)
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PreparedJob> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<PreparedJob> jobs) {
        this.jobs = jobs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
