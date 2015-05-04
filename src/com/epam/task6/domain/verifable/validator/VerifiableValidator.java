package com.epam.task6.domain.verifable.validator;

import com.epam.task6.dao.impl.UserDAO;
import com.epam.task6.domain.verifable.PreparedJob;
import com.epam.task6.domain.verifable.PreparedProject;
import com.epam.task6.domain.verifable.ProposedOrder;
import com.epam.task6.domain.verifable.SignInForm;
import org.apache.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by olga on 21.04.15.
 */
public class VerifiableValidator {
    /** Initializes logger */
    private static Logger logger = Logger.getLogger("errors");

    /** Logger message */
    private static final String ERROR_CHECK_STATUS = "logger.validator.error.check.status";

    /** Keeps validator factory */
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /** Keeps validator object */
    private static Validator validator = factory.getValidator();

    /**
     * This method verifies ProposedOrder object.
     *
     * @param order ProposedOrder object
     * @return True if data correct, otherwise false
     */
    public static boolean isOrderValid(ProposedOrder order) {
        Set<ConstraintViolation<ProposedOrder>> violations = validator.validate(order);
        return violations.size() == 0 && order.getCount() == order.getJobs().size();
    }

    /**
     * This method verifies SignInForm object.
     *
     * @param form SignInForm object
     * @return True if data correct, otherwise false.
     */
    public static boolean isSignInFormValid(SignInForm form) {
        Set<ConstraintViolation<SignInForm>> violations = validator.validate(form);
        return violations.size() == 0;
    }

    /**
     * This method verifies PreparedProject object.
     *
     * @param project PreparedProject object
     * @return True if data correct, otherwise false.
     */
    public static boolean isPreparedProjectValid(PreparedProject project) {
        Set<ConstraintViolation<PreparedProject>> violations = validator.validate(project);
        if (violations.size() != 0) {
            return false;
        }
        ArrayList<PreparedJob> jobs = project.getJobs();
        UserDAO dao = new UserDAO();
        for (PreparedJob job : jobs) {
            ArrayList<String> mails = job.getEmployees();
        }
        return true;
    }

}
