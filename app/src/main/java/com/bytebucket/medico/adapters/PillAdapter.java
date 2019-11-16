package com.bytebucket.medico.adapters;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.models.PillReminder;
import com.bytebucket.medico.utilities.AlarmReceiver;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import static android.content.Context.ALARM_SERVICE;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.ViewHolder> {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context context;
    List<PillReminder> pillList;

    public PillAdapter(Context context, List<PillReminder> pillList) {
        this.context = context;
        this.pillList = pillList;
    }

    @NonNull
    @Override
    public PillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.pill_reminder_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PillAdapter.ViewHolder holder, final int position) {
        final PillReminder pillReminder = pillList.get(position);
        holder.pillName.setText(pillReminder.getPillName());
        holder.pillDosage.setText("Dosage instructions: " + pillReminder.getPillDosage());
        holder.pillTime.setText(pillReminder.getPillHour() + ":" + pillReminder.getPillMin());
        holder.pillFreq.setText("Repeat after every " + pillReminder.getPillFreq() + " hours");
        prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editor = prefs.edit();

        //set click to delete;
        holder.pillDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Reminder");
                StringBuilder sb = new StringBuilder();
                sb.append("Are you sure to delete?");
                builder.setMessage(sb.toString());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(context, AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, pillReminder.getPillId(), myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
                        alarmManager.cancel(pendingIntent);
                        editor.putString("name" + pillReminder.getPillId(), null);
                        editor.apply();
                        pillList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, pillList.size());
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pillList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pillName, pillDosage, pillTime, pillFreq;
        ImageView pillDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pillName = itemView.findViewById(R.id.pill_name);
            pillDosage = itemView.findViewById(R.id.pill_dosage);
            pillTime = itemView.findViewById(R.id.pill_time);
            pillDelete = itemView.findViewById(R.id.pill_delete);
            pillFreq = itemView.findViewById(R.id.pill_freq_text);
        }
    }
}
