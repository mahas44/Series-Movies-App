package com.enes.moviesapp.service;

import android.content.Context;

import com.enes.moviesapp.model.YouTubeVideoItem;
import com.enes.moviesapp.ui.OnTaskCompletedListener;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.List;

public class YoutubeService {

    private YouTube youTube;

    private YouTube.Search.List query;

    private OnTaskCompletedListener onTaskCompletedListener;

    private static String YouTube_API_KEY = "api key";
    private static String SHA_1_FINGER_PRINT = "sha-1 finger print";

    public YoutubeService(final Context context){

        youTube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
                request.getHeaders().set("X-Android-Package", context.getPackageName());
                request.getHeaders().set("X-Android-Cert", SHA_1_FINGER_PRINT);
            }
        }).setApplicationName("Series-Movie App").build();

        try{
            query = youTube.search().list("id,snippet");
            query.setKey(YouTube_API_KEY);
            query.setType("video");
            query.setFields("items(id/videoId,snippet/title,snippet/description,snippet/thumbnails/default/url)");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getApi() {
        return YouTube_API_KEY;
    }


    public YouTubeVideoItem search(String keywords, OnTaskCompletedListener listener) {
        query.setQ(keywords);
        query.setMaxResults((long) 1);

        onTaskCompletedListener = listener;

        try{
            SearchListResponse response = query.execute();

            List<SearchResult> results = response.getItems();

            YouTubeVideoItem youTubeVideoItem = new YouTubeVideoItem();
            if (results != null){
                ResourceId resourceId = results.get(0).getId();

                youTubeVideoItem.setId(resourceId.getVideoId());
                youTubeVideoItem.setTitle(results.get(0).getSnippet().getTitle());
                youTubeVideoItem.setDescription(results.get(0).getSnippet().getDescription());
                youTubeVideoItem.setThumbnailURL(results.get(0).getSnippet().getThumbnails().getDefault().getUrl());

            }
            listener.onTaskCompleted();
            return youTubeVideoItem;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
