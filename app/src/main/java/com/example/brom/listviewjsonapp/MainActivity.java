package com.example.brom.listviewjsonapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;

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

//this application makes use of the library Picasso under the Apache License (Version 2.0). documentation available at https://square.github.io/picasso/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        new FetchData().execute();





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_refresh){
            new FetchData().execute();
            return true;
        }
        if(id == R.id.action_about){
            launchAboutActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void launchAboutActivity(){
        Intent activityIntent = new Intent (this, About.class);
        startActivity(activityIntent);

    }

    public void launchFrogDetailsActivity(View view, String frogInfo, String image, String license, String profileurl) {


        Intent intent = new Intent(this, FrogDetailsActivity.class);
        intent.putExtra("FROGINFO", frogInfo);
        intent.putExtra("IMGURL", image);
        intent.putExtra("license", license);
        intent.putExtra("profileurl", profileurl);

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
                URL url = new URL("http://wwwlab.iit.his.se/c14jonfr/VT19/androidProjectFrogs/frogJSON.json");

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

                JSONArray frgs= new JSONArray(o);


                Log.e("berra", "ok det funkar än så länge");


                final List<Frog> frogs = new ArrayList<Frog>();
                ImageView imageView;
                imageView = (ImageView)findViewById(R.id.imageView);
                for (int i = 0; i<frgs.length(); i++){
                    Log.e("berra", String.valueOf(i));
                    JSONObject frg = new JSONObject(frgs.getString(i));
                     String frgimg = new String(frg.getString("auxdata"));
                     Log.e("berra", frgimg);
                    frogs.add(new Frog(frg.getString("name"), frg.getString("location"), frg.getInt("size"), frg.getString("auxdata"), frg.getString("category"), frg.getString("cost")));
                }





                final List<String> frogData = new ArrayList<String> ();

                for (int i = 0; i < frogs.size(); i++) {
                   frogData.add(frogs.get(i).toString());

                }
                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), R.layout.list_item_textview, R.id.my_item_textview, frogData);

                final ListView myListView = (ListView) findViewById(R.id.listView);
                myListView.setAdapter(adapter);

                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String frogInfo = frogs.get(position).info();
                        String imgurl = frogs.get(position).image();
                        String profile = frogs.get(position).license();
                        String profileurl = frogs.get(position).profileurl();


                        launchFrogDetailsActivity(view, frogInfo, imgurl, profile, profileurl);


                    }
                });
            } catch(JSONException e){
                Log.e("berra", "E:" + e.getMessage());
            }


        }
    }
}



