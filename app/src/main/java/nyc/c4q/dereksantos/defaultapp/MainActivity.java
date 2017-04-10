package nyc.c4q.dereksantos.defaultapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.dereksantos.defaultapp.api.FlowersApi;
import nyc.c4q.dereksantos.defaultapp.model.Flower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements FlowersContract.View {

    private RecyclerView recyclerView;
    private List<Flower> flowerList = new ArrayList<>();
    private FlowerPresenter presenter;
    private ProgressBar progressBar;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        errorTextView = (TextView) findViewById(R.id.error_textview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        presenter = new FlowerPresenter(this);
        presenter.getFlowers();

    }

    @Override
    public void setFlowers(List<Flower> list) {
        this.flowerList = list;
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView.setVisibility(View.VISIBLE);
        FlowerAdapter adapter = new FlowerAdapter(flowerList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.release();
        }
    }
}
