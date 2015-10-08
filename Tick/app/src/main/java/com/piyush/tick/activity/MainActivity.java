package com.piyush.tick.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.piyush.tick.R;
import com.piyush.tick.base.BaseActivity;
import com.piyush.tick.utils.TicTackToeConstants;
import java.util.HashMap;


public class MainActivity extends BaseActivity implements View.OnClickListener, Animation.AnimationListener {
    private ImageButton mBtnCol1_1, mBtnCol1_2, mBtnCol1_3, mBtnCol2_1, mBtnCol2_2, mBtnCol2_3, mBtnCol3_1, mBtnCol3_2, mBtnCol3_3;
    private Button mBtnDone, mBtnCancel;
    private int playerStatus;
    private int prePlayerStatus;
    private ImageButton preImageButton;
    private HashMap<Integer, Boolean> gameStatus;
//    private Boolean btnDoneStatus = false;
//    private Boolean btnCancelStatus = false;
    private int selectedPosition = -1;
    private Animation animBlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.main_layout);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initOthers() {

    }

    @Override
    protected void initViews() {
        mBtnCol1_1 = (ImageButton) findViewById(R.id.col1_1);
        mBtnCol1_2 = (ImageButton) findViewById(R.id.col1_2);
        mBtnCol1_3 = (ImageButton) findViewById(R.id.col1_3);
        mBtnCol2_1 = (ImageButton) findViewById(R.id.col2_1);
        mBtnCol2_2 = (ImageButton) findViewById(R.id.col2_2);
        mBtnCol2_3 = (ImageButton) findViewById(R.id.col2_3);
        mBtnCol3_1 = (ImageButton) findViewById(R.id.col3_1);
        mBtnCol3_2 = (ImageButton) findViewById(R.id.col3_2);
        mBtnCol3_3 = (ImageButton) findViewById(R.id.col3_3);
        mBtnDone = (Button) findViewById(R.id.btnDone);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);
    }

    @Override
    protected void initMembers() {
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        animBlink.setAnimationListener(this);
    }

    @Override
    protected void initData() {
        gameStatus = new HashMap<Integer, Boolean>();
        for (int i = 0; i < 9; i++) {
            gameStatus.put(i, false);
        }
        playerStatus = TicTackToeConstants.PLAYER1;
    }

    @Override
    protected void initListeners() {
        mBtnCol1_1.setOnClickListener(this);
        mBtnCol1_2.setOnClickListener(this);
        mBtnCol1_3.setOnClickListener(this);
        mBtnCol2_1.setOnClickListener(this);
        mBtnCol2_2.setOnClickListener(this);
        mBtnCol2_3.setOnClickListener(this);
        mBtnCol3_1.setOnClickListener(this);
        mBtnCol3_2.setOnClickListener(this);
        mBtnCol3_3.setOnClickListener(this);
        mBtnDone.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.col1_1:
                selectedPosition = 0;
                changeIcon(selectedPosition, mBtnCol1_1);
                break;
            case R.id.col1_2:
                selectedPosition = 3;
                changeIcon(selectedPosition, mBtnCol1_2);
                break;
            case R.id.col1_3:
                selectedPosition = 6;
                changeIcon(selectedPosition, mBtnCol1_3);
                break;
            case R.id.col2_1:
                selectedPosition = 1;
                changeIcon(selectedPosition, mBtnCol2_1);
                break;
            case R.id.col2_2:
                selectedPosition = 4;
                changeIcon(selectedPosition, mBtnCol2_2);
                break;
            case R.id.col2_3:
                selectedPosition = 7;
                changeIcon(selectedPosition, mBtnCol2_3);
                break;
            case R.id.col3_1:
                selectedPosition = 2;
                changeIcon(selectedPosition, mBtnCol3_1);
                break;
            case R.id.col3_2:
                selectedPosition = 5;
                changeIcon(selectedPosition, mBtnCol3_2);
                break;
            case R.id.col3_3:
                selectedPosition = 8;
                changeIcon(selectedPosition, mBtnCol3_3);
                break;
            case R.id.btnDone:
                changeStatus(selectedPosition);
                break;
            case R.id.btnCancel:
                cancelSelection();
                break;
        }
    }

    private void changeStatus(int selectedPosition) {
        if (!gameStatus.get(selectedPosition).booleanValue()) {
            mBtnDone.clearAnimation();
            gameStatus.put(selectedPosition, true);
            this.selectedPosition = -1;
            changePlayer();
        } else {
            Toast.makeText(getApplicationContext(), "Already selected", Toast.LENGTH_LONG).show();
        }
    }

    private void changePlayer() {
        if (playerStatus == TicTackToeConstants.PLAYER1) {
            playerStatus = TicTackToeConstants.PLAYER2;
        } else {
            playerStatus = TicTackToeConstants.PLAYER1;
        }
    }

    private void cancelSelection() {
        if(preImageButton!=null && selectedPosition!= -1){
            preImageButton.setImageResource(android.R.color.transparent);
        }
    }

    private void changeIcon(int selectedPosition, ImageButton btn) {

        if (!gameStatus.get(selectedPosition).booleanValue()) {
          if(prePlayerStatus == playerStatus){
              preImageButton.setImageResource(android.R.color.transparent);
          }
            if (playerStatus == TicTackToeConstants.PLAYER1) {
                btn.setImageResource(R.drawable.ic_launcher);
            } else {
                btn.setImageResource(R.drawable.right_arrow);
            }
            prePlayerStatus = playerStatus;
            preImageButton = btn;
            mBtnDone.startAnimation(animBlink);
        } else {
            Toast.makeText(getApplicationContext(), "Already selected", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
