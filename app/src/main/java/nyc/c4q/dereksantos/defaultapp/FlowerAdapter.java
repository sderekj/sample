package nyc.c4q.dereksantos.defaultapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.dereksantos.defaultapp.model.Flower;

class FlowerAdapter extends RecyclerView.Adapter<FlowerViewHolder> {
    private List<Flower> flowerList;

    public FlowerAdapter(List<Flower> flowerList) {
        this.flowerList = flowerList;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_viewholder, parent, false);
        return new FlowerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FlowerViewHolder holder, int position) {
        Flower flower = flowerList.get(position);
        holder.bind(flower);
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }
}
