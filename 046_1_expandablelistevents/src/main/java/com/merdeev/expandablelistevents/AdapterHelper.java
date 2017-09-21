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

    Context ctx;
    SimpleExpandableListAdapter adapter;

    final String ATTR_GROUP_NAME= "groupName";
    final String ATTR_PHONE_NAME= "phoneName";

    AdapterHelper (Context ctx) {
        this.ctx = ctx;
    }

    SimpleExpandableListAdapter getAdapter() {
        groups = ctx.getResources().getStringArray(R.array.groups);
        phonesHTC = ctx.getResources().getStringArray(R.array.phonesHTC);
        phonesSams = ctx.getResources().getStringArray(R.array.phonesSams);
        phonesLG = ctx.getResources().getStringArray(R.array.phonesLG);

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
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSams) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesLG) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String childFrom[] = new String[]{ATTR_PHONE_NAME};
        int childTo[] = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
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
