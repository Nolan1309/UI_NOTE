package com.example.dury;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dury.Adaper.FolderAdapter;
import com.example.dury.Adaper.FolderAdapterRecyc;
import com.example.dury.Adaper.FolderAdapterSpinner;
import com.example.dury.Adaper.SmallNoteAdapter;
import com.example.dury.Model.FolderModel;
import com.example.dury.Model.FolderModel_ver2;
import com.example.dury.Model.SmallNoteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    View view;
    private NotificationFragment mNotìticationFragment;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ListView folderListViewTB;
    RecyclerView smallNoteRecyclerViewTB;
    private FolderAdapter folderAdapter;
    private SmallNoteAdapter smallNoteAdapter;
    private ArrayList<FolderModel> folderList;
    private ArrayList<SmallNoteModel> smallNoteList;


    private FolderModel_ver2 folderModel_ver2;
    private List<FolderModel_ver2> listVer2 ;
    Spinner spinnerCategory,spinnerYear;
    LinearLayout linearLayoutTB;
    SearchView searchView;
    List<FolderModel> list ;
    Spinner year , category;
    FolderAdapterRecyc adapterRecyc;
    Button suaButton,btnTaoThongBao,btncomeback;
    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_notification, container, false);

        addController();
        addEvent();

        list = getList();
        mNotìticationFragment = this;
        adapterRecyc = new FolderAdapterRecyc(getActivity(),list,mNotìticationFragment);

        //Ngăn cách
        RecyclerView.ItemDecoration itemDecoration =new DividerItemDecoration(getActivity() , DividerItemDecoration.VERTICAL);
        smallNoteRecyclerViewTB.addItemDecoration(itemDecoration);
        smallNoteRecyclerViewTB.setAdapter(adapterRecyc);




        searchView = view.findViewById(R.id.search_item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) searchView.getLayoutParams();
                if (hasFocus) {
                    int marginInDp = 10;
                    int marginInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDp, getResources().getDisplayMetrics());
                    layoutParams.topMargin = marginInPx;
                    btnTaoThongBao.setVisibility(View.GONE);
                    linearLayoutTB.setVisibility(View.GONE);
                    smallNoteRecyclerViewTB.setVisibility(View.GONE);
                    year.setVisibility(View.GONE);
                    category.setVisibility(View.GONE);
                } else {
                    int marginInDp = 76;
                    int marginInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginInDp, getResources().getDisplayMetrics());
                    layoutParams.topMargin = marginInPx;
                    year.setVisibility(View.VISIBLE);
                    category.setVisibility(View.VISIBLE);
                    btnTaoThongBao.setVisibility(View.VISIBLE);
                    linearLayoutTB.setVisibility(View.VISIBLE);
                    smallNoteRecyclerViewTB.setVisibility(View.VISIBLE);
                }
                searchView.setLayoutParams(layoutParams);
            }
        });

        listVer2 = getListCategory();
        FolderAdapterSpinner adapter = new FolderAdapterSpinner(getActivity(), listVer2, R.layout.folder_item_ver2);

        spinnerCategory = view.findViewById(R.id.spnCategory); // Chỉnh sửa ID của Spinner theo mã XML của bạn
        spinnerCategory.setAdapter(adapter);


        spinnerYear = view.findViewById(R.id.spnYear); // Chỉnh sửa ID của Spinner theo mã XML của bạn
        spinnerYear.setAdapter(adapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Lấy đối tượng FolderModel_ver2 tương ứng với vị trí được chọn trong Spinner
                FolderModel_ver2 selectedCategory = listVer2.get(position);

                // Hiển thị thông tin của mục đã chọn
                Toast.makeText(getActivity(), "ID: " + selectedCategory.getId() + ", Tên: "+ selectedCategory.getFolderName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Không có mục nào được chọn
            }
        });
        return view;
    }
    private List<FolderModel_ver2> getListCategory(){
        List<FolderModel_ver2> categoryList = new ArrayList<>();
        categoryList.add(new FolderModel_ver2("1", "Phân bón","2023"));
        categoryList.add(new FolderModel_ver2("2", "Phun thuốc","2023"));
        return categoryList;
    }

    private List<FolderModel> getList() {
        List<FolderModel>  folderList = new ArrayList<>();
        folderList.add(new FolderModel("2023", "Phun thuốc đúng giờ 1"));
        folderList.add(new FolderModel("2022", "Phun thuốc đúng giờ 2"));
        folderList.add(new FolderModel("2021", "Phun thuốc đúng giờ 3"));
        folderList.add(new FolderModel("2020", "Phun thuốc đúng giờ 4"));
        folderList.add(new FolderModel("2019", "Phun thuốc đúng giờ 5"));
        folderList.add(new FolderModel("2018", "Phun thuốc đúng giờ 6"));
        folderList.add(new FolderModel("2017", "Phun thuốc đúng giờ 7"));
        return folderList;
    }

    void addController(){
//        suaButton = view.findViewById(R.id.buttonsuaTB);
        btnTaoThongBao = view.findViewById(R.id.btnTaoTB);
//        btncomeback = view.findViewById(R.id.buttonbackTB);
        //        folderListViewTB = findViewById(R.id.folderListViewTB);
        smallNoteRecyclerViewTB = view.findViewById(R.id.smallNoteRecyclerViewTB);
        searchView = view.findViewById(R.id.search_item);
        linearLayoutTB = view.findViewById(R.id.linearLayoutTB);

        year = view.findViewById(R.id.spnYear);
        category = view.findViewById(R.id.spnCategory);

    }
    void addEvent(){
//        suaButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showEditDialog(v);
//            }
//        });
        btnTaoThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificationDetailActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
//        btncomeback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });

    }
    private void initializeData() {
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
        smallNoteAdapter = new SmallNoteAdapter(getActivity(), filteredSmallNoteList);
        smallNoteRecyclerViewTB.setLayoutManager(new LinearLayoutManager(getActivity()));
        smallNoteRecyclerViewTB.setAdapter(smallNoteAdapter);


        // Hiển thị RecyclerView và ẩn ListView
        smallNoteRecyclerViewTB.setVisibility(View.VISIBLE);
    }


    // nut sua
//    public void showEditDialog(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Chọn dữ liệu để sửa");
//
//        // Tạo một ListView để hiển thị danh sách dữ liệu
//        ListView listView = new ListView();
//        listView.setAdapter(folderAdapter); // adapter là adapter của ListView của bạn
//
//        builder.setView(listView);
//        builder.setPositiveButton("Chọn", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Xử lý khi người dùng bấm vào nút Chọn
//                // Cập nhật giao diện để hiển thị ô vuông nhỏ cho việc chọn nhiều dòng dữ liệu
//                // Ví dụ: bằng cách thay đổi layout của mỗi item trong ListView
//            }
//        });
//
//        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // Xử lý khi người dùng bấm vào nút Hủy
//                dialog.dismiss();
//            }
//        });
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }
}