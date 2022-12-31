package com.learn.urlshorten.service;

import com.learn.urlshorten.model.Url;
import lombok.NonNull;

public interface UrlManager {
    public String getUrlByKey(@NonNull String key);
    public Url shortenUrl(@NonNull String url);
}
