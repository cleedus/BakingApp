package com.example.cletrezo.bakingapp;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class RecipeWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {


        return new RecipeRemoteViewsFactory(this.getApplication(),intent);


    }



}
