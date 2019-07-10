package com.weimi.wmmess.business.shimu.fragment;


import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.MainApplication;
import com.weimi.wmmess.R;
import com.weimi.wmmess.base.BaseFragment;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.activity.PictureDisplayActivity;
import com.weimi.wmmess.business.shimu.activity.ShiMuActivity;
import com.weimi.wmmess.business.shimu.adapter.step8.ChongTianPingHengAdapter;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbean;
import com.weimi.wmmess.business.shimu.bean.step8.ChongTianPingHengResbeanDao;
import com.weimi.wmmess.business.shimu.bean.step8.Step8Resbean;
import com.weimi.wmmess.business.shimu.bean.step8.Step8ResbeanDao;
import com.weimi.wmmess.business.shimu.presenter.Step8Presenter;
import com.weimi.wmmess.utils.NumFormatUtil;
import com.weimi.wmmess.utils.TestUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * 充填平衡测试
 */
public class Step8Fragment extends BaseFragment<Step8Presenter> implements View.OnClickListener {
    private static final int stepPosition = 7;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnNextStep, btnAddRecord;
    private Step8Presenter presenter;
    private Step8Resbean step8Resbean;
    private EditText etMinAverageValue, etMaxAverageValue, etAverageValueZhiCha, etAverageValueBiZhiCha;//(不可输入文字)
    private EditText etDuanSheOne, etDuanSheTwo, etDuanSheThree, etDuanSheFour, etDuanSheFive, etDuanSheSix;
    private TextView tvOnePic, tvTwoPic, tvThreePic, tvFourPic, tvFivePic, tvSixPic;
    private PopupWindow mPopWindow;
    private static final int REQUEST_CODE_CAMERA = 123;
    private static final int REQUEST_CODE_ALBUM = 321;
    private int picIndex = 0;
    private String imgUrl = "";
    private RecyclerView rcvList;
    private ChongTianPingHengAdapter adapter;

