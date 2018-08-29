package com.example.cletrezo.bakingapp;

public interface CommunicationChannel {
    void passData(String url,String description, String thumbnailUrl, int position);
}
