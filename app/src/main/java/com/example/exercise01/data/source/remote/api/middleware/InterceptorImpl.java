package com.example.exercise01.data.source.remote.api.middleware;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.net.HttpURLConnection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class InterceptorImpl implements Interceptor {

    private static final String TAG = "InterceptorImpl";
    private static final String TOKEN_TYPE = "Bearer ";
    private static final String KEY_TOKEN = "Authorization";

    private static InterceptorImpl sInstance;


    public static synchronized InterceptorImpl getInstance() {
        if (sInstance == null) {
            sInstance = new InterceptorImpl();
        }
        return sInstance;
    }


    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        //TODO check connection

        Request.Builder builder = initializeHeader(chain);
        Request request = builder.build();
        Response response = chain.proceed(request);

        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            refreshToken();

            builder.removeHeader(KEY_TOKEN);

          /*  String accessToken = this.mTokenRepository.getToken();
            if (accessToken != null && accessToken.isEmpty()) {
                builder.addHeader(KEY_TOKEN, TOKEN_TYPE + accessToken);
            }*/

            request = builder.build();
            response = chain.proceed(request);
        }
        return response;
    }

    private Request.Builder initializeHeader(Chain chain) {
        Request originRequest = chain.request();
        Request.Builder builder = originRequest.newBuilder()
                .header("Accept", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Cache-Control", "no-store")
                .method(originRequest.method(), originRequest.body());

       /* String accessToken = this.mTokenRepository.getToken();
        if (accessToken != null) {
            builder.addHeader(KEY_TOKEN, TOKEN_TYPE + accessToken);
        }*/
        return builder;
    }

    private void refreshToken() {
        //TODO Call Api refresh token
    }
}