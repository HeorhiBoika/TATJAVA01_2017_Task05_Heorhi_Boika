package com.company.bean;

import java.io.Serializable;

/**
 * Created by PC on 19.02.2017.
 */
public class Request implements Serializable{
    private String command;
    private String tagName;

    public Request(){

    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
