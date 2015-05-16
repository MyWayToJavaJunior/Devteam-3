package com.epam.task6.test;

import com.epam.task6.domain.verifable.PreparedJob;
import com.epam.task6.domain.verifable.PreparedProject;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by olga on 05.05.15.
 */
public class ValidationProjectTest {
    private Validator validator;
    private PreparedProject project;

    @Before
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        PreparedJob job = new PreparedJob();
        job.setId(6);
        job.setCost(2000);
        ArrayList<String> mails = new ArrayList<>();
        mails.add("designer5@devteam.com");
        job.setEmployees(mails);
        project = new PreparedProject();
        project.setName("Design");
        project.setId(3);
        ArrayList<PreparedJob> map = new ArrayList<>();
        map.add(job);
        project.setJobs(map);
    }

    @Test
    public void testValid() {
        Set<ConstraintViolation<PreparedProject>> violations = validator.validate(project);
        assertEquals(violations.size(), 0);
    }
}
