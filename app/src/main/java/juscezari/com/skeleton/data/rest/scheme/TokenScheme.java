package juscezari.com.skeleton.data.rest.scheme;

import juscezari.com.skeleton.data.model.Token;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TokenScheme {

    /**
     * Método na API para requisitar um novo Token
     * @return
     */
    @FormUrlEncoded
    @POST("token")
    Call<Token> requestToken(
            @Field("grant_type") String grantType,
            @Field("userName") String userName,
            @Field("password") String password);

}