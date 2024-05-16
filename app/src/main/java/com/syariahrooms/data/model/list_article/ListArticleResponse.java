
package com.syariahrooms.data.model.list_article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ListArticleResponse implements Serializable {

    @SerializedName("status_code")
    @Expose
    public Integer statusCode;
    @SerializedName("data")
    @Expose
    public List<Datum> data = null;

    public interface ListArticleResponseCallback {
        void onSuccess(ListArticleResponse listArticleResponse);

        void onFailure(String message);
    }
}
