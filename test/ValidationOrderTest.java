package com.epam.task6.test;

import com.epam.task6.domain.verifable.ProposedOrder;
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
public class ValidationOrderTest {
    private ProposedOrder order;
    private Validator validator;

    @Before
    public void init() {
        order = new ProposedOrder();
        order.setSpecification("c");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testOrder() {
        Set<ConstraintViolation<ProposedOrder>> violations = validator.validate(order);
        assertEquals(violations.size(), 0);
    }

}
