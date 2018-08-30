
package com.example.cletrezo.bakingapp.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cletrezo.bakingapp.Adapters.RecipeRecyclerViewAdapter;
import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.utils.RecipeDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RecipeFragment extends Fragment{
    private RecipeDataSource recipeDataSource = new RecipeDataSource();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;
    public RecipeFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // getting Json data from Network
        new RecipeDataAsyncTask().execute("https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json");



      //[reference]
//https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi
       /* String json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("recipeJson");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }*/









        final View rootView = inflater.inflate(R.layout.default_launch_layout,container,false );

         //recipe recyclerView
         recyclerView = rootView.findViewById(R.id.recipe_recyclerView);
         recyclerView.setHasFixedSize(true);

         boolean deviceType = getResources().getBoolean(R.bool.isTablet);

         //checking device size
         if(deviceType){
             recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));


         }else {
             recyclerView.setLayoutManager(linearLayoutManager);
         }
        // Log.v("size", String.valueOf(recipeDataSource.getRecipeModelList().size()));
        /*recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext(),recipeDataSource.getRecipeModelList());
        recyclerView.setAdapter(recipeRecyclerViewAdapter);
*/
         return  rootView;



    }


    public class  RecipeDataAsyncTask extends AsyncTask<String, Void, Integer> {
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
                }else {
                    inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    String jsonString = stringBuilder.toString();
                    recipeDataSource.JsonParser(jsonString);
                    result = 1;


                }
            } catch (IOException e) {
                e.printStackTrace();
            }



            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(result==1){
                recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext(),recipeDataSource.getRecipeModelList());
                recyclerView.setAdapter(recipeRecyclerViewAdapter);


            }
            super.onPostExecute(result);
        }
    }


}
