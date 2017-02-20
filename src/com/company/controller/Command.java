package com.company.controller;

import com.company.bean.Request;
import com.company.bean.Response;
import com.company.controller.exception.ControllerException;

/**
 * Created by PC on 19.02.2017.
 */
public interface Command {
    Response execute(Request request) throws ControllerException;
}
