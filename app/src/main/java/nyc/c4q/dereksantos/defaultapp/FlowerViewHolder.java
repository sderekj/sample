package nyc.c4q.dereksantos.defaultapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.dereksantos.defaultapp.model.Flower;

public class FlowerViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView;
    private final TextView instructionsTextView;
    private final TextView categoryTextView;

    public FlowerViewHolder(View itemView) {
        super(itemView);
        nameTextView = (TextView) itemView.findViewById(R.id.name);
        instructionsTextView = (TextView) itemView.findViewById(R.id.instructions);
        categoryTextView = (TextView) itemView.findViewById(R.id.category);
    }

    public void bind(Flower flower) {
        nameTextView.setText(flower.getName());
        instructionsTextView.setText(flower.getInstructions());
        categoryTextView.setText(flower.getCategory());
    }
}
