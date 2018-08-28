package com.example.cletrezo.bakingapp;

import com.example.cletrezo.bakingapp.model.RecipeModel;

public interface CommunicationChannel {
    void passData(String url,String description, int position);
}
