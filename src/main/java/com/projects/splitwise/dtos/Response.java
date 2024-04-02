package com.projects.splitwise.dtos;

import lombok.Data;

@Data
public class Response {

    private ResponseType type;
    private String errorMsg;

    public static Response getSuccessResponse() {
        Response response = new Response();
        response.setType(ResponseType.SUCCESS);
        return response;
    }

    public static Response getFailureResponse(String errorMsg) {
        Response response = new Response();
        response.setType(ResponseType.FAILURE);
        response.setErrorMsg(errorMsg);
        return response;
    }

}
