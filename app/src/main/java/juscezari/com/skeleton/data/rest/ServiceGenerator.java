package juscezari.com.skeleton.data.rest;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import juscezari.com.skeleton.data.rest.scheme.TokenScheme;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe para gerar services para uso do Retrofit
 * Essa classe foi baseada no artigo abaixo:
 * https://futurestud.io/blog/retrofit-getting-started-and-android-client
 */
public class ServiceGenerator {

    // Urls base para as chamadas da API
    public static final String BASE_URL_API = "http://api.playya.com.br";
    // URL que irá receber um token para ser usado nos headers
    public static String TOKEN;
    // Quantidade de segundos para timeout
    public static int timeout = 30;

    // Interceptor do Stetho
    public static StethoInterceptor stethoInterceptor = new StethoInterceptor();

    // Função que cria um novo okHttpCliente
    private static OkHttpClient.Builder httpClientFactory(){
        // Retorna um novo objeto
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(stethoInterceptor)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS);
    }

    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    // Building do Retrofit usando a BASE_URL_API para chamadas comuns
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL_API)
                    // Adicionado o conversor GSON para trabalhar com JSON
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson));

    /**
     * Método que cria os services apontando para a BASE_URL_API que é o padrão utilizando o token
     * quando a variável static não está null.
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {

        // Criação do httpClient usando o okHttpClient e adição do NetworkInterceptor do Stetho junto
        // com o valor de 30 segundos para TimeOut de requisição
        final OkHttpClient.Builder httpClient = httpClientFactory();

        // Adiciona interceptor que irá testar se deu erro por conta de falta de token
        httpClient.addInterceptor(
                new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        // Pega toda a chain de request atual
                        Request request = chain.request();

                        // Build de um novo request
                        Request.Builder builder = request.newBuilder();

                        // Seta o token no request
                        setAuthHeader(builder, ServiceGenerator.TOKEN);

                        // Sobrescreve o request antigo
                        request = builder.build();

                        // Try para tratar possível StackOverflow - Ideal que nunca aconteça, mas né... #goHorse
                        try {

                            // Procede com o request, aqui o request original será executado
                            Response response = chain.proceed(request);

                            // Se a resposta der como não autorizado
                            if (response.code() == 401) {
                                // Faz um block em todas as requisições 401 para prevenir múltiplos requests de token
                                synchronized (httpClient) {

                                    // Chama a função que faz refresh do token
                                    refreshToken();

                                    // Se o token não for null após dar o refresh
                                    if (ServiceGenerator.TOKEN != null) {
                                        // Seta o novo token no request
                                        setAuthHeader(builder, ServiceGenerator.TOKEN);
                                        // Builda o request
                                        request = builder.build();
                                        // Repete o request agora com o token
                                        return chain.proceed(request);
                                    }
                                }
                            }

                            // Retorna a response do request
                            return response;

                        } catch (StackOverflowError e) {
                            // StackTrace que deu o stackOverflow
                            e.printStackTrace();

                            // Se der StackOverflow retorna null, causará um erro de nullPointerException porém
                            // irá reiniciar o ciclo dos requests não travando asssim todas as requisições futuras
                            return null;
                        }

                    }

                    /**
                     * Função que seta um header de Authorization em um request com base em um token
                     *
                     * @param builder
                     * @param token
                     */

                    private void setAuthHeader(Request.Builder builder, String token) {
                        // Se o token passado não for null
                        if (token != null)
                            // Adiciona no header desse request
                            builder.addHeader("Authorization", String.format("Bearer %s", token));
                            builder.addHeader("Content-Encoding", "gzip");
                    }

                    /**
                     * Função que requisita um token do servidor e se obter salva no banco local do TCA
                     */
                    private void refreshToken() {

                        // Lógica para buscar e salvar o Token

                    }

                }

        );

        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    /**
     * Método fixo que cria o service para requisição de token da API.
     *
     * @return
     */
    public static TokenScheme createServiceToken() {

        // Criação do httpClient específico para a requisição de token com timeout de 30 segundos
        OkHttpClient.Builder httpClientToken = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS);

        // Building do Retrofit usando a BASE_URL para chamar o token
        Retrofit.Builder builderToken =
                new Retrofit.Builder()
                        .baseUrl(BASE_URL_API)
                        // Adicionado o conversor GSON para trabalhar com JSON no retorno
                        .addConverterFactory(GsonConverterFactory.create());

        // Cria uma instância do Retrofit com esse builder
        Retrofit retrofitToken = builderToken.client(httpClientToken.build()).build();
        // Retorna a class de serviço do Token
        return retrofitToken.create(TokenScheme.class);
    }
}
