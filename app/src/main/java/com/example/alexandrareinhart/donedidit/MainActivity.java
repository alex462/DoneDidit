package com.example.alexandrareinhart.donedidit;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.alexandrareinhart.donedidit.ViewFragments.AddNewFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewAllFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewCompletedFragment;
import com.example.alexandrareinhart.donedidit.ViewFragments.ViewIncompleteFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TaskAdapter.AdapterCallback, AddNewFragment.ActivityCallback {


    private ViewPagerAdapter viewPagerAdapter;
    private TaskDatabase taskDatabase;
    private TaskAdapter taskAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Task> tasksList;

    @BindView(R.id.tab_layout)
    protected TabLayout tabLayout;
    @BindView(R.id.view_pager)
    protected ViewPager viewPager;
    @BindView(R.id.main_recycler_view)
    protected RecyclerView mainRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        AddNewFragment addNewFragment = AddNewFragment.newInstance();
        addNewFragment.attachParent(this);
        viewPagerAdapter.AddFragment(addNewFragment, "NEW");
        viewPagerAdapter.AddFragment(new ViewAllFragment(), "ALL");
        viewPagerAdapter.AddFragment(new ViewIncompleteFragment(), "INCOMPLETE");
        viewPagerAdapter.AddFragment(new ViewCompletedFragment(), "COMPLETED");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_add_task);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_view_all);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_view_incomplete);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_view_completed);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

        linearLayoutManager = new LinearLayoutManager(this);
//        taskAdapter = new TaskAdapter(taskDatabase.taskDao().getTasks(), this);
        taskDatabase = ((TaskApplication) getApplication()).getDatabase();

        mainRecycler.setLayoutManager(linearLayoutManager);
        mainRecycler.setAdapter(taskAdapter);
    }

    @Override
    public Context getContext() {

        return getApplicationContext();
    }

    private void setUpRecyclerView() {


        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        taskAdapter = new TaskAdapter(taskDatabase.taskDao().getTasks(), this);

//        mainRecycler.setLayoutManager(linearLayoutManager);
//        mainRecycler.setAdapter(taskAdapter);

        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void addClicked() {

        setUpRecyclerView();
        AddNewFragment addNewFragment = AddNewFragment.newInstance();

        addNewFragment.attachParent(this);

//        getSupportFragmentManager().beginTransaction().replace(R.id.view_pager, addNewFragment).commit();


//        getSupportFragmentManager().beginTransaction().commit();
        mainRecycler.setAdapter(taskAdapter);
        taskAdapter = new TaskAdapter(tasksList, this);
        taskAdapter.updateList(taskDatabase.taskDao().getTasks());



        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


    }

    @Override
    public void rowClicked(Task task) {

    }

    @Override
    public void rowLongClicked(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Task Completed?")
                .setMessage("Are you sure you want to mark this task \"completed\"?")
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        taskDatabase.taskDao().deleteTask(task); //delete task from database
                        taskAdapter.updateList(taskDatabase.taskDao().getTasks()); //adapter updates view
                        Toast.makeText(MainActivity.this, "TASK COMPLETED", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
