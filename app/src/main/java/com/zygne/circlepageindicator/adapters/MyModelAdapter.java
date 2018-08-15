package com.zygne.circlepageindicator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zygne.circlepageindicator.R;
import com.zygne.circlepageindicator.models.MyModel;

import java.util.List;

/**
 * Created by Bardur Thomsen on 8/15/18.
 */
public class MyModelAdapter extends RecyclerView.Adapter<MyModelAdapter.ViewHolder> {


    private Context context;
    private List<MyModel> list;


    public MyModelAdapter(Context context, List<MyModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.list_item_mymodel, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        final MyModel myModel = list.get(i);

        viewHolder.tvName.setText(myModel.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
