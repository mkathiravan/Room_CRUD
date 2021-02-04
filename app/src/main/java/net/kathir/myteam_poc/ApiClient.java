package net.kathir.myteam_poc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://www.allgeo.com";

   // public static final String BASE_URL = "https://staging2.allgeo.com";

    private static ApiInterface requestInterface;


    public static ApiInterface getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().
                readTimeout(40, TimeUnit.SECONDS)
                .connectTimeout(40, TimeUnit.SECONDS)
                .addInterceptor(interceptor).
                        build();

        if(requestInterface==null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            requestInterface = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build().create(ApiInterface.class);
        }
        return requestInterface;
    }

}
