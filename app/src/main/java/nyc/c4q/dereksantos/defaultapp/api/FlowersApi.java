package nyc.c4q.dereksantos.defaultapp.api;

import java.util.List;

import nyc.c4q.dereksantos.defaultapp.model.Flower;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FlowersApi {

    @GET("feeds/flowers.json")
    Call<List<Flower>> getFlowers();
}
