package com.merdeev.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by r.merdeev on 26.09.2017.
 */

public class BoxAdapter extends BaseAdapter implements OnCheckedChangeListener {

    Context ctx;
    ArrayList<Product> data;
    LayoutInflater lInflater;

    BoxAdapter(Context ctx, ArrayList<Product> data) {
        this.ctx = ctx;
        this.data = data;

        lInflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup vGroup) {
        View view = v;
        if (view == null) {
            view = lInflater.inflate(R.layout.item, vGroup, false);
        }

        Product p = getProduct(i);

        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price + "");
        ((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(this);
        cbBuy.setTag(i);
        cbBuy.setChecked(p.box);

        return view;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        getProduct((Integer) compoundButton.getTag()).box = b;
    }

    Product getProduct(int i) {
        return ((Product) getItem(i));
    }

    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<Product>();
        for (Product p : data) {
            if (p.box)
                box.add(p);
        }
        return box;
    }
}
