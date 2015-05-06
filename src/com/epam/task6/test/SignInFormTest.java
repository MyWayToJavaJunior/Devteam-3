package com.epam.task6.test;

import com.epam.task6.domain.verifable.SignInForm;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by olga on 05.05.15.
 */
public class SignInFormTest {
    private SignInForm form = new SignInForm();
    private Validator validator;

    @Before
    public void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testForm() {
        //UserDAO loginDAO = UserDAO.getInstance();
        //User user = loginDAO.checkUserMailAndPassword("olga@com.com", "olga");
        form.setLogin("alibaba@mail.ru");
        form.setPassword("1");
        Set<ConstraintViolation<SignInForm>> violations = validator.validate(form);
        assertEquals(violations.size(), 0);
    }

}