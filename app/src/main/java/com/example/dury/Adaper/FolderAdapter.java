package com.example.dury.Adaper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dury.R;
import com.example.dury.Model.FolderModel;

import java.util.ArrayList;
public class FolderAdapter extends ArrayAdapter<FolderModel> {
    public FolderAdapter(Context context, ArrayList<FolderModel> folderList) {
        super(context, 0, folderList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FolderModel folderItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.folder_item, parent, false);
        }

        TextView folderNameTextView = convertView.findViewById(R.id.folderNameTextView);
        TextView yearTextView = convertView.findViewById(R.id.yearTextView);

        folderNameTextView.setText(folderItem.getFolderName());
        yearTextView.setText(folderItem.getYear());

        return convertView;
    }
}
