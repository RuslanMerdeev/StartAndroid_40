package com.merdeev.expandablelistevents;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by r.merdeev on 20.09.2017.
 */

public class AdapterHelper {

    String[] groups;
    String[] phonesHTC;
    String[] phonesSams;
    String[] phonesLG;
    String[] colorsHTC;
    String[] colorsSams;
    String[] colorsLG;

    Context ctx;
    SimpleExpandableListAdapter adapter;

    final String ATTR_GROUP_NAME= "groupName";
    final String ATTR_PHONE_NAME= "phoneName";
    final String ATTR_COLOR_NAME= "colorName";

    AdapterHelper (Context ctx) {
        this.ctx = ctx;
    }

    SimpleExpandableListAdapter getAdapter() {
        groups = ctx.getResources().getStringArray(R.array.groups);
        phonesHTC = ctx.getResources().getStringArray(R.array.phonesHTC);
        phonesSams = ctx.getResources().getStringArray(R.array.phonesSams);
        phonesLG = ctx.getResources().getStringArray(R.array.phonesLG);
        colorsHTC = ctx.getResources().getStringArray(R.array.colorsHTC);
        colorsSams = ctx.getResources().getStringArray(R.array.colorsSams);
        colorsLG = ctx.getResources().getStringArray(R.array.colorsLG);

        Map<String, String> m;

        ArrayList<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            m = new HashMap<String, String>();
            m.put(ATTR_GROUP_NAME, group);
            groupData.add(m);
        }

        String groupFrom[] = new String[]{ATTR_GROUP_NAME};
        int groupTo[] = new int[]{android.R.id.text1};

        ArrayList<ArrayList<Map<String, String>>> childData = new ArrayList<ArrayList<Map<String, String>>>();

        ArrayList<Map<String, String>> childDataItem;

        childDataItem = new ArrayList<Map<String, String>>();
        for (int i=0; i<phonesHTC.length; i++) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phonesHTC[i]);
            m.put(ATTR_COLOR_NAME, colorsHTC[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (int i=0; i<phonesSams.length; i++) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phonesSams[i]);
            m.put(ATTR_COLOR_NAME, colorsSams[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (int i=0; i<phonesLG.length; i++) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phonesLG[i]);
            m.put(ATTR_COLOR_NAME, colorsLG[i]);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String childFrom[] = new String[]{ATTR_PHONE_NAME, ATTR_COLOR_NAME};
        int childTo[] = new int[]{R.id.tvName, R.id.tvColor};

        adapter = new SimpleExpandableListAdapter(ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                R.layout.item,
                childFrom,
                childTo);

        return adapter;
    }

    String getGroupText(int groupPos) {
        return ((Map<String,String>)(adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }

    String getChildText(int groupPos, int childPos) {
        return ((Map<String,String>)(adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText(int groupPos, int childPos) {
        return getGroupText(groupPos) + " " +  getChildText(groupPos, childPos);
    }
}
