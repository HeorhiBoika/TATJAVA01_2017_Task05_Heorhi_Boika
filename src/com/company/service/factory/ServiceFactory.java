package com.company.service.factory;

import com.company.service.ParserService;
import com.company.service.impl.ParseServiceImpl;

/**
 * Created by PC on 19.02.2017.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ParserService parserService = new ParseServiceImpl();

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ParserService getParserService() {
        return parserService;
    }
}
