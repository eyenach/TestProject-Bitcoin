package com.example.bitcoin.entities;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private int status;
    private String result;
    private List<Bitcoin> data = new ArrayList<Bitcoin>();

    public Response() {
    }

    public Response(int status, String result) {
        this.status = status;
        this.result = result;
    }

    public Response(int status, String result, List<Bitcoin> data) {
        this.status = status;
        this.result = result;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }

    public List<Bitcoin> getData() {
        return data;
    }
    public void setData(List<Bitcoin> data) {
        this.data = data;
    }

}
