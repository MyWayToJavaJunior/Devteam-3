package com.epam.task6.service.builder;

import com.epam.task6.dao.impl.UserDAO;
import com.epam.task6.domain.user.User;
import com.epam.task6.domain.verifable.SignInForm;
import com.epam.task6.domain.verifable.validator.VerifiableValidator;
import com.epam.task6.util.Hasher;

/**
 * Created by olga on 21.04.15.
 */
public class UserBuilder {

    public static User buildUser(SignInForm form)  {
        User user = null;
        if (VerifiableValidator.isSignInFormValid(form)) {
               UserDAO dao = new UserDAO();
               user = dao.checkUserMailAndPassword(form.getLogin(), Hasher.getMD5(form.getPassword()));
        }
        return user;
    }

}