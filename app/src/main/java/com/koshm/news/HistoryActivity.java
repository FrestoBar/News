package com.koshm.news;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.koshm.news.models.Article;
import com.koshm.news.models.History;
import com.koshm.news.models.News;

import java.util.ArrayList;
import java.util.List;

import static com.koshm.news.MainActivity.databaseController;
import static com.koshm.news.R.id.historyView;
import static com.koshm.news.R.id.historyheadelines;
import static com.koshm.news.R.layout.activity_history;
import static com.koshm.news.R.layout.history;

public class HistoryActivity extends AppCompatActivity {
    public HistoryAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public RecyclerView recyclerView;
    private TextView topHeadline;
    private ArrayList<History> HISTORIES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_history);

        topHeadline = findViewById(historyheadelines);
        recyclerView = findViewById(historyView);
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setNestedScrollingEnabled(false);
        HISTORIES = new ArrayList<>();
        getDataFromDB();
    }

    private void getDataFromDB() {
        HISTORIES = databaseController.getHistories();
        RecyclerView recyclerView = findViewById(R.id.historyView);

        adapter = new HistoryAdapter(this, HISTORIES);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                databaseController.deleteByID(HISTORIES.get(viewHolder.getAdapterPosition()).getId());
                HISTORIES.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }


}
