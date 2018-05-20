package com.example.alexandrareinhart.donedidit.ViewFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alexandrareinhart.donedidit.R;
import com.example.alexandrareinhart.donedidit.TaskAdapter;
import com.example.alexandrareinhart.donedidit.Task;
import com.example.alexandrareinhart.donedidit.TaskApplication;
import com.example.alexandrareinhart.donedidit.TaskDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.alexandrareinhart.donedidit.MainActivity.ALL_TASKS_LIST;

public class ViewAllFragment extends Fragment implements TaskAdapter.AdapterCallback {

    @BindView(R.id.view_all_recycler)
    protected RecyclerView viewAllRecycler;
    private List<Task> allTasksList;
    private TaskAdapter taskAdapter;
    private TaskDatabase taskDatabase;
    private AllTasksCallback allTasksCallback;

    public void attachView(AllTasksCallback allTasksCallback) {
        this.allTasksCallback = allTasksCallback;
    }

    @Override
    public void rowClicked(Task task) {

    }

    @Override
    public void rowLongClicked(Task task) {

    }

    public interface AllTasksCallback{
        void addClicked();
    }


    public ViewAllFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        taskDatabase = ((TaskApplication) getActivity().getApplication()).getDatabase();
        allTasksList = new ArrayList<>();
        assert getArguments() != null;
        allTasksList = getArguments().getParcelableArrayList(ALL_TASKS_LIST);
        populateView();

    }

    private void populateView() {

        TaskAdapter taskAdapter = new TaskAdapter(allTasksList, this);

        viewAllRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewAllRecycler.setAdapter(taskAdapter);
    }

    public static ViewAllFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ViewAllFragment fragment = new ViewAllFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
