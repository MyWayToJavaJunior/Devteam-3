package com.epam.task6.test;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.user.User;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by olga on 05.05.15.
 */
public class SignInFormTest {
    //private SignInForm form = new SignInForm();
    //private Validator validator;

    @Before
    public void init() {
       // ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        //validator = factory.getValidator();
    }

    @Test
    public void testForm() {
        UserDAOImpl loginDAO = UserDAOImpl.getInstance();
        try {
            User user = loginDAO.checkUserMailAndPassword("olga@com.com", "olga");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        //form.setLogin("olga@com.com");
       // form.setPassword("olga");
       // Set<ConstraintViolation<SignInForm>> violations = validator.validate(form);
       // assertEquals(violations.size(), 0);
    }

}