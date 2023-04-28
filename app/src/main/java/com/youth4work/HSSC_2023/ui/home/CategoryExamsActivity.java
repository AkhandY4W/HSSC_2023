package com.youth4work.HSSC_2023.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.vlonjatg.progressactivity.ProgressRelativeLayout;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;
import com.youth4work.HSSC_2023.PrepApplication;

import com.youth4work.HSSC_2023.R;
import com.youth4work.HSSC_2023.network.model.Category;
import com.youth4work.HSSC_2023.ui.adapter.SubCategoryListingAdapter;
import com.youth4work.HSSC_2023.ui.base.BaseActivity;
import com.youth4work.HSSC_2023.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryExamsActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.txt_header)
    TextView txtHeader;
    @Nullable
    @BindView(R.id.exams_recycler_view)
    RecyclerView examsRecyclerView;
    @Nullable
    @BindView(R.id.progressActivity)
    ProgressRelativeLayout progressActivity;
    private Category mCategory;
    private List<Category> mCategories;
    private Tracker mTracker;
    private int pageNo=1;
    private boolean loading = true;
    SubCategoryListingAdapter adapter;
    GridLayoutManager llm;
    public static void show(@NonNull Context fromActivity, Category category) {
        Intent intent = new Intent(fromActivity, CategoryExamsActivity.class);
        intent.putExtra(DashboardActivity.CATEGORY, new Gson().toJson(category));
        fromActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_exams);

        PrepApplication application = (PrepApplication) getApplication();
        mTracker = application.getDefaultTracker();
        Constants.sendScreenImageName(mTracker, "CategoryExamsActivity");

        ButterKnife.bind(this);
        // getContent();
        //prepService = PrepApi.provideRetrofit();



        initUI();
        getExamsByCategory();
    }

    private void getContent() {
        mCategory = new Gson().fromJson(getIntent().getExtras().getString(DashboardActivity.CATEGORY, ""), Category.class);
        assert txtHeader != null;
        txtHeader.setText(mCategory.getCategory());
    }

    private void getExamsByCategory() {

        mCategories=Constants.getCategory();
        initializeAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exam_categories, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView;
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.equals(""))
                    adapter.getFilter().filter(query);
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.equals(""))
                    adapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    private void initUI() {
        if (examsRecyclerView != null) {
            llm = new GridLayoutManager(CategoryExamsActivity.this, 1,
                    RecyclerView.VERTICAL, false);
            examsRecyclerView.setLayoutManager(llm);
            examsRecyclerView.setHasFixedSize(false);

            //examsRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), true));
        }
    }
    private void initializeAdapter() {
       mCategories=Constants.getCategory();
       adapter=new SubCategoryListingAdapter(CategoryExamsActivity.this,mCategories,"CategoryExamsActivity");
       examsRecyclerView.setAdapter(adapter);
       adapter.setOnClickListener(new SubCategoryListingAdapter.OnItemClickListener() {
           @Override
           public void onForumClick(Category category) {
               mUserManager.getUser().setSelectedCatID(category.getCatid());
               category.setSubCategoryImg(!category.getSubCategoryImg().equals("") ? category.getSubCategoryImg() : category.getSubCatImg());
               mUserManager.setCategory(category);
               mUserManager.setUser(mUserManager.getUser());
               Intent i = new Intent(CategoryExamsActivity.this, DashboardActivity.class);
               i.putExtra("menuFragment", "4");
               i.putExtra("Catid", category.getCatid());
               startActivity(i);
           }

           @Override
           public void onTakeTestClick(Category category) {
               mUserManager.getUser().setSelectedCatID(category.getCatid());
               category.setSubCategoryImg(!category.getSubCategoryImg().equals("") ? category.getSubCategoryImg() : category.getSubCatImg());
               mUserManager.setCategory(category);
               mUserManager.setUser(mUserManager.getUser());
               finish();
               DashboardActivity.show(CategoryExamsActivity.this, true);

           }
       });
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            llm.setSpanCount(1);
        } else {
            //show in two columns
            llm.setSpanCount(2);
        }
    }
}
