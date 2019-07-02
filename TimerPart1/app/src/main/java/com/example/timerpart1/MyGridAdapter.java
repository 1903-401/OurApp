package com.example.timerpart1;

import android.content.Context;
import android.graphics.Color;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Locale;

public class MyGridAdapter extends ArrayAdapter {

    List<Date> dates;
    Calendar currentDate;
    List<Events> events;
    LayoutInflater inflater;
    Button delete;

    public MyGridAdapter(Context context, List<Date> dates, Calendar currentDate, List<Events> events) {
        super(context, R.layout.single_cell_layout);

        this.dates=dates;
        this.currentDate = currentDate;
        this.events = events;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Date monthDate = dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int DayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH)+1;
        int currentYear = currentDate.get(Calendar.YEAR);


        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.single_cell_layout, parent, false);

        }

        if (displayMonth == currentMonth && displayYear == currentYear) {
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.orange));
        }
        else {
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.black));
        }

        TextView Day_Number = view.findViewById(R.id.calendar_day);
        TextView EventNumber = view.findViewById(R.id.events_id);
        delete = view.findViewById(R.id.deleteButton);
        Day_Number.setText(String.valueOf(DayNo));
        Calendar eventCalendar = Calendar.getInstance();
        final ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            eventCalendar.setTime(ConvertStringToDate(events.get(i).getDATE()));
            if (DayNo == eventCalendar.get(Calendar.DAY_OF_MONTH) &&
                    displayMonth == eventCalendar.get(Calendar.MONTH)+1 &&
                    displayYear == eventCalendar.get(Calendar.YEAR)) {
                arrayList.add(events.get(i).getEVENT());
                EventNumber.setText(arrayList.size() + " Events");
            }

        }

        return view;
    }

    private Date ConvertStringToDate(String eventDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(eventDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;

    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public int getPosition(Object item) {
        return dates.indexOf(item);
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }
}



