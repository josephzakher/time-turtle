package com.example.timeturtle.helperclasses;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeturtle.MainActivity;
import com.example.timeturtle.R;

import java.util.ArrayList;


public class TasksDatesAdapter  extends RecyclerView.Adapter<TasksDatesAdapter.DatesViewHolder> {
    private ArrayList<String> dates;
    private Context mContext;
    //Inner class to define a ViewHolder that holds a CardView object
    public static class DatesViewHolder extends RecyclerView.
            ViewHolder {
        private CardView cardView;

        public DatesViewHolder(CardView cv) {
            super(cv);
            cardView = cv;
        }
    }

    public TasksDatesAdapter(ArrayList<String> dates,Context context) {
        this.dates = dates;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DatesViewHolder onCreateViewHolder(@NonNull ViewGroup
                                                             viewGroup, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(viewGroup.
                getContext()).inflate(R.layout.date_card, viewGroup, false);
        return new DatesViewHolder(cv);
    }

    //onBindViewHolder will be called repeatedly for each ViewHolder object

    @Override
    public void onBindViewHolder(TasksDatesAdapter.DatesViewHolder viewHolder, int
            position) {
        CardView cardView = viewHolder.cardView;
        TextView textView = cardView.findViewById(R.id.card_date);
        textView.setText(dates.get(position));
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mContext instanceof MainActivity) {
                    ((MainActivity)mContext).testing(dates.get(position));
                        cardView.setCardBackgroundColor(Color.RED);
                }
            }
        });
    }
    //how many items are we displaying?
    @Override
    public int getItemCount() {
        return dates.size();
    }

}