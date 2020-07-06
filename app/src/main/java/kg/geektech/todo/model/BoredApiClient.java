package kg.geektech.todo.model;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class BoredApiClient {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    BoredApi client = retrofit.create(BoredApi.class);

    public void getAction(String type, Integer participants, Double price, Double maxPrice, Double minPrice, Double accessibility,
                          Double minAccessibility, Double maxAccessibility, BoredActionCallback callback) {

        Call<BoredAction> call = client.getAction(
                type, participants,
                price,
                maxPrice,
                minPrice,
                accessibility,
                minAccessibility,
                maxAccessibility
        );

        call.enqueue(new Callback<BoredAction>() {
            @Override
            public void onResponse(Call<BoredAction> call, Response<BoredAction> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callback.onSuccess(response.body());
                        Log.d("ololo", response.body().toString());
                    } else {
                        callback.onFailure(new Exception("Body is empty"));
                    }
                } else {
                    callback.onFailure(new Exception("Response code " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<BoredAction> call, Throwable t) {
                callback.onFailure(new Exception(t));
            }
        });
    }
public interface BoredActionCallback extends BaseCallback <BoredAction>{}
    public interface BaseCallback <A> {
        void onSuccess(A result);

        void onFailure(Exception exception);

    }


    public interface BoredApi {
        @GET("/api/activity/")
        Call<BoredAction> getAction(
                @Query("type") String type,
                @Query("participants") Integer participants,
                @Query("price") Double price,
                @Query("minprice") Double minPrice,
                @Query("maxprice") Double maxPrice,
                @Query("accessibility") Double accessibility,
                @Query("minaccessibility") Double minAccessibility,
                @Query("maxaccessibility") Double maxAccessibility
        );

    }


}
