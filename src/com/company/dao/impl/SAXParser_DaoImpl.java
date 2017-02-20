package com.company.dao.impl;

import com.company.bean.Menu;
import com.company.dao.SAXParser_Dao;
import com.company.dao.exception.DAOException;
import com.company.dao.impl.sax.MySaxParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;


/**
 * Created by PC on 19.02.2017.
 */
public class SAXParser_DaoImpl implements SAXParser_Dao {

    private final static String path = "C:\\Users\\PC\\IdeaProjects\\Task5\\src\\com\\company\\menu.xml";

    public SAXParser_DaoImpl(){

    }

    @Override
    public List sax_Parser() throws DAOException {
        MySaxParser parser;
        List<Menu> food;
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            parser = new MySaxParser();
            reader.setContentHandler(parser);
            reader.parse(new InputSource(path));
            food = parser.getListFood();
        }catch (SAXException | IOException |NumberFormatException e){
            throw new DAOException(e.getMessage(),e);
        }
        return food;
    }
}
