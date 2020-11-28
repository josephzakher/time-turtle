package com.example.timeturtle.helperclasses;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeturtle.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> tasks;

    //Inner class to define a ViewHolder that holds a CardView object
    public static class ViewHolder extends RecyclerView.
            ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
    }

    public TaskAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                 viewGroup, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.
                getContext()).inflate(R.layout.event_card, viewGroup, false);
        return new ViewHolder(cv);
    }

    //onBindViewHolder will be called repeatedly for each ViewHolder object

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int
            position) {
        CardView cardView = viewHolder.cardView;
        //ImageView imageView = cardView.findViewById(R.id.icon);
        //imageView.setImageResource(tasks[position].getIconID());
        //imageView.setContentDescription(tasks[position].getName());
        TextView textView = cardView.findViewById(R.id.task_text);
        textView.setText(tasks.get(position).getName());
        TextView timeView = cardView.findViewById(R.id.date);
        timeView.setText(tasks.get(position).getDate());
        TextView descriptionTextView = cardView.findViewById(R.id.description);
        descriptionTextView.setText(tasks.get(position).getDescription());
    }

    //how many items are we displaying?
    @Override
    public int getItemCount() {
        return tasks.size();
    }

}