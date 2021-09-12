package com.aravind.rest.response.object;

import lombok.Getter;
import lombok.Setter;

/**
 * <Class Description>
 *
 * @author arvind.n
 */
@Getter
@Setter
public class UrlShortenResponse {

    private String longUrl;
    private String shortUrl;
    private String clientId;
}
