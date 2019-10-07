package com.example.exercise01.data.source.remote.network;

import com.example.exercise01.App;
import com.example.exercise01.BuildConfig;
import com.example.exercise01.data.source.remote.api.middleware.BooleanAdapter;
import com.example.exercise01.data.source.remote.api.middleware.IntegerAdapter;
import com.example.exercise01.data.source.remote.api.middleware.InterceptorImpl;
import com.example.exercise01.data.source.remote.service.AppApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {

    private static final String BASE_URL = BuildConfig.ENDPOINT;

    private static final int CACHE_SIZE = 10 * 1024 * 1024;

    private static RetrofitProvider sInstance;

    private AppApi mAppApi;

    private Retrofit mRetrofit;

    public static synchronized RetrofitProvider getInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitProvider();
        }
        return sInstance;
    }

    private RetrofitProvider() {
        Gson gson = getConfigGson();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(getConfigOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        mRetrofit = retrofitBuilder.build();

        mAppApi = mRetrofit.create(AppApi.class);
    }

    private Gson getConfigGson() {
        BooleanAdapter booleanAdapter = new BooleanAdapter();
        IntegerAdapter integerAdapter = new IntegerAdapter();
        return new GsonBuilder().registerTypeAdapter(Boolean.class, booleanAdapter)
                .registerTypeAdapter(boolean.class, booleanAdapter)
                .registerTypeAdapter(Integer.class, integerAdapter)
                .registerTypeAdapter(int.class, integerAdapter)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    private OkHttpClient getConfigOkHttpClient() {
        // Config OkHttpClient

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        Cache cache =
                new Cache(App.getInstance().getApplicationContext().getCacheDir(), CACHE_SIZE);
        okHttpClient.cache(cache);
        okHttpClient.addInterceptor(InterceptorImpl.getInstance());

        // Ignore timeout exception in case response is big json string
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS);
        okHttpClient.readTimeout(30, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS);
        okHttpClient.retryOnConnectionFailure(true);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            okHttpClient.addInterceptor(logging);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        return okHttpClient.build();
    }

    public <T> T create(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    public AppApi getAppApi() {
        return mAppApi;
    }
}