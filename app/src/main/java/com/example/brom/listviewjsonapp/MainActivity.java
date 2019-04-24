package com.example.brom.listviewjsonapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FetchData().execute();
        //final Mountain m0 = new Mountain("Matterhorn", "Alps", 4478);
        //final Mountain m1 = new Mountain("Mont Blanc", "Alps", 4808);
        //final Mountain m2 = new Mountain("Denali", "Alaska", 6190);

      //  final List<Mountain> mountains = new ArrayList<Mountain>();
       //mountains.add(m0);
       // mountains.add(m1);
       // mountains.add(m2);



       // final List<String> mountainData = new ArrayList<String> ();

       // for (int i = 0; i < mountains.size(); i++) {
        //    mountainData.add(mountains.get(i).toString());
       // }



        //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview, R.id.my_item_textview, mountainData);

       // final ListView myListView = (ListView) findViewById(R.id.listView);
       // myListView.setAdapter(adapter);

       // myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


         //   @Override
        //    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          //      String mountainInfo = mountains.get(position).info();
          //      launchMountainDetailsActivity(view, mountainInfo);


         //   }
       // });

    }
    public void launchMountainDetailsActivity(View view, String mountainInfo) {


        Intent intent = new Intent(this, MountainDetailsActivity.class);
        intent.putExtra("MOUNTAININFO", mountainInfo);
        startActivity(intent);
    }

    private class FetchData extends AsyncTask<Void,Void,String>{
        @Override
        protected String doInBackground(Void... params) {
            // These two variables need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a Java string.
            String jsonStr = null;

            try {
                // Construct the URL for the Internet service
                URL url = new URL("http://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");

                // Create the request to the PHP-service, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                jsonStr = buffer.toString();
                return jsonStr;
            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in
                // attempting to parse it.
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("Network error", "Error closing stream", e);
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);

            // This code executes after we have received our data. The String object o holds
            // the un-parsed JSON string or is null if we had an IOException during the fetch.

            // Implement a parsing code that loops through the entire JSON and creates objects
            // of our newly created Mountain class.

            try{

                JSONArray mtns= new JSONArray(o);


                Log.e("berra", "ok det funkar än så länge");


                final List<Mountain> mountains = new ArrayList<Mountain>();

                for (int i = 0; i<mtns.length(); i++){
                    Log.e("berra", String.valueOf(i));
                    JSONObject mtn = new JSONObject(mtns.getString(i));
                    mountains.add(new Mountain(mtn.getString("name"), mtn.getString("location"), mtn.getInt("size")));
                }

                final List<String> mountainData = new ArrayList<String> ();

                for (int i = 0; i < mountains.size(); i++) {
                   mountainData.add(mountains.get(i).toString());

                }
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview, R.id.my_item_textview, mountainData);

                final ListView myListView = (ListView) findViewById(R.id.listView);
                myListView.setAdapter(adapter);

                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String mountainInfo = mountains.get(position).info();
                        launchMountainDetailsActivity(view, mountainInfo);


                    }
                });
            } catch(JSONException e){
                Log.e("berra", "E:" + e.getMessage());
            }


        }
    }
}



