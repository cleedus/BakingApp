package com.example.cletrezo.bakingapp.Adapters;

import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.cletrezo.bakingapp.CommunicationChannel;
import com.example.cletrezo.bakingapp.MainActivity;
import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.RecipeDetailActivity;
import com.example.cletrezo.bakingapp.model.RecipeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.CustomViewHolder> {

    private Context context;
    private List<RecipeModel> recipeModelList;
    private LayoutInflater inflater;
    private ImageView imageView;



    public RecipeRecyclerViewAdapter(Context context, List<RecipeModel> recipeModelList) {
        this.context = context;
        this.recipeModelList = recipeModelList;
        inflater= LayoutInflater.from((android.content.Context) context);
        //communicationChannel = (CommunicationChannel) context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_recipe,parent ,false );
        return new CustomViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.recipe_name.setText(recipeModelList.get(position).getName());


        switch (recipeModelList.get(position).getName()) {
            case "Nutella Pie":
                LoadImage(R.drawable.nutella_pie);
                break;

            case "Brownies":
                LoadImage(R.drawable.brownies);
                break;
            case "Yellow Cake":
                LoadImage(R.drawable.yellow_cake);
                break;

            case "Cheesecake":
                LoadImage(R.drawable.cheesecake);
                break;

            default:
                LoadImage(R.drawable.no_video_available);

        }

    }


    @Override
    public int getItemCount() {

        return recipeModelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        CardView card_view;
        TextView recipe_name;


        private CustomViewHolder(View itemView) {
            super(itemView);
            this.card_view = itemView.findViewById(R.id.single_recipe_cardView);
            this.recipe_name = itemView.findViewById(R.id.recipe_name);
            imageView =itemView.findViewById(R.id.recipe_image);


            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getLayoutPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        Intent intent = new Intent(context, RecipeDetailActivity.class);
                        intent.putExtra(MainActivity.RECIPE_IN_CURRENT_CLICKED_POSITION,recipeModelList.get(position));
                        intent.putExtra("currentPosition", position);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    private void LoadImage(int image)
    {

        Picasso.get()
                .load(image)
                .into(imageView);
    }

}
