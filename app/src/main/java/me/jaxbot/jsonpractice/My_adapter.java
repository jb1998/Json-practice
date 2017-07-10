package me.jaxbot.jsonpractice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pc on 3/14/2017.
 */

public class My_adapter extends ArrayAdapter<Items> {
    Context mcontext;
    ArrayList<Items> mitem;
    public My_adapter(Context context, ArrayList<Items> objects) {
        super(context,0,objects);
        mcontext=context;
        mitem=objects;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            convertView=View.inflate(mcontext,R.layout.one_item_look,null);
        }
        TextView name=(TextView)convertView.findViewById(R.id.name);
        TextView change=(TextView)convertView.findViewById(R.id.change);
        TextView symbol = (TextView)convertView.findViewById(R.id.symbol);

        Items item=mitem.get(position);
        name.setText(item.name);
        change.setText(item.percentagechange+"");
        symbol.setText(item.symbol);

        return convertView;
    }
}
