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

public class ViewAllFragment extends Fragment {

    @BindView(R.id.view_all_recycler)
    protected RecyclerView viewAllRecycler;
    private List<Task> allTasksList;
    private TaskAdapter taskAdapter;
    private TaskDatabase taskDatabase;


    public ViewAllFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all, container, false);
        ButterKnife.bind(this, view);
//        TaskAdapter taskAdapter = new TaskAdapter(getContext(), taskList);
//        taskAdapter = new TaskAdapter(taskDatabase.taskDao().getTasks(), this);
        viewAllRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewAllRecycler.setAdapter(taskAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        taskDatabase = ((TaskApplication) getActivity().getApplication()).getDatabase();
        allTasksList = new ArrayList<>();
//        allTasksList = getArguments().getParcelableArrayList(ALL_TASKS_LIST);
        //TODO - find where allTasksList is declared as List instead of ArrayList and fix, or why "List" is required in this Parcelable instead of ArrayList

    }

    public static ViewAllFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ViewAllFragment fragment = new ViewAllFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
