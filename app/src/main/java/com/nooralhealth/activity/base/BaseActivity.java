package com.nooralhealth.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import com.nooralhealth.R;
import com.nooralhealth.model.room.viewmodel.ClassTypeViewModel;
import com.nooralhealth.model.room.viewmodel.ImgViewModel;
import com.nooralhealth.utill.CustomTypefaceSpan;


public abstract class BaseActivity extends AppCompatActivity {

    private String TAG = BaseActivity.class.getSimpleName();
    private Context context = BaseActivity.this;

    public ClassTypeViewModel classTypeViewModel;
    public ImgViewModel imgViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseViewModels();
    }

    private void initialiseViewModels() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);

        classTypeViewModel = viewModelProvider.get(ClassTypeViewModel.class);
        imgViewModel = viewModelProvider.get(ImgViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void makeMessageBold(TextView otpMessage, @StringRes int messageId, @StringRes int messageBoldId) {
        String message = getString(messageId);
        String highlight = getString(messageBoldId);

        makeMessageBold(otpMessage, message, highlight);
    }

    protected void makeMessageBold(TextView otpMessage, String message, String highlight) {
        int start = message.indexOf(highlight);
        int end = start + highlight.length();

        SpannableStringBuilder SS = new SpannableStringBuilder(message);
        SS.setSpan(new CustomTypefaceSpan(ResourcesCompat.getFont(context, R.font.assistantbold)), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        otpMessage.setText(SS);
    }

}
