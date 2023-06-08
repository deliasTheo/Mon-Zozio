package edu.polytech.Mon_Zozio;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;


public class EbirdApiClient {
    private static final String BASE_URL = "https://api.ebird.org/v2/";
    private static final String API_KEY = "rtng477kn01j";
    String regionCode = "FR";

    private RequestQueue requestQueue;

    public EbirdApiClient(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getRecentObservations(Response.Listener<JSONObject> successListener, Response.ErrorListener errorListener) {
        String url = BASE_URL+"data/obs/"+regionCode+"/geo/recent";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, successListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("X-eBirdApiToken", API_KEY);
                return headers;
            }
        };
        requestQueue.add(request);
    }

}