package com.hazelcast.concurrency;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyTest {

    private static final String API_URL = "http://cloudapi-env-1.eba-dhigsunw.eu-west-2.elasticbeanstalk.com:5000/tick";
    private static final int NUM_THREADS = 10;
    private static final int NUM_REQUESTS_PER_THREAD = 10;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++) {
            executorService.submit(() -> {
                OkHttpClient client = new OkHttpClient();
                for (int j = 0; j < NUM_REQUESTS_PER_THREAD; j++) {
                    Request request = new Request.Builder()
                            .url(API_URL)
                            .build();

                    try (Response response = client.newCall(request).execute()) {
                        if (!response.isSuccessful()) {
                            System.err.println("Request failed: " + response);
                        } else {
                            String responseBody = response.body().string();
                            System.out.println("Response: " + responseBody);
                        }
                    } catch (IOException e) {
                        System.err.println("Request failed: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        }

        executorService.shutdown();
    }
}