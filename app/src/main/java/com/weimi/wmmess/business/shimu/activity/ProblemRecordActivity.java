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
    public static final String HISTORY_ID = "history_id";
    private long historyId;

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
        tvComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProblemRecordComplete();
            }
        });
        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProblemRecord();
            }
        });

    }

    private void onProblemRecordComplete() {
        if (Constants.isNeedTest) {
            if (presenter.checkDataIsLegal(problemItemResbeans)) {
                try {
                    problemResbean.setProblemId(MainApplication.thisTimeId);
                    problemResbean.setCurrentStepIsChecked(true);
                    ProblemResbeanDao problemResbeanDao = daoSession.getProblemResbeanDao();
                    ProblemItemResbeanDao problemItemResbeanDao = daoSession.getProblemItemResbeanDao();
                    problemItemResbeanDao.insertInTx(problemItemResbeans);
                    problemResbeanDao.insert(problemResbean);

                    ToastUtils.showShort("记录成功");
                    isJump = true;
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String currentTime = dateFormat.format(new Date());
                    ShiMuResbean shiMuResbean = new ShiMuResbean(null, currentTime, false, thisTimeId, thisTimeId, thisTimeId, thisTimeId, thisTimeId,
                            thisTimeId, thisTimeId, thisTimeId, thisTimeId, thisTimeId, thisTimeId, thisTimeId, thisTimeId, thisTimeId, thisTimeId);
                    ShiMuResbeanDao shiMuResbeanDao = daoSession.getShiMuResbeanDao();
                    shiMuResbean.setIsComplete(true);
                    shiMuResbeanDao.insert(shiMuResbean);
                } catch (Exception e) {
                    if (historyId != 0) {
                        finish();
                    }
                } finally {
                    TestUtil.loadAllDatas();
                    if (isJump) {
                        jump2ShiMuHistoryList();
                    }
                }
            }
        }
    }

    private boolean isJump = false;

    private void jump2ShiMuHistoryList() {
        Intent intent = new Intent(this, ShiMuHistoryActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            ShiMuResbean shiMuResbean = MainApplication.daoSession.getShiMuResbeanDao().load(MainApplication.thisTimeId);
            if (!shiMuResbean.getIsComplete()) {
                //为完成的就删掉
                Log.e("SipCallActivity", "delete all");
                TestUtil.deleteNotCompleteDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        historyId = getIntent().getLongExtra(HISTORY_ID, 0);
        if (historyId != 0) {
            showHistoryData(historyId);
        }
    }

    private void showHistoryData(long historyId) {
        try {
            List<ProblemResbean> problemResbeans = MainApplication.daoSession.getProblemResbeanDao().loadAll();

            List<ProblemItemResbean> problemItemResbeanList = MainApplication.daoSession.getProblemItemResbeanDao().loadAll();

            if (problemItemResbeans != null && problemItemResbeans.size() > 0) {
                problemItemResbeans.clear();
            }
            for (ProblemItemResbean problemItemResbean : problemItemResbeanList) {
                if (problemItemResbean.getProblemItemResbeanId().equals(historyId)) {
                    problemItemResbeans.add(problemItemResbean);
                }
            }
            problemListAdapter.notifyDataSetChanged();
            rcvProblemList.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
