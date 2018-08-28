package com.example.cletrezo.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cletrezo.bakingapp.CommunicationChannel;
import com.example.cletrezo.bakingapp.Fragments.RecipeInstructionFragment;
import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.RecipeDetailActivity;
import com.example.cletrezo.bakingapp.RecipeVideoInstructionActivity;
import com.example.cletrezo.bakingapp.model.Steps;

import java.util.List;

public class RecipeStepsRecyclerViewAdapter extends RecyclerView.Adapter<RecipeStepsRecyclerViewAdapter.CustomViewHolder> {
    public   int position;
    RecipeDetailActivity recipeDetailActivity = new RecipeDetailActivity();

    private Context context;
    public List<Steps> recipeStepsList;
    private LayoutInflater inflater;
    private CommunicationChannel communicationChannel;

    public RecipeStepsRecyclerViewAdapter() {
    }

    public RecipeStepsRecyclerViewAdapter(Context context, List<Steps> recipeStepsList) {
        this.context = context;
        this.recipeStepsList = recipeStepsList;
        inflater = LayoutInflater.from(context);
       communicationChannel= (CommunicationChannel) context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_view_recipe_detail,parent , false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.textView.setText(recipeStepsList.get(position).getShortDescription());

    }

    @Override
    public int getItemCount() {
        return recipeStepsList.size();
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public CustomViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.recipe_steps_item);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     position= getLayoutPosition();
                    if(position!=RecyclerView.NO_POSITION) {
                        if(RecipeDetailActivity.deviceType){ communicationChannel.passData(recipeStepsList.get(position).getVideoUrl(),recipeStepsList.get(position).getDescription() ,position );
                        }else {
                            Intent intent = new Intent(context, RecipeVideoInstructionActivity.class);
                            if (recipeStepsList.get(position).getVideoUrl().isEmpty()) {
                                intent.putExtra("VIDEOURL", "NO VIDEO");
                            }
                            intent.putExtra("VIDEOURL", recipeStepsList.get(position).getVideoUrl());
                            intent.putExtra("DESCRIPTION", recipeStepsList.get(position).getDescription());
                            intent.putExtra("position", position);
                            context.startActivity(intent);
                        }
                    }

                }
            });
        }
    }
}
