package com.example.hiep.bds.utilts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.ListPopupWindow;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.example.hiep.bds.R;
import java.util.ArrayList;

public class CustemSpinner extends ArrayAdapter<String> {

// Your sent context
private Context context;
// Your custom values for the spinner (User)
private ArrayList<String> values;

public CustemSpinner(Context context, int textViewResourceId,
        ArrayList<String> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
        }

@Override
public int getCount(){
        return values.size();
        }

@Override
public String getItem(int position){
        return values.get(position);
        }

@Override
public long getItemId(int position){
        return position;
        }


// And the "magic" goes here
// This is for the "passive" state of the spinner
@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position));

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
        }

// And here is when the "chooser" is popped up
// Normally is the same view, but you can customize it if you want
@Override
public View getDropDownView(int position, View convertView,
        ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position));

        return label;
        }
        }