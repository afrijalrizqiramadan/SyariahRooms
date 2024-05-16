package com.syariahrooms.data.network;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.syariahrooms.data.model.list_article.ListArticleResponse;

import org.json.JSONObject;

/**
 * Created by User on 11/26/2018.
 */

public class APIRequest {

    private static String TAG = "hasil";
    private final String BASE_URL = "https://syariahrooms.com/index.php/api/";
    private String clientKey = "qJRCK04wWxcNNNNN";


    public void getArticle(final ListArticleResponse.ListArticleResponseCallback callback) {
        String requestUrl = BASE_URL + "get_artc";
        final Gson gson = new GsonBuilder().create();
        System.out.println(requestUrl);

        AndroidNetworking.get(requestUrl.trim())
                .setPriority(Priority.MEDIUM)
                .addQueryParameter("client_key", clientKey)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ListArticleResponse dataResponse = gson.fromJson(response.toString(), ListArticleResponse.class);
                        if (dataResponse != null) {
                            callback.onSuccess(dataResponse);
                        } else {
                            callback.onFailure("Cannot get Object");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onFailure("" + anError.getErrorDetail());
                    }
                });

    }
//
//    public static void submit(String message, final DataResponse2.DataResponseCallback callback) {
//        String requestUrl = BASE_URL_SARAN + message.trim();
//        final Gson gson =new GsonBuilder().create();
//        System.out.println(message);
//        System.out.println(requestUrl);
//
//        AndroidNetworking.get(requestUrl.trim())
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        DataResponse2 dataResponse = gson.fromJson(response.toString(), DataResponse2.class);
//                        if (dataResponse != null) {
//                            callback.onSuccess(dataResponse);
//                        } else {
//                            callback.onFailure("Cannot get Object");
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//                        callback.onFailure(""+anError.getErrorDetail());
//                    }
//                });
//
//    }

    public void cancelAllReq() {
        AndroidNetworking.cancelAll();
    }


    public enum ErrorCode {
        NOT_FOUND,
        NO_INTERNET,
        SERVER_ERROR
    }


}

