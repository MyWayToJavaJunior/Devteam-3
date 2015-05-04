package com.epam.task6.domain.verifable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by olga on 21.04.15.
 */
public class ProposedJob {
    @NotNull
    @Size(min = 1)
    private String name;

    @Pattern(regexp = "Architect|Copywriter|Designer|Programmer|Tester")
    private String qualification;

    @NotNull
    @Min(value = 1)
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
