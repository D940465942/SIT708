package com.example.LostFoundApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LostAndFoundAdapter extends RecyclerView.Adapter<LostAndFoundAdapter.ViewHolder> {
    private List<DBHelper.ItemDescription> itemList;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int itemId);
    }
    public LostAndFoundAdapter(List<DBHelper.ItemDescription> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view, parent, false); // Inflate the layout for an item
        return new ViewHolder(view); // Return the new ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DBHelper.ItemDescription item = itemList.get(position);
        if (holder.textDescription == null || holder.idTextView == null) {
            throw new NullPointerException("TextView or idTextView is null");
        }
        holder.textDescription.setText(item.getDescription());
        holder.idTextView.setText(String.valueOf(item.getId()));
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int itemId = Integer.parseInt(holder.idTextView.getText().toString());
                onItemClickListener.onItemClick(itemId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textDescription;
        TextView idTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            textDescription = itemView.findViewById(R.id.textDescription);
            idTextView = itemView.findViewById(R.id.idTextView);
        }
    }
}
