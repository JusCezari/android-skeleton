package juscezari.com.skeleton.data.rest.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by julio on 21/11/16.
 */

public class TokenRequest {

    @SerializedName("grant_type")
    private String grantType;

    @SerializedName("password")
    private String password;

    @SerializedName("userName")
    private String userName;

}
