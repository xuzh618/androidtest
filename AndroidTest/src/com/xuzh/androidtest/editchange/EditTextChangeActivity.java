package com.xuzh.androidtest.editchange;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xuzh.androidtest.R;

public class EditTextChangeActivity extends Activity {

    private final static boolean DEBUG = true;
    private final static String TAG = EditTextChangeActivity.class.getSimpleName();

    private EditText mEditTextMsg;
    private TextView mTvAvailableCharNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text_view);

        initViews();

    }

    private ActionBar mActionBar;

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        mActionBar = getActionBar();
        mActionBar.setTitle("输入框监听");
        mActionBar.show();
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void initViews() {
        mEditTextMsg = (EditText) findViewById(R.id.et_sendmessage);
        mTvAvailableCharNum = (TextView) findViewById(R.id.tv_available_char_num);

        mEditTextMsg.addTextChangedListener(new EditChangedListener());
    }

    class EditChangedListener implements TextWatcher {
        private CharSequence temp;//监听前的文本
        private int editStart;//光标开始位置
        private int editEnd;//光标结束位置
        private final int charMaxNum = 10;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (DEBUG)
                Log.i(TAG, "输入文本之前的状态");
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (DEBUG)
                Log.i(TAG, "输入文字中的状态，count是一次性输入字符数");
            mTvAvailableCharNum.setText("还能输入" + (charMaxNum - s.length()) + "字符");

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (DEBUG)
                Log.i(TAG, "输入文字后的状态");
            /** 得到光标开始和结束位置 ,超过最大数后记录刚超出的数字索引进行控制 */
            editStart = mEditTextMsg.getSelectionStart();
            editEnd = mEditTextMsg.getSelectionEnd();
            if (temp.length() > charMaxNum) {
                Toast.makeText(getApplicationContext(), "你输入的字数已经超过了限制！", Toast.LENGTH_LONG).show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                mEditTextMsg.setText(s);
                mEditTextMsg.setSelection(tempSelection);
            }

        }
    };

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
