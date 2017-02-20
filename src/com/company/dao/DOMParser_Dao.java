package com.company.dao;

import com.company.dao.exception.DAOException;

import java.util.List;

/**
 * Created by PC on 19.02.2017.
 */
public interface DOMParser_Dao {

    List domParser(String tagname) throws DAOException;
}
