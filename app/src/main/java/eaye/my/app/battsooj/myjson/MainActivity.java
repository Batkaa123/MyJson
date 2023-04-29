package eaye.my.app.battsooj.myjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button button_past = findViewById(R.id.button_past);

        mQueue = Volley.newRequestQueue(this);

        button_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonPast();
            }
        });
    }
    private void jsonPast() {
        String url = "https://jsonplaceholder.typicode.com/todos/1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("bataa");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject bataa = jsonArray.getJSONObject(i);

                                String firstName = bataa.getString("firetName");
                                int age = bataa.getInt("age");
                                String mail = bataa.getString("mail");

                                mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail+ "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mQueue.add(request);
    }

}