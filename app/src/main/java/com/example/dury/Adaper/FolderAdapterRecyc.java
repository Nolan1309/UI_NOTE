package com.example.dury.Adaper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dury.Model.FolderModel;
import com.example.dury.NoteDetailActivity;
import com.example.dury.NoteFragment;
import com.example.dury.NotificationDetailActivity;
import com.example.dury.NotificationFragment;
import com.example.dury.R;

import java.util.List;

public class FolderAdapterRecyc extends RecyclerView.Adapter<FolderAdapterRecyc.UserViewHolder> {

    private List<FolderModel> list;
    private Context mContext;
    private Fragment mFragment;
    public FolderAdapterRecyc(Context context, List<FolderModel> list, Fragment fragment) {
        this.mContext = context;
        this.list = list;
        this.mFragment = fragment;
    }

    @NonNull
    @Override
    public FolderAdapterRecyc.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderAdapterRecyc.UserViewHolder holder, int position) {

        final FolderModel model = list.get(position);
        if(model== null){
            return;
        }
        holder.name.setText(model.getFolderName());
        holder.year.setText(model.getYear());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(model);
            }
        });
    }
    private void onClickGoToDetail(FolderModel model){

        if (mFragment instanceof NoteFragment) {
            Intent intent = new Intent(mContext, NoteDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object",model);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        } else if (mFragment instanceof NotificationFragment){
            Intent intent = new Intent(mContext, NotificationDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("object",model);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }

        // Lấy ra Activity hiện tại từ Context
//        if (mContext instanceof Activity) {
//            Activity activity = (Activity) mContext;
//            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//        }
    }
    public FolderModel getItem(int position) {
        if (position < 0 || position >= list.size()) {
            return null; // Trả về null nếu vị trí không hợp lệ
        }
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        TextView name;
        TextView year;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.folderNameTextView);
            year = itemView.findViewById(R.id.yearTextView);
            layout = itemView.findViewById(R.id.layout_item_folder);

        }
    }

}
