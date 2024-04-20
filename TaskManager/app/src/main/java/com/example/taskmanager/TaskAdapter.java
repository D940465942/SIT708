package com.example.taskmanager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private static List<Task> task_list;

    private OnTaskClickListener onTaskClickListener;

    public interface OnTaskClickListener {
        void onTaskClick(Task task);
    }

    public TaskAdapter(List<Task> task_list, OnTaskClickListener onTaskClickListener) {
        this.task_list = task_list;
        this.onTaskClickListener = onTaskClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = task_list.get(position);
        holder.textview_title.setText(task.getTitle());
        holder.textview_description.setText(task.getDescription());
        holder.itemView.setOnClickListener(v -> onTaskClickListener.onTaskClick(task));
    }

    @Override
    public int getItemCount() {
        return task_list.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textview_title, textview_description;

        public MyViewHolder(View itemView) {
            super(itemView);
            textview_title = itemView.findViewById(R.id.textview_task_title);
            textview_description = itemView.findViewById(R.id.textview_task_description);
        }
    }
}
