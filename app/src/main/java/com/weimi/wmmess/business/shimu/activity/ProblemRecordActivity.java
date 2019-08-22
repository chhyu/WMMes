package com.weimi.wmmess.business.shimu.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseActivity;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.adapter.ProblemListAdapter;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbean;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbeanDao;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbean;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbeanDao;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbean;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemResbeanDao;
import com.weimi.wmmess.business.shimu.presenter.ProblemRecordPresenter;
import com.weimi.wmmess.utils.TestUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.weimi.wmmess.MainApplication.daoSession;
import static com.weimi.wmmess.MainApplication.thisTimeId;

/**
 * 问题点记录
 */
public class ProblemRecordActivity extends BaseActivity<ProblemRecordPresenter> {

    private RecyclerView rcvProblemList;
    private Button btnAddRecord;
    private ProblemResbean problemResbean;
    private ProblemListAdapter problemListAdapter;
    private ProblemRecordPresenter presenter;

    @Override
    public int initLayout() {
        return R.layout.activity_problem_record;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        rcvProblemList = findViewById(R.id.rcvProblemList);
        btnAddRecord = findViewById(R.id.btnAddRecord);
        TextView tvComplete = findViewById(R.id.tvRightOp);
        tvComplete.setVisibility(View.VISIBLE);
        tvComplete.setOnClickListener(v -> onProblemRecordComplete());
        btnAddRecord.setOnClickListener(v -> addProblemRecord());

    }

    private void onProblemRecordComplete() {
        if (Constants.isNeedTest) {
            if (presenter.checkDataIsLegal(problemItemResbeans)) {
                // TODO: 2019/8/16 记录试模问题
                presenter.recodeTrialPeoblem();
            }
        }
    }

    @Override
    public void initData() {
        setTitle("问题点记录");
        presenter = new ProblemRecordPresenter(this);
        problemResbean = new ProblemResbean();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcvProblemList.setLayoutManager(layoutManager);
        problemListAdapter = new ProblemListAdapter(this, problemItemResbeans);
        rcvProblemList.setAdapter(problemListAdapter);

        problemListAdapter.setOnEditCompleteListener(new ProblemListAdapter.OnEditCompleteListener() {
            @Override
            public void onComplete(String text, int position, int etIndex) {
                setData2Bean(text, position, etIndex);
            }
        });

    }


    private void setData2Bean(String text, int position, int etIndex) {
        ProblemItemResbean problemItemResbean = problemItemResbeans.get(position);
        if (etIndex == ProblemListAdapter.ET_PROBLEM_DESCRIBLE) {
            problemItemResbean.setDescribe(text);
        } else if (etIndex == ProblemListAdapter.ET_PROBLEM_REASON) {
            problemItemResbean.setReason(text);
        } else if (etIndex == ProblemListAdapter.ET_PROBLEM_SOLUTION) {
            problemItemResbean.setSolution(text);
        } else if (etIndex == ProblemListAdapter.ET_PROBLEM_DATE) {
            problemItemResbean.setCompleteDate(text);
        } else if (etIndex == ProblemListAdapter.ET_PROBLEM_PERSON) {
            problemItemResbean.setPerson(text);
        }
    }


    private List<ProblemItemResbean> problemItemResbeans = new ArrayList<>();

    private void addProblemRecord() {
        if (problemItemResbeans.size() == 0) {
            problemItemResbeans.add(new ProblemItemResbean(null, thisTimeId, problemItemResbeans.size() + 1, "", "", "", "", ""));
            problemListAdapter.notifyDataSetChanged();
        } else {
            if (presenter.checkDataIsLegal(problemItemResbeans)) {
                problemItemResbeans.add(new ProblemItemResbean(null, thisTimeId, problemItemResbeans.size() + 1, "", "", "", "", ""));
                problemListAdapter.notifyDataSetChanged();
            }
        }
    }
}
