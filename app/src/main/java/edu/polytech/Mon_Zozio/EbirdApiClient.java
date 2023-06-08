package edu.polytech.Mon_Zozio;

import java.io.IOException;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EbirdApiClient {
    private static final String BASE_URL = "https://api.ebird.org/v2/";
    private static final String API_KEY = "rtng477kn01j";


    OkHttpClient client = new OkHttpClient().newBuilder()
            .build();
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "");
    Request request = new Request.Builder()
            .url("https://api.ebird.org/v2/data/obs/FR/recent")
            .method("GET", body)
            .addHeader("X-eBirdApiToken", API_KEY)
            .build();
    //Response response = client.newCall(request).execute();

    public EbirdApiClient() throws IOException {
    }

    /*public EbirdApiClient(OkHttpClient client, MediaType mediaType, RequestBody body, Request request, Response response) {
        this.client = client;
        this.mediaType = mediaType;
        this.body = body;
        this.request = request;
        this.response = response;
    }*/

}