package com.epam.task6.dao.api;

import com.epam.task6.dao.DAOException;
import com.epam.task6.domain.project.Bill;

import java.util.List;

/**
 * Created by olga on 08.05.15.
 */
public interface BillDAO {
    public List<Bill> getCustomerBills(int id) throws DAOException;
    public List<Bill> getManagerBills(int id) throws DAOException;
    public String getLastBillName() throws DAOException;
    public void createBill(String name, int cid, int pid, int mid, int sum) throws DAOException;
}
