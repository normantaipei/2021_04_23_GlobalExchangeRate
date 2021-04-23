package com.example.mid_ui4a62;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String ITEM_TITLE = "Item title";
    private static final String ITEM_NUM = "Item num";
    private static final String ITEM_ICON = "Item icon";

    private TextView mTxtR;
    private ListView mListViewRegion;
    private Button mCleanAll,mAddAll,mAddnew;
    private boolean[] R_regionList;
    private String[] regionList;
    private String[] numList;
    private TypedArray regionIconList;
    private List<Map<String, Object>> itemList;
    //private Map<String, Object> item;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTxtR = findViewById(R.id.txtR);
        mListViewRegion = findViewById(R.id.listViewRegion);
        mCleanAll = findViewById(R.id.removeAll);
        mAddAll = findViewById(R.id.addAll);
        mAddnew = findViewById(R.id.newList);

        //int nowcoin = 3;


        regionList = getResources().getStringArray(R.array.coin);
        R_regionList = new boolean[regionList.length];
        for (int i = 0; i < regionList.length; i++) {
           R_regionList[i]=false;
        }
        //R_regionList[0] = regionList[0];
        for (int i = 0; i < 5; i++) {
            R_regionList[i]=true;
        }
        numList = getResources().getStringArray(R.array.num);
        regionIconList =
                getResources().obtainTypedArray(R.array.region_icon_list);

        //TypedArray R_regionIconList;
        itemList = new ArrayList<Map<String, Object>>();
        //item = new HashMap<String, Object>();



        for (int i = 0; i < regionList.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            if(R_regionList[i]){
                item.put(ITEM_NUM,numList[i]);
                item.put(ITEM_TITLE, regionList[i]);
                item.put(ITEM_ICON, regionIconList.getResourceId(i, 0));
                itemList.add(item);
            }

        }

        SimpleAdapter simAdapListViewRegion = new SimpleAdapter(
                MainActivity.this, itemList,
                R.layout.list_view_item,
                new String[] {ITEM_NUM,ITEM_TITLE, ITEM_ICON},
                new int[] {R.id.txtView2, R.id.txtView, R.id.imgView});
        mListViewRegion.setAdapter(simAdapListViewRegion);

        mListViewRegion.setOnItemClickListener(listViewRegionOnItemClick);
        //mListViewRegion.setOnItemLongClickListener();
        mCleanAll.setOnClickListener(cleanAll);
        mAddAll.setOnClickListener(addAll);
    }


    private  View.OnClickListener addNew = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };
    private View.OnClickListener addAll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int j = 0; j < regionList.length; j++) {
                R_regionList[j] = true;

            }
            for (int i = 0; i < regionList.length; i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                if (R_regionList[i]) {
                    item.put(ITEM_NUM, numList[i]);
                    item.put(ITEM_TITLE, regionList[i]);
                    item.put(ITEM_ICON, regionIconList.getResourceId(i, 0));
                    itemList.add(item);
                }
            }
            SimpleAdapter simAdapListViewRegion = new SimpleAdapter(
                    MainActivity.this, itemList,
                    R.layout.list_view_item,
                    new String[] {ITEM_NUM,ITEM_TITLE, ITEM_ICON},
                    new int[] {R.id.txtView2, R.id.txtView, R.id.imgView});
            mListViewRegion.setAdapter(simAdapListViewRegion);
        }

    };

    private View.OnClickListener cleanAll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           // Map<String, Object> item = new HashMap<String, Object>();
            //item.clear();
            itemList.clear();
            SimpleAdapter simAdapListViewRegion = new SimpleAdapter(
                    MainActivity.this, itemList,
                    R.layout.list_view_item,
                    new String[] {ITEM_NUM,ITEM_TITLE, ITEM_ICON},
                    new int[] {R.id.txtView2, R.id.txtView, R.id.imgView});
            mListViewRegion.setAdapter(simAdapListViewRegion);


        }
    };

    private AdapterView.OnItemClickListener listViewRegionOnItemClick
            = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //String s = getString(R.string.region_selected);
            TextView txtView = view.findViewById(R.id.txtView);
            TextView txtView2 = view.findViewById(R.id.txtView2);
            String updown = txtView2.getText().toString();
            Float uptest = Float.parseFloat(updown);
            TextView textView3 = view.findViewById(R.id.txtR);
            Double sum = 1.0/uptest;
            updown = sum.toString();


            String s= "1TWD=" + updown+ " " + txtView.getText()+"\n1"+txtView.getText()+"="+txtView2.getText()+"TWD";
            //mTxtR.setText(updown);
            Toast.makeText(view.getContext(),
                    s, Toast.LENGTH_LONG)
                    .show();


        }
    };

}