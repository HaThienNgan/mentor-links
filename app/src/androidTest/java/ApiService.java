import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Currency;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    String loginAct = "loginAct";

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService API_SERVICE = new Retrofit.Builder()
            .baseUrl("https://alpha.mentorlinks.net/MLSOAP/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("loginSOAP.php")
    Call<List<User>> getListUsers (@Query("t") String loginAct,
                                   @Query("mail") String mail,
                                   @Query("pwd") String pwd );

}
