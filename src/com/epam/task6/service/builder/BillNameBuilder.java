package com.epam.task6.service.builder;

import java.util.Calendar;

/**
 * This class for build bill names.
 *
 * Created by olga on 08.05.15.
 */
public class BillNameBuilder {
    /* Default bill name prefix */
    private static final String INVOICE = "INVOICE-";

    /**
     * This method creates bill name for new bill
     *
     * @param name Last bill name in database
     * @return new bill name
     */
    public static String createBillName(String name) {
        int number = 1;
        if(!name.equals("")){
            name = name.replaceFirst("-", "+");
            number += Integer.parseInt(name.substring(name.indexOf("+") + 1, name.indexOf("-")));
        }
        Calendar now = Calendar.getInstance();
        return INVOICE + number + "-" + now.get(Calendar.YEAR);
    }

}
