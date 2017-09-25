package com.merdeev.simpleadapterdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    final String TEXT = "text";
    final String IMAGE = "image";
    ListView lvSimple;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();
        for (int i=1; i<5; i++) {
            m = new HashMap<>();
            m.put(TEXT, "Sometext" + i);
            m.put(IMAGE, R.drawable.andro);
            data.add(m);
        }

        String[] from = {TEXT, IMAGE};
        int[] to = {R.id.tvText, R.id.ivImg};

        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
        lvSimple.setOnCreateContextMenuListener(this);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.lvSimple:
                getMenuInflater().inflate(R.menu.mymenu, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();

                data.remove(acmi.position);

                sAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        m = new HashMap<>();
        m.put(TEXT, "sometext " + (data.size() + 1));
        m.put(IMAGE, R.drawable.andro);

        data.add(m);

        sAdapter.notifyDataSetChanged();
    }
}
