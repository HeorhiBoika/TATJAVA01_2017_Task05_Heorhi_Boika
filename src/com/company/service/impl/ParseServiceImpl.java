package com.company.service.impl;

import com.company.bean.Menu;
import com.company.dao.DOMParser_Dao;
import com.company.dao.SAXParser_Dao;
import com.company.dao.StAXParser_Dao;
import com.company.dao.exception.DAOException;
import com.company.dao.factory.DAOFactory;
import com.company.service.ParserService;
import com.company.service.exception.ServiceException;

import java.util.List;

/**
 * Created by PC on 19.02.2017.
 */
public class ParseServiceImpl implements ParserService {

    public ParseServiceImpl() {

    }

    @Override
    public List parseXML(String command, String tagName) throws ServiceException {
        List<Menu> foodList = null;
        if (command.toLowerCase().trim().equals("dom")) {
            if (tagName.equals("hotSnacks") | tagName.equals("coldSnacks") |
                    tagName.equals("breakfast")) {
                DAOFactory daoFactory = DAOFactory.getInstance();
                DOMParser_Dao domParser_dao = daoFactory.getDomParserDAO();
                try {
                    foodList = domParser_dao.domParser(tagName);
                } catch (DAOException e) {
                    throw new ServiceException(e.getMessage(), e);
                }
            } else {
                throw new ServiceException("Error,wrong name of tag!!!");
            }
        }
        if (command.toLowerCase().trim().equals("sax")) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            SAXParser_Dao saxParser_dao = daoFactory.getSaxParserDao();
            try {
                foodList = saxParser_dao.sax_Parser();
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        if (command.toLowerCase().trim().equals("stax")) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            StAXParser_Dao stAXParser_dao = daoFactory.getStAXParserDao();
            try {
                foodList = stAXParser_dao.stax_Parser();
            } catch (DAOException e) {
                throw new ServiceException(e.getMessage(), e);
            }
        }
        return foodList;
    }
}
