package com.company;

import com.company.view.Console;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
	// write your code here
        Console console = Console.getInstance();
        console.consoleMenu();
    }
}
