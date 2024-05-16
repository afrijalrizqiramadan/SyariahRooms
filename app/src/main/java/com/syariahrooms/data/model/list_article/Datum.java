
package com.syariahrooms.data.model.list_article;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Datum implements Serializable {

    @SerializedName("judul")
    @Expose
    public String judul;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("imageArticle")
    @Expose
    public String imageArticle;
    @SerializedName("registered")
    @Expose
    public String registered;

}
