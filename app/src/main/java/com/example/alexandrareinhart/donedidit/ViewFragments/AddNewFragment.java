package com.example.alexandrareinhart.donedidit.ViewFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alexandrareinhart.donedidit.R;
import com.example.alexandrareinhart.donedidit.Task;
import com.example.alexandrareinhart.donedidit.TaskAdapter;
import com.example.alexandrareinhart.donedidit.TaskApplication;
import com.example.alexandrareinhart.donedidit.TaskDatabase;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewFragment extends Fragment implements TaskAdapter.AdapterCallback {
    
    private ActivityCallback activityCallback;
    private TaskDatabase taskDatabase;
    private TaskAdapter taskAdapter;
    private List<Task> tasksList;
    
    @BindView(R.id.title_input_editText)
    protected TextInputEditText titleEditText;
    @BindView(R.id.due_date_input_editText)
    protected TextInputEditText dateEditText;
    @BindView(R.id.details_input_editText)
    protected TextInputEditText detailsEditText;

    public AddNewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        taskDatabase = ((TaskApplication) getActivity().getApplication()).getDatabase();

    }

    public static AddNewFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AddNewFragment fragment = new AddNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.submit_button)
    protected void submitButtonClicked() {

        if(titleEditText.getText().toString().isEmpty() || dateEditText.getText().toString().isEmpty() || detailsEditText.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "ALL FIELDS REQUIRED", Toast.LENGTH_LONG).show();
        } else {
//            Toast.makeText(getActivity(), "submit call made, unsuccessful", Toast.LENGTH_SHORT).show();

            Task task = new Task(titleEditText.getText().toString(), detailsEditText.getText().toString(), new Date());

            addTaskToDatabase(task);
            Toast.makeText(getActivity(), "TASK ADDED SUCCESSFULLY", Toast.LENGTH_LONG).show();
        }
    }

    private void addTaskToDatabase(final Task task) {

        taskAdapter = new TaskAdapter(tasksList, this);
        taskDatabase.taskDao().addTask(task);
        taskAdapter.updateList(taskDatabase.taskDao().getTasks());

        activityCallback.addClicked();

        Toast.makeText(getActivity(), "call to database made", Toast.LENGTH_LONG).show();
    }

    public void attachParent(ActivityCallback activityCallback) {

        this.activityCallback = activityCallback;
    }

    @Override
    public void rowClicked(Task task) {

    }

    @Override
    public void rowLongClicked(Task task) {

    }

    public interface ActivityCallback {

        void addClicked();
    }
}
