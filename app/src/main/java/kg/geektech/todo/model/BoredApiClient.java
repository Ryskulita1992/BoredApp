package kg.geektech.todo.model;

import android.widget.TextView;

import kg.geektech.todo.data.data.CoreCallback;
import retrofit2.Call;
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

    public BoredApiClient() {
    }

    public void getAction(String type, Integer participants, TextView price, Float maxPrice, Float minPrice, Float accessibility,
                          Float minAccessibility, Float maxAccessibility, BoredActionCallback callback) {

        Call<BoredAction> call = client.getAction(
                type,
                maxPrice,
                minPrice,
                minAccessibility,
                maxAccessibility
        );
        call.enqueue(new CoreCallback<BoredAction>() {
            @Override
            public void onSuccess(BoredAction result) {
                callback.onSuccess(result);
            }
            @Override
            public void onFailure(Exception exception) {
                callback.onFailure(exception);
            }
        });
    }
public interface BoredActionCallback extends BaseCallback <BoredAction>{}
    public interface BaseCallback <A> {
        void onSuccess(A result);
        void onFailure(Exception exception);

    }

    private interface BoredApi {
        @GET("/api/activity/")
        Call<BoredAction> getAction(
                @Query("type") String type,
                @Query("minprice") Float minPrice,
                @Query("maxprice") Float maxPrice,
                @Query("minaccessibility") Float minAccessibility,
                @Query("maxaccessibility") Float maxAccessibility
        );

    }


}
