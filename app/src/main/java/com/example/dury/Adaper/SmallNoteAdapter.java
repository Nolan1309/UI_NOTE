package com.example.dury.Adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dury.R;
import com.example.dury.Model.SmallNoteModel;

import java.util.ArrayList;
public class SmallNoteAdapter extends RecyclerView.Adapter<SmallNoteAdapter.ViewHolder> {

    private ArrayList<SmallNoteModel> smallNoteList;
    private Context context;

    public SmallNoteAdapter(Context context, ArrayList<SmallNoteModel> smallNoteList) {
        this.context = context;
        this.smallNoteList = smallNoteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.small_note_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SmallNoteModel smallNoteItem = smallNoteList.get(position);
        holder.smallNoteNameTextView.setText(smallNoteItem.getSmallNoteName());
        holder.dateTextView.setText(smallNoteItem.getDate());
    }

    @Override
    public int getItemCount() {
        return smallNoteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView smallNoteNameTextView;
        public TextView dateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            smallNoteNameTextView = itemView.findViewById(R.id.smallNoteNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
