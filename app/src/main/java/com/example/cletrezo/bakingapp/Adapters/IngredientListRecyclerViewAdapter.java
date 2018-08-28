package com.example.cletrezo.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.model.Ingredients;
import java.util.List;


public class IngredientListRecyclerViewAdapter extends RecyclerView.Adapter<IngredientListRecyclerViewAdapter.CustomViewHolder> {
    private Context context;
    private List<Ingredients>ingredientsList;
    private LayoutInflater inflater;



    public IngredientListRecyclerViewAdapter(Context context, List<Ingredients> ingredientsList) {
        this.context = context;
        this.ingredientsList = ingredientsList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_list_of_ingredient_recycler,parent , false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.quantity.setText(String.valueOf(ingredientsList.get(position).getQuantity()));
        holder.measure.setText(ingredientsList.get(position).getMeasure());
        holder.ingredients.setText(ingredientsList.get(position).getIngredient());

    }

    @Override
    public int getItemCount() {

        return ingredientsList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView measure;
        TextView ingredients;
        TextView quantity;

        public CustomViewHolder(View itemView) {
            super(itemView);

            this.ingredients = itemView.findViewById(R.id.ingredient_value);
            this.quantity = itemView.findViewById(R.id.quantity_value);
            this.measure = itemView.findViewById(R.id.measure_value);

        }
    }

}
