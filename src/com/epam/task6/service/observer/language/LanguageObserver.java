package com.epam.task6.service.observer.language;

import com.epam.task6.dao.DAOException;
import com.epam.task6.dao.impl.UserDAO;
import com.epam.task6.domain.user.User;
import com.epam.task6.service.observer.Observer;

import java.util.EventObject;

/**
 * Created by olga on 30.04.15.
 */
public class LanguageObserver  implements Observer {
    /** Initializes error logger */
//    private static final Logger logger = Logger.getLogger("logic");

    /** Logger message */
    private static final String ERROR_UPDATE_LANG = "logger.observer.error.change.lang";

    /**
     * Updates database when user changing UI language.
     *
     * @param event May be instance of EventObject class
     */
    @Override
    public void handle(EventObject event) {
        LanguageEvent languageEvent = (LanguageEvent)event;
        User user = languageEvent.getSource();
        UserDAO dao = new UserDAO();
        try {
            dao.changeUILanguage(user.getLanguage(), user.getId());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

}
