package com.company.bean;

import java.io.Serializable;

/**
 * Created by PC on 19.02.2017.
 */
public class Response implements Serializable {

    private String errorMessage;
    private String successMessage;
    private Integer status;

    public Response(){

    }

    public Integer getStatus() {

        return status;
    }

    public void setStatus(Integer status) {

        this.status = status;

    }

    public String getErrorMessage() {

        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {

        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {

        this.successMessage = successMessage;

    }
}
