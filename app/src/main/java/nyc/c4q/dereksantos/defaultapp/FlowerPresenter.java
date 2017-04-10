package nyc.c4q.dereksantos.defaultapp;

import java.util.List;

import nyc.c4q.dereksantos.defaultapp.api.FlowersApi;
import nyc.c4q.dereksantos.defaultapp.model.Flower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlowerPresenter implements FlowersContract.Presenter {


    private FlowersContract.View view;

    public FlowerPresenter(FlowersContract.View view) {
        this.view = view;
    }

    @Override
    public void release() {
        view = null;
    }

    @Override
    public void getFlowers() {
        view.showProgressBar(true);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://services.hanselandpetal.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlowersApi api = retrofit.create(FlowersApi.class);

        Call<List<Flower>> call = api.getFlowers();

        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                onResponseSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                onResponseFailed();
            }
        });
    }

    private void onResponseSuccessful(List<Flower> body) {
        view.showProgressBar(false);
        view.setFlowers(body);
    }

    private void onResponseFailed() {
        view.showProgressBar(false);
        view.showError();
    }
}
