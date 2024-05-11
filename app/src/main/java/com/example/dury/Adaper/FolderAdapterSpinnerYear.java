package com.example.dury.Adaper;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dury.Model.FolderModel_ver2;
import com.example.dury.R;

import java.util.List;

public class FolderAdapterSpinnerYear extends ArrayAdapter<FolderModel_ver2> {

    private LayoutInflater mInflater;
    private int mResource;

    public FolderAdapterSpinnerYear(Context context, List<FolderModel_ver2> folders, int resource) {
        super(context, 0, folders);
        mInflater = LayoutInflater.from(context);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent);
    }

    private View createViewFromResource(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.txtDanhMuc); // Thay R.id.text_view bằng ID thích hợp trong layout item của bạn
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FolderModel_ver2 folder = getItem(position);
        if (folder != null) {
            holder.textView.setText(folder.getYear());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
    }
}
