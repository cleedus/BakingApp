package com.example.cletrezo.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String RECIPE_IN_CURRENT_CLICKED_POSITION = "theRecipeInCurrentClickedPosition";
    /*RecipeDataSource recipeDataSource = new RecipeDataSource();
    String jsonString = null;


    //Recycler
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*boolean deviceType = getResources().getBoolean(R.bool.isTablet);
        //recipe recyclerView
        recyclerView = findViewById(R.id.recipe_recyclerView);
        recyclerView.setHasFixedSize(true);

        new RecipeDataAsyncTask().execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");

        //checking device size
        if (deviceType) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        } else {
            recyclerView.setLayoutManager(linearLayoutManager);
        }


    }


    public class RecipeDataAsyncTask extends AsyncTask<String, Void, Integer> {
        private Integer result = 0;

        @Override
        protected Integer doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                int status = httpURLConnection.getResponseCode();
                InputStream inputStream;
                if (status != HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getErrorStream();
                    result = 0;
                } else {
                    inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    jsonString = stringBuilder.toString();
                    recipeDataSource.JsonParser(jsonString);
                    result = 1;


                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            if(result==1){
                recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(MainActivity.this,recipeDataSource.getRecipeModelList());
                recyclerView.setAdapter(recipeRecyclerViewAdapter);
            }
            super.onPostExecute(integer);
        }
    }*/
    }
}



