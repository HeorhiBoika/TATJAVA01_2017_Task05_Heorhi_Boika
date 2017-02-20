package com.company.controller.impl;

import com.company.bean.Menu;
import com.company.bean.Request;
import com.company.bean.Response;
import com.company.bean.response.ResponseListMenu;
import com.company.controller.Command;
import com.company.controller.exception.ControllerException;
import com.company.service.ParserService;
import com.company.service.exception.ServiceException;
import com.company.service.factory.ServiceFactory;

import java.util.List;

/**
 * Created by PC on 19.02.2017.
 */
public class SaxParser implements Command {
    @Override
    public Response execute(Request request) throws ControllerException {
        ResponseListMenu response = new ResponseListMenu();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ParserService parserService = serviceFactory.getParserService();
        List<Menu> menuList;
        try {
            menuList = parserService.parseXML(request.getCommand(), request.getTagName());
            response.setListFood(menuList);
            response.setSuccessMessage("ParseSAX XMl success!!!");
            response.setStatus(1);
        } catch (ServiceException e) {
            response.setErrorMessage(e.getMessage());
            response.setStatus(-1);
        }
        return response;
    }
}
