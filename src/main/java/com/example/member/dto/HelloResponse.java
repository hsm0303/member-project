package com.example.member.dto;

public class HelloResponse {

    private String message;
    private int Count;

    public HelloResponse(String message, int count) {
        this.message = message;
        Count = count;
    }

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return Count;
    }
}
