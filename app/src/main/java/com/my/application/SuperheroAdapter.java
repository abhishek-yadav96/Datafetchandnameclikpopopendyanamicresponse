package com.my.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SuperheroAdapter extends RecyclerView.Adapter<SuperheroAdapter.ViewHolder> {

    private List<Superhero> superheroList;
    private Context context;

    public SuperheroAdapter (List<Superhero> superheroList, Context context){
        this.superheroList =superheroList;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_superhero,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Superhero superhero= superheroList.get(position);
        holder.nameTextView.setText(superhero.getName());
        holder.text_source.setText(superhero.getTeam());

        Glide.with(context)
                .load(superhero.getImageurl())
                .placeholder(R.drawable.pic)
                .error(R.drawable.pic) // You can set an error placeholder if the image loading fails
                .into(holder.img_headline);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuperheroPopupDialog dialog = new SuperheroPopupDialog(context, superhero);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return superheroList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView,text_source;
        ImageView img_headline;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView=itemView.findViewById(R.id.nameTextView);
            text_source=itemView.findViewById(R.id.text_source);
            img_headline=itemView.findViewById(R.id.img_headline);
        }
    }
}
