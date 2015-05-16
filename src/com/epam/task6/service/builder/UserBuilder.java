package com.epam.task6.service.builder;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.UserDAOImpl;
import com.epam.task6.domain.user.User;
import com.epam.task6.domain.verifable.SignInForm;
import com.epam.task6.resource.ResourceManager;
import com.epam.task6.service.ServiceException;
import com.epam.task6.util.Hasher;

/**
 * This class contain method building user object.
 *
 * Created by olga on 21.04.15.
 */
public final class UserBuilder {

    private static final String USER_BUILDER_ERROR = "";

    public static User buildUser(SignInForm form) throws ServiceException {
        User user = null;
               UserDAOImpl dao = UserDAOImpl.getInstance();
            try {
                user = dao.checkUserMailAndPassword(form.getLogin(), Hasher.getMD5(form.getPassword()));
            } catch (DAOException e) {
                throw new ServiceException(ResourceManager.getProperty(USER_BUILDER_ERROR), e);
            }

        return user;
    }

}