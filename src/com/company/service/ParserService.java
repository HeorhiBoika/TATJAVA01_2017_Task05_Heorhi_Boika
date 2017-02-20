package com.company.service;

import com.company.service.exception.ServiceException;

import java.util.List;

/**
 * Created by PC on 19.02.2017.
 */
public interface ParserService {

    List parseXML(String command,String tagName) throws ServiceException;
}
