package juscezari.com.skeleton.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by julio on 21/11/16.
 */

public class Token {

    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("expires_in")
    private String expiresIn;

    @SerializedName("userName")
    private String userName;

    @SerializedName(".issued")
    private String issued;

    @SerializedName(".expires")
    private String expires;

}
