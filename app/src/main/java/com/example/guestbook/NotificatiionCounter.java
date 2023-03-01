package com.example.guestbook;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class NotificatiionCounter {
    private TextView notificationCounter;
    private final int MAX_NUMBER = 99;
    private int notification_number_counter = 0;

    public NotificatiionCounter(View view){
        notificationCounter = view.findViewById(R.id.txt_numberNotif);
    }

    public void increaseNumber(){
        notification_number_counter++;
        if (notification_number_counter > MAX_NUMBER){
            Log.d("Counter","Maximum Number Reached!");
        }else {
            notificationCounter.setText(String.valueOf(notification_number_counter));
        }
    }
}
