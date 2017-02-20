package com.company.dao.factory;

import com.company.dao.DOMParser_Dao;
import com.company.dao.SAXParser_Dao;
import com.company.dao.StAXParser_Dao;
import com.company.dao.impl.DOMParser_DaoImpl;
import com.company.dao.impl.SAXParser_DaoImpl;
import com.company.dao.impl.StAXParser_DaoImpl;

/**
 * Created by PC on 19.02.2017.
 */
public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final DOMParser_Dao domParserDAO = new DOMParser_DaoImpl();
    private final SAXParser_Dao saxParserDao = new SAXParser_DaoImpl();
    private final StAXParser_Dao stAXParserDao = new StAXParser_DaoImpl();

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public DOMParser_Dao getDomParserDAO() {
        return domParserDAO;
    }

    public SAXParser_Dao getSaxParserDao() {
        return saxParserDao;
    }

    public StAXParser_Dao getStAXParserDao() {
        return stAXParserDao;
    }
}
