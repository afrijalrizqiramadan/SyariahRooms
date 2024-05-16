package com.syariahrooms.data;

import android.content.Context;

import com.syariahrooms.data.local.SharedPrefHelper;
import com.syariahrooms.data.model.list_article.ListArticleResponse;
import com.syariahrooms.data.network.APIRequest;


public class Repository {

    private Context mContext;
    private APIRequest apiRequest;
    private SharedPrefHelper prefs;

    public Repository(Context mContext) {
        this.mContext = mContext;
        apiRequest = new APIRequest();
        prefs = new SharedPrefHelper(mContext);
    }


    public void getArticle(ListArticleResponse.ListArticleResponseCallback responseCallback) {
        apiRequest.getArticle(responseCallback);
    }

//
////    local
//
//    public void setMember(Member member){
//        prefs.putString(SharedPrefHelper.USER_NAME, member.getMemberName());
//        prefs.putString(SharedPrefHelper.USER_EMAIL, member.getMemberEmail());
//        prefs.putString(SharedPrefHelper.USER_ID, member.getMemberId());
//        prefs.putString(SharedPrefHelper.USER_ADDR, member.getMemberAddress());
//        prefs.putInt(SharedPrefHelper.USER_GENDER, member.getGender());
//    }
//
//    public Member getMember(){
//        Member member = new Member();
//        member.setMemberName(prefs.getString(SharedPrefHelper.USER_NAME));
//        member.setMemberEmail(prefs.getString(SharedPrefHelper.USER_EMAIL));
//        member.setMemberId(prefs.getString(SharedPrefHelper.USER_ID));
//        member.setGender(prefs.getInt(SharedPrefHelper.USER_GENDER));
//        member.setMemberAddress(prefs.getString(SharedPrefHelper.USER_ADDR));
//        return member;
//    }
//
//    public void saveToken(String token){
//        prefs.putString(SharedPrefHelper.ACCES_TOKEN, token);
//    }
//
//    public String getToken(){
//        return prefs.getString(SharedPrefHelper.ACCES_TOKEN);
//    }
//
//    public boolean isLogIn(){
//        return (prefs.getString(SharedPrefHelper.ACCES_TOKEN) != null);
//    }
//
//    public void logOut(){
//        prefs.clear();
//    }

    public void destroy() {
        apiRequest.cancelAllReq();
    }


}
