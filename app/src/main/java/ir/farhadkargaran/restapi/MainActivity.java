package ir.farhadkargaran.restapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url_signup = "http://farhadkargaran.ir/apps/restapitest/apis/signup.php";
    String url_login = "http://farhadkargaran.ir/apps/restapitest/apis/login.php";
    String url_del = "http://farhadkargaran.ir/apps/restapitest/apis/del.php";
    String url_update = "http://farhadkargaran.ir/apps/restapitest/apis/update.php";
    String url_user = "http://farhadkargaran.ir/apps/restapitest/apis/user.php";

    RecyclerView recyclerView;
    EditText ed_name,ed_email,ed_password,ed_contact,ed_id;
    TextView txt_response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide notification bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //-----------------
        setContentView(R.layout.activity_main);

        //-----------------
        initialize();
    }
    private void initialize()
    {
        ed_id = findViewById(R.id.ed_id);
        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_contact = findViewById(R.id.ed_contact);
        txt_response = findViewById(R.id.txt_response);
        txt_response.setMovementMethod(new ScrollingMovementMethod());
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    public void signup(View view) {

        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("name", ed_name.getText().toString());
        postParam.put("email", ed_email.getText().toString());
        postParam.put("password", ed_password.getText().toString());
        postParam.put("contact", ed_contact.getText().toString());

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url_signup, new JSONObject(postParam), new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {

                txt_response.setText(response.toString());
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getMessage() == null)
                txt_response.setText("Error was null, may be the path of API files on server is not correct");
                else txt_response.setText(error.getMessage());
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("token", "7EE9DA7D594CFF9F134268A3AC98E10BEBA66194");

                return headers;
            }

        };
        // Adding request to request queue
        queue.add(jsonObjReq);

    }

    public void signin(View view) {
        recyclerView.setVisibility(View.INVISIBLE);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        Map<String, String> postParam= new HashMap<String, String>();

        postParam.put("email", ed_email.getText().toString());
        postParam.put("password", ed_password.getText().toString());


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url_login, new JSONObject(postParam), new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {
                txt_response.setText(response.toString());
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getMessage() == null)
                    txt_response.setText("Error was null, may be the path of API files on server is not correct");
                else txt_response.setText(error.getMessage());            }
        }) {
            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("token", "7EE9DA7D594CFF9F134268A3AC98E10BEBA66194");

                return headers;
            }
        };
        // Adding request to request queue
        queue.add(jsonObjReq);
    }

    public void Getusers(View view) {
           recyclerView.setVisibility(View.VISIBLE);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            Map<String, String> postParam= new HashMap<String, String>();
            postParam.put("name", ed_name.getText().toString());
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url_user, new JSONObject(postParam), new Response.Listener<JSONObject>()
            {

                @Override
                public void onResponse(JSONObject response)
                {
                    txt_response.setText(response.toString());
                    try {
                        CustomAdapter ca = new CustomAdapter(response.getJSONArray("data"));
                        recyclerView.setAdapter(ca);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(error.getMessage() == null)
                        txt_response.setText("Error was null, may be the path of API files on server is not correct");
                    else txt_response.setText(error.getMessage());                }
            }) {
                /**
                 * Passing some request headers
                 * */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    headers.put("token", "7EE9DA7D594CFF9F134268A3AC98E10BEBA66194");

                    return headers;
                }
            };
            // Adding request to request queue
            queue.add(jsonObjReq);

    }

    public void delete(View view) {
        recyclerView.setVisibility(View.INVISIBLE);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        Map<String, String> postParam= new HashMap<String, String>();
        if(view.getTag().toString().equalsIgnoreCase("id"))
        postParam.put("id", ed_id.getText().toString());
        else if(view.getTag().toString().equalsIgnoreCase("email"))
        postParam.put("email", ed_email.getText().toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url_del, new JSONObject(postParam), new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                txt_response.setText(response.toString());
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getMessage() == null)
                    txt_response.setText("Error was null, may be the path of API files on server is not correct");
                else txt_response.setText(error.getMessage());            }
        }) {
            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("token", "7EE9DA7D594CFF9F134268A3AC98E10BEBA66194");

                return headers;
            }
        };
        // Adding request to request queue
        queue.add(jsonObjReq);
    }
    public void update(View view) {
        recyclerView.setVisibility(View.INVISIBLE);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        Map<String, String> postParam= new HashMap<String, String>();
        postParam.put("id", ed_id.getText().toString());
        postParam.put("name", ed_name.getText().toString());
        postParam.put("email", ed_email.getText().toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,url_update, new JSONObject(postParam), new Response.Listener<JSONObject>()
        {

            @Override
            public void onResponse(JSONObject response)
            {
                txt_response.setText(response.toString());
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.getMessage() == null)
                    txt_response.setText("Error was null, may be the path of API files on server is not correct");
                else txt_response.setText(error.getMessage());            }
        }) {
            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("token", "7EE9DA7D594CFF9F134268A3AC98E10BEBA66194");
                return headers;
            }
        };
        // Adding request to request queue
        queue.add(jsonObjReq);
    }



    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        private JSONArray localDataSet;
        public  class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView tv_id,tv_name,tv_email,tv_contact;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                tv_id = (TextView) view.findViewById(R.id.tv_id);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                tv_email = (TextView) view.findViewById(R.id.tv_email);
                tv_contact = (TextView) view.findViewById(R.id.tv_contact);
            }
            public TextView getTv_id() {
                return tv_id;
            }
            public TextView getTv_name() {
                return tv_name;
            }
            public TextView getTv_email() {
                return tv_email;
            }
            public TextView getTv_contact() {
                return tv_contact;
            }
        }
        public CustomAdapter(JSONArray dataSet) {
            localDataSet = dataSet;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            try {
                String id = String.valueOf(localDataSet.getJSONObject(position).getInt("id"));
                viewHolder.getTv_id().setText(id);
                viewHolder.getTv_name().setText(localDataSet.getJSONObject(position).getString("name"));
                viewHolder.getTv_email().setText(localDataSet.getJSONObject(position).getString("email"));
                viewHolder.getTv_contact().setText(localDataSet.getJSONObject(position).getString("contact"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public int getItemCount() {
            return localDataSet.length();
        }
    }

}