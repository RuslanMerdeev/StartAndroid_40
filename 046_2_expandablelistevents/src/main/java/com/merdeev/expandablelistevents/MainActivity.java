package com.merdeev.expandablelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnChildClickListener, OnGroupClickListener, OnGroupCollapseListener, OnGroupExpandListener {

    final String LOG_TAG = "States";

    ExpandableListView elvMain;
    SimpleExpandableListAdapter adapter;
    AdapterHelper ah;
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tvInfo);

        ah = new AdapterHelper(this);
        adapter = ah.getAdapter();

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        elvMain.setOnChildClickListener(this);
        elvMain.setOnGroupClickListener(this);
        elvMain.setOnGroupExpandListener(this);
        elvMain.setOnGroupCollapseListener(this);

        elvMain.expandGroup(2);
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        Log.d(LOG_TAG, "onChildClick groupPosition = " + i +
                " childPosition = " + i1 +
                " id = " + l);
        tvInfo.setText(ah.getGroupChildText(i, i1));
        return false;
    }

    @Override
    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
        Log.d(LOG_TAG, "onGroupClick groupPosition = " + i +
                " id = " + l);
        if (i == 1) return true;
        return false;
    }

    @Override
    public void onGroupCollapse(int i) {
        Log.d(LOG_TAG, "onGroupCollapse groupPosition = " + i);
        tvInfo.setText("Свернули " + ah.getGroupText(i));
    }

    @Override
    public void onGroupExpand(int i) {
        Log.d(LOG_TAG, "onGroupExpand groupPosition = " + i);
        tvInfo.setText("Развернули " + ah.getGroupText(i));
    }
}