    public static Step8Fragment newInstance(long historyId, boolean isHistory) {
        Step8Fragment fragment = new Step8Fragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARAM1, historyId);
        args.putBoolean(ARG_PARAM2, isHistory);
        fragment.setArguments(args);
        return fragment;
    }

    private Long historyId;
    private boolean isHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            historyId = getArguments().getLong(ARG_PARAM1);
            isHistory = getArguments().getBoolean(ARG_PARAM2);
        }
    }

    @Override
    public int initLayout() {
        return R.layout.fragment_step8;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        btnNextStep = rootView.findViewById(R.id.btnNextStep);
        btnAddRecord = rootView.findViewById(R.id.btnAddRecord);
        rcvList = rootView.findViewById(R.id.rcvList);

        etMinAverageValue = rootView.findViewById(R.id.etMinAverageValue);
        etMaxAverageValue = rootView.findViewById(R.id.etMaxAverageValue);
        etAverageValueZhiCha = rootView.findViewById(R.id.etAverageValueZhiCha);
        etAverageValueBiZhiCha = rootView.findViewById(R.id.etAverageValueBiZhiCha);

        etDuanSheOne = rootView.findViewById(R.id.etDuanSheOne);
        etDuanSheTwo = rootView.findViewById(R.id.etDuanSheTwo);
        etDuanSheThree = rootView.findViewById(R.id.etDuanSheThree);
        etDuanSheFour = rootView.findViewById(R.id.etDuanSheFour);
        etDuanSheFive = rootView.findViewById(R.id.etDuanSheFive);
        etDuanSheSix = rootView.findViewById(R.id.etDuanSheSix);

        tvOnePic = rootView.findViewById(R.id.tvOnePic);
        tvTwoPic = rootView.findViewById(R.id.tvTwoPic);
        tvThreePic = rootView.findViewById(R.id.tvThreePic);
        tvFourPic = rootView.findViewById(R.id.tvFourPic);
        tvFivePic = rootView.findViewById(R.id.tvFivePic);
        tvSixPic = rootView.findViewById(R.id.tvSixPic);

        tvOnePic.setOnClickListener(this);
        tvTwoPic.setOnClickListener(this);
        tvThreePic.setOnClickListener(this);
        tvFourPic.setOnClickListener(this);
        tvFivePic.setOnClickListener(this);
        tvSixPic.setOnClickListener(this);

        btnNextStep.setOnClickListener(this);
        btnAddRecord.setOnClickListener(this);

    }

    @Override
    public void initData() {
        presenter = new Step8Presenter(this);
        step8Resbean = new Step8Resbean();
        initChongTianRcv();

        if (historyId != 0 && isHistory == true) {
            showHistoryData();
        }
    }

    private void showHistoryData() {
        try {
            Step8Resbean step8Resbean = MainApplication.daoSession.getStep8ResbeanDao().load(historyId);
            etMinAverageValue.setText(step8Resbean.getMinAverageValue());
            etMaxAverageValue.setText(step8Resbean.getMaxAverageValue());
            etAverageValueZhiCha.setText(step8Resbean.getAverageValueZhiCha());
            etAverageValueBiZhiCha.setText(step8Resbean.getAverageValueBiZhiCha());

            etDuanSheOne.setText(step8Resbean.getDuanSheOne());
            etDuanSheTwo.setText(step8Resbean.getDuanSheTwo());
            etDuanSheThree.setText(step8Resbean.getDuanSheThree());
            etDuanSheFour.setText(step8Resbean.getDuanSheFour());
            etDuanSheFive.setText(step8Resbean.getDuanSheFive());
            etDuanSheSix.setText(step8Resbean.getDuanSheSix());

            etMinAverageValue.setEnabled(false);
            etMaxAverageValue.setEnabled(false);
            etAverageValueZhiCha.setEnabled(false);
            etAverageValueBiZhiCha.setEnabled(false);

            etDuanSheOne.setEnabled(false);
            etDuanSheTwo.setEnabled(false);
            etDuanSheThree.setEnabled(false);
            etDuanSheFour.setEnabled(false);
            etDuanSheFive.setEnabled(false);
            etDuanSheSix.setEnabled(false);

            rcvList.setEnabled(false);
            // TODO: 2019/6/3 此处可在 ChongTianPingHengResbean 里面增加isEnable 字段

            tvOnePic.setText(step8Resbean.getDuanShePicOne());
            tvTwoPic.setText(step8Resbean.getDuanShePicTwo());
            tvThreePic.setText(step8Resbean.getDuanShePicThree());
            tvFourPic.setText(step8Resbean.getDuanShePicFour());
            tvFivePic.setText(step8Resbean.getDuanShePicFive());
            tvSixPic.setText(step8Resbean.getDuanShePicSix());

            List<ChongTianPingHengResbean> chongTianPingHengResbeans = MainApplication.daoSession.getChongTianPingHengResbeanDao().loadAll();
            if (chongTianPingHengResbeanList != null && chongTianPingHengResbeanList.size() > 0) {
                chongTianPingHengResbeanList.clear();
            }
            for (ChongTianPingHengResbean chongTianPingHengResbean : chongTianPingHengResbeans) {
                chongTianPingHengResbeanList.add(chongTianPingHengResbean);
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<ChongTianPingHengResbean> chongTianPingHengResbeanList = new ArrayList<>();

    private void initChongTianRcv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvList.setLayoutManager(layoutManager);

        adapter = new ChongTianPingHengAdapter(getActivity(), chongTianPingHengResbeanList);
        rcvList.setAdapter(adapter);

        adapter.setOnEditCompleteListener(new ChongTianPingHengAdapter.OnEditCompleteListener() {
            @Override
            public void onComplete(String text, int position, int etIndex) {
                setEtDate2BeanAndCalculate(text, position, etIndex);
            }
        });
    }

    private void setEtDate2BeanAndCalculate(String text, int position, int etIndex) {
        ChongTianPingHengResbean bean = chongTianPingHengResbeanList.get(position);
        if (etIndex == ChongTianPingHengAdapter.ET_CHONGTIAN_XUEHAO) {
            bean.setXueHao(text);
        } else if (etIndex == ChongTianPingHengAdapter.ET_CHONGTIAN_ONE) {
            bean.setOne(text);
        } else if (etIndex == ChongTianPingHengAdapter.ET_CHONGTIAN_TWO) {
            bean.setTwo(text);
        } else if (etIndex == ChongTianPingHengAdapter.ET_CHONGTIAN_THREE) {
            bean.setThree(text);
        } else if (etIndex == ChongTianPingHengAdapter.ET_CHONGTIAN_FOUR) {
            bean.setFour(text);
        } else if (etIndex == ChongTianPingHengAdapter.ET_CHONGTIAN_FIVE) {
            bean.setFive(text);
        } else if (etIndex == ChongTianPingHengAdapter.ET_CHONGTIAN_SIX) {
            bean.setSix(text);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chongTianPingHengResbeanList.size(); i++) {
            if (i == chongTianPingHengResbeanList.size() - 1) {
                sb.append(chongTianPingHengResbeanList.get(i).getAverageValue());
            } else {
                sb.append(chongTianPingHengResbeanList.get(i).getAverageValue() + ",");
            }
        }
        calculateValue(sb);
    }

    private void calculateValue(StringBuilder sb) {
        try {
            List<String> list = Arrays.asList(sb.toString().split(","));
            String min = Collections.min(list);
            String max = Collections.max(list);
            String cha = String.valueOf(NumFormatUtil.getDoubleNumberString(Double.parseDouble(max) - Double.parseDouble(min)));

            String bi = String.valueOf(NumFormatUtil.getDoubleNumberString((Double.parseDouble(max) / Double.parseDouble(min))));
            etMinAverageValue.setText(min);
            etMaxAverageValue.setText(max);
            etAverageValueZhiCha.setText(cha);
            etAverageValueBiZhiCha.setText(bi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNextStep:
                if (Constants.isNeedTest && !isHistory) {
                    setDate2Resbean();
                    boolean isLegal = presenter.checkDataIsLegal(step8Resbean);
                    if (!isLegal) {
                        return;
                    }
                    step8Resbean.setStep8Id(MainApplication.thisTimeId);
                    step8Resbean.setCurrentStepIsChecked(true);
                    insertDate2DB();
                }
                ((ShiMuActivity) getActivity()).chooseNextFragment(stepPosition);
                break;
            case R.id.btnAddRecord:
                addNewChongTianRecord();
                break;
            case R.id.tvOnePic:
                picIndex = 1;
                if (StringUtils.isEmpty(step8Resbean.getDuanShePicOne())) {
                    showBottomPop();
                } else {
                    displayImage(step8Resbean.getDuanShePicOne());
                }
                break;
            case R.id.tvTwoPic:
                picIndex = 2;
                if (StringUtils.isEmpty(step8Resbean.getDuanShePicTwo())) {
                    showBottomPop();
                } else {
                    displayImage(step8Resbean.getDuanShePicTwo());
                }
                break;
            case R.id.tvThreePic:
                picIndex = 3;
                if (StringUtils.isEmpty(step8Resbean.getDuanShePicThree())) {
                    showBottomPop();
                } else {
                    displayImage(step8Resbean.getDuanShePicThree());
                }
                break;
            case R.id.tvFourPic:
                picIndex = 4;
                if (StringUtils.isEmpty(step8Resbean.getDuanShePicFour())) {
                    showBottomPop();
                } else {
                    displayImage(step8Resbean.getDuanShePicFour());
                }
                break;
            case R.id.tvFivePic:
                picIndex = 5;
                if (StringUtils.isEmpty(step8Resbean.getDuanShePicFive())) {
                    showBottomPop();
                } else {
                    displayImage(step8Resbean.getDuanShePicFive());
                }
                break;
            case R.id.tvSixPic:
                picIndex = 6;
                if (StringUtils.isEmpty(step8Resbean.getDuanShePicSix())) {
                    showBottomPop();
                } else {
                    displayImage(step8Resbean.getDuanShePicSix());
                }
                break;
            default:
                break;
        }
    }

    private void insertDate2DB() {
        try {
            Step8ResbeanDao step8ResbeanDao = MainApplication.daoSession.getStep8ResbeanDao();
            ChongTianPingHengResbeanDao dao = MainApplication.daoSession.getChongTianPingHengResbeanDao();
            for (int i = 0; i < chongTianPingHengResbeanList.size(); i++) {
                dao.insert(chongTianPingHengResbeanList.get(i));
            }
            step8ResbeanDao.insert(step8Resbean);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            TestUtil.getAllDbDatas();
        }
    }

    private void displayImage(String picPath) {
        Intent intent = new Intent(getActivity(), PictureDisplayActivity.class);
        intent.putExtra(PictureDisplayActivity.PHOTO_PATH, picPath);
        startActivity(intent);
//        View view = getLayoutInflater().inflate(R.layout.dialog_image_display, null);
//        ImageView ivImage = view.findViewById(R.id.ivImage);
//        ivImage.setBackgroundResource(R.mipmap.ic_launcher);
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.picture_style);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.setView(view);
//        alertDialog.getWindow().setWindowAnimations(R.style.Comm_Dialog_Anim);
//        alertDialog.show();
//        if (!StringUtils.isEmpty(picPath)) {
//                            Glide.with(getActivity()).load(picPath).into(ivImage);
////            ivImage.setImageURI(Uri.parse(picPath));
//        }

//        Dialog dia = new Dialog(getActivity(), R.style.picture_style);
//        dia.setContentView(R.layout.dialog_image_display);
//        ImageView ivImage = dia.findViewById(R.id.ivImage);
//        ivImage.setBackgroundResource(R.mipmap.ic_launcher);
//        dia.setCanceledOnTouchOutside(true); // Sets whether this dialog is
//        Window w = dia.getWindow();
//        WindowManager.LayoutParams lp = w.getAttributes();
//        lp.x = 0;
//        lp.y = 40;
//        w.setWindowAnimations(R.style.Comm_Dialog_Anim);
//        dia.onWindowAttributesChanged(lp);
//        dia.show();
//        if (!StringUtils.isEmpty(picPath)) {
//            ivImage.setImageURI(Uri.parse(picPath));
//        }
    }

    /**
     * 从底部弹出popupwindow
     */
    private void showBottomPop() {
        if (mPopWindow != null && mPopWindow.isShowing()) {
            mPopWindow.dismiss();
            return;
        }
        View parent = getActivity().findViewById(android.R.id.content);
        final View popView = View.inflate(mActivity, R.layout.pic_option_item, null);
        showAnimation(popView);//开启动画
        mPopWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.7f;
        getActivity().getWindow().setAttributes(lp);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        setOptionItemClickAction(popView, mPopWindow);
    }

    private void setOptionItemClickAction(View popView, PopupWindow mPopWindow) {
        TextView tvCamera = popView.findViewById(R.id.tvCamera);
        TextView tvAlbum = popView.findViewById(R.id.tvAlbum);
        TextView tvCancel = popView.findViewById(R.id.tvCancel);
        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermissiones();
                mPopWindow.dismiss();
            }
        });
        tvAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkReadFilePermission();
                mPopWindow.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });
    }

    private void checkReadFilePermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 321);
        } else {
            doGetPictures();
        }
    }

    private void doGetPictures() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent1, REQUEST_CODE_ALBUM);
    }

    private void checkCameraPermissiones() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 123);
        } else {
            doTakePhoto();
        }
    }

    private File cameraSavePath;//拍照照片路径
    private Uri uri;//照片uri

    //激活相机操作
    private void doTakePhoto() {
        SimpleDateFormat timeFormatter = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new java.util.Date(time));
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + imageName + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //第二个参数为 包名.fileprovider
            uri = FileProvider.getUriForFile(getActivity(), "com.weimi.wmmess.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    imgUrl = String.valueOf(cameraSavePath);
                } else {
                    imgUrl = uri.getEncodedPath();
                }
                Log.e("拍照返回图片路径:", imgUrl);
                if (!StringUtils.isEmpty(imgUrl)) {
                    setImage2Resbean(imgUrl);
                }
                break;
            case REQUEST_CODE_ALBUM:
                if (data != null) {
                    Uri uri = data.getData();
                    imgUrl = getRealPathFromURI(uri);
                    if (!StringUtils.isEmpty(imgUrl)) {
                        setImage2Resbean(imgUrl);
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doTakePhoto();
                } else {
                    ToastUtils.showShort("您拒绝了相机权限，无法使用相机功能");
                }
            case 321:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    doGetPictures();
                } else {
                    ToastUtils.showShort("您拒绝了读取文件权限，无法获取相册");
                }
                break;
            default:
                break;
        }
    }

    private void setImage2Resbean(String imgUrl) {
        if (picIndex == 1) {
            step8Resbean.setDuanShePicOne(imgUrl);
            tvOnePic.setText(imgUrl);
        } else if (picIndex == 2) {
            step8Resbean.setDuanShePicTwo(imgUrl);
            tvTwoPic.setText(imgUrl);
        } else if (picIndex == 3) {
            step8Resbean.setDuanShePicThree(imgUrl);
            tvThreePic.setText(imgUrl);
        } else if (picIndex == 4) {
            step8Resbean.setDuanShePicFour(imgUrl);
            tvFourPic.setText(imgUrl);
        } else if (picIndex == 5) {
            step8Resbean.setDuanShePicFive(imgUrl);
            tvFivePic.setText(imgUrl);
        } else if (picIndex == 6) {
            step8Resbean.setDuanShePicSix(imgUrl);
            tvSixPic.setText(imgUrl);
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        ContentResolver contentResolver = getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private void setDate2Resbean() {
        step8Resbean.setMinAverageValue(etMinAverageValue.getText().toString());
        step8Resbean.setMaxAverageValue(etMaxAverageValue.getText().toString());
        step8Resbean.setAverageValueZhiCha(etAverageValueZhiCha.getText().toString());
        step8Resbean.setAverageValueBiZhiCha(etAverageValueBiZhiCha.getText().toString());

        step8Resbean.setDuanSheOne(etDuanSheOne.getText().toString());
        step8Resbean.setDuanSheTwo(etDuanSheTwo.getText().toString());
        step8Resbean.setDuanSheThree(etDuanSheThree.getText().toString());
        step8Resbean.setDuanSheFour(etDuanSheFour.getText().toString());
        step8Resbean.setDuanSheFive(etDuanSheFive.getText().toString());
        step8Resbean.setDuanSheSix(etDuanSheSix.getText().toString());

    }


    private void addNewChongTianRecord() {
        if (chongTianPingHengResbeanList.size() == 0) {
            chongTianPingHengResbeanList.add(new ChongTianPingHengResbean(null, MainApplication.thisTimeId, "", "", "", "", "", "", "", "", "", ""));
            adapter.notifyDataSetChanged();
        } else {
            boolean isHave = presenter.checkDataIsHaveNull(chongTianPingHengResbeanList);
            if (!isHave) {
                chongTianPingHengResbeanList.add(new ChongTianPingHengResbean(null, MainApplication.thisTimeId, "", "", "", "", "", "", "", "", "", ""));
                adapter.notifyDataSetChanged();
            }
        }

    }

    /**
     * 给popupwindow添加动画
     *
     * @param popView
     */
    private void showAnimation(View popView) {
        AnimationSet animationSet = new AnimationSet(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
        alphaAnimation.setDuration(300);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        popView.startAnimation(animationSet);
    }
}
