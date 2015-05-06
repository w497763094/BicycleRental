package com.wudebin.bicyclerental.activity;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.util.LinkedList;
import java.util.List;

public class HttpClientProxy {

    private static AsyncHttpClient sAsyncHttpClient = new AsyncHttpClient();

    private List<RequestHandle> mRequestHandleList;

    public HttpClientProxy() {
        mRequestHandleList = new LinkedList<RequestHandle>();
    }

    public void post(Context context, String url, Header[] headers, HttpEntity entity, String contentType,
                     ResponseHandlerInterface responseHandler) {
        addRequestList(sAsyncHttpClient.post(context, url, headers, entity, contentType, responseHandler));
    }

    public void post(Context context, String url, Header[] headers, RequestParams params, String contentType,
                              ResponseHandlerInterface responseHandler) {
        addRequestList(sAsyncHttpClient.post(context, url, headers, params, contentType, responseHandler));
    }

    public void post(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        addRequestList(sAsyncHttpClient.post(context, url, entity, contentType, responseHandler));
    }

    public void post(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        addRequestList(sAsyncHttpClient.post(context, url, params, responseHandler));
    }

    public void post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        addRequestList(sAsyncHttpClient.post(url, params, responseHandler));
    }

    public void post(String url, ResponseHandlerInterface responseHandler) {
        addRequestList(sAsyncHttpClient.post(url, responseHandler));
    }

    private void addRequestList(RequestHandle requestHandle) {
        if (requestHandle != null) {
            mRequestHandleList.add(requestHandle);
        }
    }

    public static void setCookieStore(PersistentCookieStore cookieStore) {
        sAsyncHttpClient.setCookieStore(cookieStore);
    }

    public void cancelAll() {
        for (RequestHandle requestHandle : mRequestHandleList) {
            requestHandle.cancel(true);
        }
    }

}
