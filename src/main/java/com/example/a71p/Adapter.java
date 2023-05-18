package com.example.a71p;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.LostAndFoundViewHolder> {

    private SetOnItemClickListener setOnItemClickListener;
    private ArrayList<LostAndFoundModel> list = new ArrayList<>();

    // Method to submit a new list of data to the adapter
    public void submit(ArrayList<LostAndFoundModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    // Constructor
    public Adapter(SetOnItemClickListener setOnItemClickListener) {
        this.setOnItemClickListener = setOnItemClickListener;
    }

    // ViewHolder class for holding the views of each item in the RecyclerView
    public static class LostAndFoundViewHolder extends RecyclerView.ViewHolder {
        private TextView heading;
        private TextView detail;

        // Constructor
        public LostAndFoundViewHolder(View view) {
            super(view);
            heading = view.findViewById(R.id.heading);
            detail = view.findViewById(R.id.detail);
        }

        // Bind the data to the views of the ViewHolder
        public void bind(final LostAndFoundModel lostAndFoundModel, final SetOnItemClickListener setOnItemClickListener) {
            // Set the text for the heading and detail views
            heading.setText(lostAndFoundModel.getIsLostOrFound() + ": " + lostAndFoundModel.getName());
            detail.setText(new StringBuilder()
                    .append(lostAndFoundModel.getDate()).append("\n")
                    .append(lostAndFoundModel.getLocation()).append("\n")
                    .append(lostAndFoundModel.getPhone()).append("\n")
                    .append(lostAndFoundModel.getDescription())
                    .toString());

            // Set click listener for the item view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setOnItemClickListener.onItemClickListener(lostAndFoundModel);
                }
            });
        }
    }

    @Override
    public LostAndFoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the view for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view, parent, false);
        return new LostAndFoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LostAndFoundViewHolder holder, int position) {
        // Bind the data to the ViewHolder
        holder.bind(list.get(position), setOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

// Interface for handling item click events in the RecyclerView
interface SetOnItemClickListener {
    void onItemClickListener(LostAndFoundModel lostAndFoundModel);
}