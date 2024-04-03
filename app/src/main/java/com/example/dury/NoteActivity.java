package com.example.dury;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dury.Adaper.FolderAdapter;
import com.example.dury.Adaper.SmallNoteAdapter;
import com.example.dury.Model.FolderModel;
import com.example.dury.Model.SmallNoteModel;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    private ListView folderListView;
    private RecyclerView smallNoteRecyclerView;
    private FolderAdapter folderAdapter;
    private SmallNoteAdapter smallNoteAdapter;
    private ArrayList<FolderModel> folderList;
    private ArrayList<SmallNoteModel> smallNoteList;


    Button suaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        folderListView = findViewById(R.id.folderListView);
        smallNoteRecyclerView = findViewById(R.id.smallNoteRecyclerView);

        // Khởi tạo danh sách lớp học và học sinh
        initializeData();

        // Thiết lập adapter cho ListView của danh sách lớp học
        folderAdapter = new FolderAdapter(this, folderList);
        folderListView.setAdapter(folderAdapter);

        // Xử lý sự kiện khi chọn một lớp học từ ListView
        folderListView.setOnItemClickListener((parent, view, position, id) -> {
            FolderModel selectedClass = folderList.get(position);
            Toast.makeText(NoteActivity.this, "Bạn đã chọn: " + selectedClass.getFolderName(), Toast.LENGTH_SHORT).show();

            // Hiển thị danh sách học sinh tương ứng với lớp học được chọn
            showSmallNoteList(selectedClass);
        });


        // nut sửa
        suaButton = findViewById(R.id.buttonsua);

        suaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị dialog hoặc menu để cho phép người dùng chọn chức năng
                showEditDialog(v);
            }
        });


    }

    private void initializeData() {
        // Khởi tạo danh sách lớp học
        folderList = new ArrayList<>();
        folderList.add(new FolderModel("2023", "Phun thuốc"));
        folderList.add(new FolderModel("2024", "Bón phân"));


        // Khởi tạo danh sách học sinh (giả sử mỗi lớp có cùng một danh sách học sinh)
        smallNoteList = new ArrayList<>();
        smallNoteList.add(new SmallNoteModel( "Phun thuốc","01/01/2000" , "Title 1"));
        smallNoteList.add(new SmallNoteModel("Phun thuốc","02/02/2001", "Title 2"));
        smallNoteList.add(new SmallNoteModel("Bón phân","03/03/2002", "Title 3"));
    }

    private void showSmallNoteList(FolderModel selectedModel) {
        // Lọc danh sách small note tương ứng với folder được chọn
        ArrayList<SmallNoteModel> filteredSmallNoteList = new ArrayList<>();
        for (SmallNoteModel smallnote : smallNoteList) {
            if (smallnote.getFolderName().equals(selectedModel.getFolderName())) {
                filteredSmallNoteList.add(smallnote);
            }
        }

        // Thiết lập adapter cho RecyclerView của danh sách học sinh
        smallNoteAdapter = new SmallNoteAdapter(this, filteredSmallNoteList);
        smallNoteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        smallNoteRecyclerView.setAdapter(smallNoteAdapter);

//        // Hiển thị RecyclerView và ẩn ListView
//        smallNoteRecyclerView.setVisibility(android.view.View.VISIBLE);
//        folderListView.setVisibility(android.view.View.GONE);

        // Hiển thị RecyclerView và ẩn ListView
//        smallNoteRecyclerView.setVisibility(View.VISIBLE);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) folderListView.getLayoutParams();
//        params.addRule(RelativeLayout.ABOVE, R.id.smallNoteRecyclerView);
//        folderListView.setLayoutParams(params);

        // Hiển thị RecyclerView và ẩn ListView
        smallNoteRecyclerView.setVisibility(View.VISIBLE);
    }


    // nut sua
    public void showEditDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn dữ liệu để sửa");

        // Tạo một ListView để hiển thị danh sách dữ liệu
        ListView listView = new ListView(this);
        listView.setAdapter(folderAdapter); // adapter là adapter của ListView của bạn

        builder.setView(listView);
        builder.setPositiveButton("Chọn", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng bấm vào nút Chọn
                // Cập nhật giao diện để hiển thị ô vuông nhỏ cho việc chọn nhiều dòng dữ liệu
                // Ví dụ: bằng cách thay đổi layout của mỗi item trong ListView
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng bấm vào nút Hủy
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }





}