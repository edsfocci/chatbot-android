// http://stackoverflow.com/questions/20059576/import-android-volley-to-android-studio
package com.edsfoci.android.cleverchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL =
            "http://ed-chatbot.herokuapp.com/?text=";

    private TextView mYouText;
    private TextView mBotText;
    private EditText mInputText;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mYouText = (TextView) findViewById(R.id.you_text);
        mBotText = (TextView) findViewById(R.id.bot_text);

        mInputText = (EditText) findViewById(R.id.input_text);
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String requestText = mInputText.getText().toString();

                mYouText.setText("You: " + requestText);
                // URL url = new URL(API_URL + );
                // new RetrieveFeedTask().execute();

                // Instantiate the RequestQueue.
                mRequestQueue = Volley.newRequestQueue(MainActivity.this);
                String url = API_URL + requestText;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mBotText.setText("Robot: " + response);
                        Log.i("console.log", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error){
                        // Log.i("console.log", error.toString());
                        mBotText.setText("Robot: That didn't work!");
                    }
                });
                // Add the request to the RequestQueue.
                mRequestQueue.add(stringRequest);

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
/*
    class RetrieveFeedTask extends AsyncTask<Void, Void, String> {

        private Exception exception;

        // protected void onPreExecute() {
        //     progressBar.setVisibility(View.VISIBLE);
        //     responseView.setText("");
        // }

        protected String doInBackground(Void... urls) {
            String inputText = mInputText.getText().toString();
            // Do some validation here

            try {
                URL url = new URL(API_URL + inputText);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText(response);
            // TODO: check this.exception
            // TODO: do something with the feed

//            try {
//                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
//                JSONArray photos = object.getJSONArray("photos");
//                .
//                .
//                .
//                .
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }*/
}
