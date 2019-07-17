package com.example.timerpart1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;


// called in the longClick method in CustomCalendarView - sets up the arraylist to view in the
// recyclerView

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder> {


    Context context;
    ArrayList<Events> arrayList;



    public EventRecyclerAdapter(Context context, ArrayList<Events> arrayList) {
        this.context = context;
        this.arrayList = arrayList;


    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_rowlayout,
                parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Events events = arrayList.get(position);
        holder.Event.setText(events.getEVENT());
        holder.Datetxt.setText(events.getDATE());
        holder.Time.setText(events.getTIME());

    }


    // sets up a count so that under each date, it says "X events", where X is the number of events

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Datetxt, Event, Time;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);
            Datetxt = itemView.findViewById(R.id.eventdate);
            Event = itemView.findViewById(R.id.eventname);
            Time = itemView.findViewById(R.id.eventime);


        }
    }

}
