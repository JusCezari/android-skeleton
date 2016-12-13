package juscezari.com.skeleton.data.rest.helper;

import java.io.IOException;

import juscezari.com.skeleton.data.model.Token;
import juscezari.com.skeleton.data.rest.ServiceGenerator;
import juscezari.com.skeleton.data.rest.request.TokenRequest;
import juscezari.com.skeleton.data.rest.scheme.TokenScheme;
import retrofit2.Call;
import retrofit2.Callback;

public class TokenHelper {

    /**
     * Função para retornar um TokenTemplate com as informações de token do servidor para acessar
     * a nova API. Esse método é síncrono.
     * @param androidId
     * @return
     */
    public static Token getTokenSync(String androidId) throws IOException {
        // Cria o cliente do serviço apontando apenas para a BASE_URL
        TokenScheme service =
                ServiceGenerator.createServiceToken();

        // Cria um objeto para request de novo token
        TokenRequest tokenRequest = new TokenRequest();

        // Cria a chamada passando o objeto como parâmetro
        Call<Token> call = service.requestToken("password",
                "username",
                "password");
        // Executa a chamada e guarda o retorno dela
        Token response = call.execute().body();

        // Retorna essa resposta
        return response;
    }

    /**
     * Função para retornar um TokenTemplate com as informações de token do servidor para acessar
     * a nova API. Esse método é síncrono.
     * @param usuario
     * @param senha
     * @return
     */
    public static Token getTokenSync(String usuario, String senha) throws IOException {
        // Cria o cliente do serviço apontando apenas para a BASE_URL
        TokenScheme service =
                ServiceGenerator.createServiceToken();

        // Cria um objeto para request de novo token
        TokenRequest tokenRequest = new TokenRequest();

        // Cria a chamada passando o objeto como parâmetro
        Call<Token> call = service.requestToken("password",
                "username",
                "password");
        // Executa a chamada e guarda o retorno dela
        Token response = call.execute().body();

        // Retorna essa resposta
        return response;
    }

    /**
     * Função para retornar um TokenTemplate com as informações de token do servidor para acessar
     * a nova API. Esse método é assíncrono.
     * @param androidId
     * @param callback
     * @return
     */
    public static void getTokenAsync(String androidId, Callback callback) {
        // Cria o cliente do serviço apontando apenas para a BASE_URL
        TokenScheme service =
                ServiceGenerator.createServiceToken();

        // Cria um objeto para request de novo token
        TokenRequest tokenRequest = new TokenRequest();

        // Cria a chamada passando o objeto como parâmetro
        Call<Token> call = service.requestToken("password",
                "username",
                "password");

        // Chama a callback passada por parâmetro
        call.enqueue(callback);

    }

}
