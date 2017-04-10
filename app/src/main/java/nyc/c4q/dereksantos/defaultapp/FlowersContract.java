package nyc.c4q.dereksantos.defaultapp;

import java.util.List;

import nyc.c4q.dereksantos.defaultapp.model.Flower;

public interface FlowersContract {
    interface View {
        void setFlowers(List<Flower> list);
        void showProgressBar(boolean b);
        void showError();
    }

    interface Presenter {
        void release();
        void getFlowers();
    }
}
