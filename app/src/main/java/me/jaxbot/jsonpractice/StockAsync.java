package me.jaxbot.jsonpractice;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pc on 3/14/2017.
 */

public class StockAsync extends AsyncTask<String,Void,ArrayList<Items>> {
    private StockDownloadListener listener;
    @Override
    protected ArrayList<Items> doInBackground(String... strings) {
        String urlstring = strings[0];
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL url = new URL(urlstring);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNext()) {
                stringBuffer.append(sc.nextLine());
            }


        } catch (MalformedURLException e) {


            return null;
        } catch (IOException e) {

        }
        Log.i("TTTT",stringBuffer.toString());
        return parseStockList(stringBuffer.toString());

    }


    private ArrayList<Items> parseStockList(String json) {

        ArrayList<Items> itemss= new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONObject query = object.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONArray quotes = results.getJSONArray("quote");
            for (int i = 0; i < quotes.length(); i++) {
                JSONObject stockobject = quotes.getJSONObject(i);


                String symbol = stockobject.getString("symbol");
                String name = stockobject.getString("Currency");
                String changeinpercent = stockobject.getString("Change_PercentChange");
                Items item=new Items(name,changeinpercent,symbol);
                itemss.add(item);
                Log.i("ttt", changeinpercent);

            }
            return itemss;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    void setStockDownloadListener(StockDownloadListener listener) {
        this.listener = listener;

    }

    protected void onPostExecute(ArrayList<Items> item) {
        super.onPostExecute(item);

        if (listener != null)
            listener.onDownloadComplete(item);

    }


    public interface StockDownloadListener {
        void onDownloadComplete(ArrayList<Items> item);
    }
}
