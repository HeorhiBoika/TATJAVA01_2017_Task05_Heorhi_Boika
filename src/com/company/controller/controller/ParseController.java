package com.company.controller.controller;

import com.company.bean.Menu;
import com.company.bean.Request;
import com.company.bean.response.ResponseListMenu;
import com.company.controller.Controller;
import com.company.view.Console;

import java.util.List;


/**
 * Created by PC on 19.02.2017.
 */
public class ParseController extends Controller {
    public void domParser(String tagName) {
        Request request = new Request();
        request.setCommand("DOM");
        request.setTagName(tagName);
        ResponseListMenu response = (ResponseListMenu) executeTask(request);
        render(response);
        if (response.getStatus() == 1) {
            printMenu(response);
        }
        Console console = Console.getInstance();
        console.consoleMenu();
    }

    public void saxParser() {
        Request request = new Request();
        request.setCommand("SAX");
        ResponseListMenu response = (ResponseListMenu) executeTask(request);
        render(response);
        if (response.getStatus() == 1) {
            printMenu(response);
        }
        Console console = Console.getInstance();
        console.consoleMenu();
    }

    public void staxParser() {
        Request request = new Request();
        request.setCommand("StAX");
        ResponseListMenu response = (ResponseListMenu) executeTask(request);
        render(response);
        if (response.getStatus() == 1) {
            printMenu(response);
        }
        Console console = Console.getInstance();
        console.consoleMenu();
    }


    public void printMenu(ResponseListMenu response) {
        List<Menu> listFood = response.getListFood();
        System.out.println("-------------------");
        for (Menu line : listFood) {
            System.out.println(line);

        }
        System.out.println("-------------------");
    }
}
