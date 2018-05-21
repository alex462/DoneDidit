package com.example.alexandrareinhart.donedidit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.alexandrareinhart.donedidit.MainActivity.ALL_TASKS_LIST;

public class EditTaskFragment extends Fragment {

    @BindView(R.id.edit_details_dialog_linearlayout)
    protected LinearLayout editDialog;
    @BindView(R.id.task_textview)
    protected TextView taskTitleLabel;
    @BindView(R.id.details_textview)
    protected TextView detailsLabel;
    @BindView(R.id.due_date_textView)
    protected TextView dueDateLabel;
    @BindView(R.id.task_title_dialog_variable)
    protected EditText editTaskTitle;
    @BindView(R.id.details_dialog_variable)
    protected EditText editTaskDetails;
    @BindView(R.id.due_date_dialog_variable)
    protected EditText editTaskDueDate;



    private TaskCallback taskCallback;
    private List<Task> allTasksList;

    private void attachView(TaskCallback taskCallback) {
        this.taskCallback = taskCallback;
    }

    public interface TaskCallback {
        void editTask();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_task, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static EditTaskFragment newInstance() {
        
        Bundle args = new Bundle();
        
        EditTaskFragment fragment = new EditTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        allTasksList = new ArrayList<>();
        allTasksList = getArguments().getParcelableArrayList(ALL_TASKS_LIST);
    }
}
