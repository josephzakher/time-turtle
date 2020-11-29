package com.example.timeturtle.helperclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeturtle.MainActivity;
import com.example.timeturtle.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class TasksDatesAdapter  extends RecyclerView.Adapter<TasksDatesAdapter.DatesViewHolder> {
    private ArrayList<DateElement> dateElements;
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

    public TasksDatesAdapter(ArrayList<DateElement> dateElements, Context context) {
        this.dateElements = dateElements;
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

//    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(TasksDatesAdapter.DatesViewHolder viewHolder, int
            position) {
        CardView cardView = viewHolder.cardView;
        TextView textView = cardView.findViewById(R.id.card_date);
        textView.setText(dateElements.get(position).getDate());
        if(dateElements.get(position).getSelected()){
            Log.d(TAG, "onBindViewHolder: "+"selected");
//            final int color = R.color.purple_500;
            cardView.setCardBackgroundColor(Color.rgb(47,62,158));
            textView.setTextColor(Color.WHITE);
        }
        else
        {
            Log.d(TAG, "onBindViewHolder: "+"not selected");
            cardView.setCardBackgroundColor(Color.WHITE);
            textView.setTextColor(Color.BLACK);
        }
        cardView.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if (mContext instanceof MainActivity) {
                    ((MainActivity)mContext).switchBetweenDateFragment(dateElements.get(position).getDate(),position);
                }
            }
        });
    }
    //how many items are we displaying?
    @Override
    public int getItemCount() {
        return dateElements.size();
    }

}