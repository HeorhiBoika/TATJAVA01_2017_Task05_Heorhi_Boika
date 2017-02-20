package com.company.controller;

import com.company.bean.Request;
import com.company.bean.Response;
import com.company.controller.exception.ControllerException;
import com.company.view.Console;


/**
 * Created by PC on 19.02.2017.
 */
public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    public Response executeTask(Request request) {
        Response response;
        try {
            String commandName = request.getCommand();
            Command command = commandProvider.getCommand(commandName);
            response = command.execute(request);
        } catch (ControllerException e) {
            response = new Response();
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }

    protected void render(Response response) {
        if (response.getStatus() == 1) {
            Console.getInstance().printSuccessMessage(response.getSuccessMessage());
        } else if (response.getStatus() == -1) {
            Console.getInstance().printErrorMessage(response.getErrorMessage());
        } else {
            Console.getInstance().printErrorMessage(response.getErrorMessage());
        }
    }
}
