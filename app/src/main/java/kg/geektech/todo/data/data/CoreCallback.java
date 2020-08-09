package kg.geektech.todo.data.data;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract public class CoreCallback<A> implements Callback<A> {

    public abstract void onSuccess(A result);
    public abstract void onFailure(Exception exception);

    @Override
    public void onResponse(Call<A> call, Response<A> response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                onSuccess(response.body());
            } else {
                onFailure(new Exception("Body is empty"));
            }
        } else {
            onFailure(new Exception("Response code " + response.code()));
        }
    }

    @Override
    public void onFailure(Call<A> call, Throwable t) {
        onFailure(new Exception(t));
    }
}