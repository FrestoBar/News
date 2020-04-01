package com.koshm.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koshm.news.models.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<History> histories;
    private Context context;

    public HistoryAdapter(Context context, ArrayList<History> histories) {
        this.histories = histories;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.title.setText(histories.get(position).getTitle());
        holder.url.setText(histories.get(position).getUrl());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, histories.get(position).getUrl() + " is oppening.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(histories.get(position).getUrl()));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView url;
        RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.historyTitle);
            url = itemView.findViewById(R.id.historyUrl);
            layout = itemView.findViewById(R.id.historyItem);

        }
    }
}
