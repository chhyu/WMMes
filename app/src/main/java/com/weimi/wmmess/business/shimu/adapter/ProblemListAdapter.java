package com.weimi.wmmess.business.shimu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.shimu.bean.problem.ProblemItemResbean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/5/27
 * Describle:
 */
public class ProblemListAdapter extends RecyclerView.Adapter<ProblemListAdapter.ViewHolder> {

    private Context context;
    private List<ProblemItemResbean> problemItemResbeans;

    public static int ET_PROBLEM_INDEX = 1;
    public static int ET_PROBLEM_DESCRIBLE = 2;
    public static int ET_PROBLEM_REASON = 3;
    public static int ET_PROBLEM_SOLUTION = 4;
    public static int ET_PROBLEM_DATE = 5;
    public static int ET_PROBLEM_PERSON = 6;

    public ProblemListAdapter(Context context, List<ProblemItemResbean> problemItemResbeans) {
        this.context = context;
        this.problemItemResbeans = problemItemResbeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_problem_list, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        ProblemItemResbean problemItemResbean = problemItemResbeans.get(i);

        holder.etProblemDescribe.setTag(i);
        holder.etReason.setTag(i);
        holder.etSolution.setTag(i);
        holder.etCompleteDate.setTag(i);
        holder.etPerson.setTag(i);

        holder.etIndex.setText(i + 1 + "");
        holder.etProblemDescribe.setText(problemItemResbean.getDescribe());
        holder.etReason.setText(problemItemResbean.getReason());
        holder.etSolution.setText(problemItemResbean.getSolution());
        holder.etCompleteDate.setText(problemItemResbean.getCompleteDate());
        holder.etPerson.setText(problemItemResbean.getPerson());
    }

    @Override
    public int getItemCount() {
        return problemItemResbeans == null ? 0 : problemItemResbeans.size();
    }

    public interface OnEditCompleteListener {
        void onComplete(String text, int position, int etIndex);
    }

    private OnEditCompleteListener listener;

    public void setOnEditCompleteListener(OnEditCompleteListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final EditText etIndex, etProblemDescribe, etReason, etSolution, etCompleteDate, etPerson;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etIndex = itemView.findViewById(R.id.etIndex);
            etProblemDescribe = itemView.findViewById(R.id.etProblemDescribe);
            etReason = itemView.findViewById(R.id.etReason);
            etSolution = itemView.findViewById(R.id.etSolution);
            etCompleteDate = itemView.findViewById(R.id.etCompleteDate);
            etPerson = itemView.findViewById(R.id.etPerson);

            etProblemDescribe.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etCompleteDate.getTag();
                    String text = etProblemDescribe.getText().toString();
                    problemItemResbeans.get(position).setDescribe(text);
                    if (listener != null) {
                        listener. onComplete(text, position, ET_PROBLEM_DESCRIBLE);
                    }
                }
            });

            etReason.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etReason.getTag();
                    String text = etReason.getText().toString();
                    problemItemResbeans.get(position).setReason(text);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_PROBLEM_REASON);
                    }
                }
            });

            etSolution.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etSolution.getTag();
                    String text = etSolution.getText().toString();
                    problemItemResbeans.get(position).setSolution(text);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_PROBLEM_SOLUTION);
                    }
                }
            });

            etCompleteDate.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etCompleteDate.getTag();
                    String text = etCompleteDate.getText().toString();
                    problemItemResbeans.get(position).setCompleteDate(text);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_PROBLEM_DATE);
                    }
                }
            });

            etPerson.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) etPerson.getTag();
                    String text = etPerson.getText().toString();
                    problemItemResbeans.get(position).setPerson(text);
                    if (listener != null) {
                        listener.onComplete(text, position, ET_PROBLEM_PERSON);
                    }
                }
            });
        }
    }
}
