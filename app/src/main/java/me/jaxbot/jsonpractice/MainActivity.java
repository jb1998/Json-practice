package me.jaxbot.jsonpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements StockAsync.StockDownloadListener{
    ArrayList<Items> items;
    My_adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items = new ArrayList<>();
        listView = (ListView) findViewById(R.id.courses);
        adapter = new My_adapter(this, items);
        listView.setAdapter(adapter);

        fetchdata();
    }

    private void fetchdata() {
        String urlString = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22%2C%22AAPL%22%2C%22GOOG%22%2C%22MSFT%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        StockAsync task = new StockAsync();
        task.setStockDownloadListener(this);
        task.execute(urlString);
    }

    @Override
    public void onDownloadComplete(ArrayList<Items> item) {
        if(item==null)
        {
            Log.i("mmm", "onDownloadComplete: ");
            return;
        }
        items.clear();
     //   items.addAll(item);

        for(int i = 0; i < item.size(); i++){
            Log.i("ttt", "goes");
           items.add(item.get(i));
            Toast.makeText(this,items.get(i).name+"",Toast.LENGTH_SHORT);

        }

        adapter.notifyDataSetChanged();
    }
}
