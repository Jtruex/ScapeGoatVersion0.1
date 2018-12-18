package com.example.jtrue.scapegoatv01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public int price;
    public String baseIdUrl = "http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=317";
    public String finalUrl;
    private TextView resultTextView = (TextView) findViewById(R.id.resultTextView);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute("http://services.runescape.com/m=itemdb_oldschool/api/catalogue/detail.json?item=317");

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                String message = "";

                JSONObject jsonObject = new JSONObject("item");
                String itemInfo = jsonObject.getString("name");
                Log.i("Item Details", itemInfo);
                JSONArray arr = new JSONArray(itemInfo);
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonPart = arr.getJSONObject(i);

                    Log.i("main", jsonPart.getString("main"));
                    //String main = "";
                    //String description = "";
                    //main = jsonPart.getString("current");
                    //description = jsonPart.getString("description");

                    //if (main != "" && description != "") {

                      //  message += main + ": ";

                    //}
                //}
                if (message != "") {

                  resultTextView.setText(message);

                }
                    // else {
                  //  Toast.makeText(getApplicationContext(), "Could not find item data", Toast.LENGTH_LONG);
                }

            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Could not find item data", Toast.LENGTH_LONG);
                e.printStackTrace();
            }
        }
    }

}
