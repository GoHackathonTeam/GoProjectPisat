package com.dekinci.lksbstu.communication.VKApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VKApi {

    @GET("wall.get?&owner_id=-153165581&v=5.74&access_token=3c7281213c7281213c728121453c10140e33c723c728121669205520abaad85c1fcf3fa")
    Call<List<VKposts>> getPolyMem();
}
