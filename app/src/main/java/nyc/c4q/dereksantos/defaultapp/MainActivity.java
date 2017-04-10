package nyc.c4q.dereksantos.defaultapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.dereksantos.defaultapp.api.FlowersApi;
import nyc.c4q.dereksantos.defaultapp.model.Flower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Flower> flowerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(new FlowerAdapter(flowerList));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://services.hanselandpetal.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlowersApi api = retrofit.create(FlowersApi.class);

        Call<List<Flower>> call = api.getFlowers();

        call.enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                flowerList = response.body();
                FlowerAdapter adapter = new FlowerAdapter(flowerList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });
    }
}
