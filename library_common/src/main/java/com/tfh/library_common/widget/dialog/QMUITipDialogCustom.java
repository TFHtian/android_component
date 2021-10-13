package com.tfh.library_common.widget.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.qmuiteam.qmui.skin.QMUISkinHelper;
import com.qmuiteam.qmui.skin.QMUISkinManager;
import com.qmuiteam.qmui.skin.QMUISkinValueBuilder;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUILoadingView;
import com.qmuiteam.qmui.widget.dialog.QMUIBaseDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialogView;
import com.qmuiteam.qmui.widget.textview.QMUISpanTouchFixTextView;
import com.tfh.library_common.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***
 * 重新show方法
 * 处理showloading的时候沉浸式会失效
 */
public class QMUITipDialogCustom extends QMUIBaseDialog {


    public QMUITipDialogCustom(Context context) {
        this(context, R.style.QMUI_TipDialog);
    }

    public QMUITipDialogCustom(Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 生成默认的 {@link QMUITipDialog}
     * <p>
     * 提供了一个图标和一行文字的样式, 其中图标有几种类型可选。见 {@link QMUITipDialog.Builder.IconType}
     * </p>
     *
     * @see QMUITipDialog.CustomBuilder
     */
    public static class Builder {
        /**
         * 不显示任何icon
         */
        public static final int ICON_TYPE_NOTHING = 0;
        /**
         * 显示 Loading 图标
         */
        public static final int ICON_TYPE_LOADING = 1;
        /**
         * 显示成功图标
         */
        public static final int ICON_TYPE_SUCCESS = 2;
        /**
         * 显示失败图标
         */
        public static final int ICON_TYPE_FAIL = 3;
        /**
         * 显示信息图标
         */
        public static final int ICON_TYPE_INFO = 4;

        @IntDef({ICON_TYPE_NOTHING, ICON_TYPE_LOADING, ICON_TYPE_SUCCESS, ICON_TYPE_FAIL, ICON_TYPE_INFO})
        @Retention(RetentionPolicy.SOURCE)
        public @interface IconType {
        }

        private @QMUITipDialog.Builder.IconType
        int mCurrentIconType = ICON_TYPE_NOTHING;

        private Context mContext;

        private CharSequence mTipWord;

        private QMUISkinManager mSkinManager;

        public Builder(Context context) {
            mContext = context;
        }

        /**
         * 设置 icon 显示的内容
         *
         * @see QMUITipDialog.Builder.IconType
         */
        public Builder setIconType(@IconType int iconType) {
            mCurrentIconType = iconType;
            return this;
        }

        /**
         * 设置显示的文案
         */
        public Builder setTipWord(CharSequence tipWord) {
            mTipWord = tipWord;
            return this;
        }

        public Builder setSkinManager(@Nullable QMUISkinManager skinManager) {
            mSkinManager = skinManager;
            return this;
        }

        public QMUITipDialogCustom create() {
            return create(true);
        }

        public QMUITipDialogCustom create(boolean cancelable) {
            return create(cancelable, R.style.QMUI_TipDialog);
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 {Dialog#show()} 方法
         *
         * @param cancelable 按系统返回键是否可以取消
         * @return 创建的 Dialog
         */
        public QMUITipDialogCustom create(boolean cancelable, int style) {
            QMUITipDialogCustom dialog = new QMUITipDialogCustom(mContext, style);
            dialog.setCancelable(cancelable);
            dialog.setSkinManager(mSkinManager);
            Context dialogContext = dialog.getContext();
            QMUITipDialogView dialogView = new QMUITipDialogView(dialogContext);

            QMUISkinValueBuilder builder = QMUISkinValueBuilder.acquire();
            if (mCurrentIconType == ICON_TYPE_LOADING) {
                QMUILoadingView loadingView = new QMUILoadingView(dialogContext);
                loadingView.setColor(QMUIResHelper.getAttrColor(
                        dialogContext, R.attr.qmui_skin_support_tip_dialog_loading_color));

                loadingView.setSize(QMUIResHelper.getAttrDimen(
                        dialogContext, R.attr.qmui_tip_dialog_loading_size));
                builder.tintColor(R.attr.qmui_skin_support_tip_dialog_loading_color);
                QMUISkinHelper.setSkinValue(loadingView, builder);
                dialogView.addView(loadingView, onCreateIconOrLoadingLayoutParams(dialogContext));

            } else if (mCurrentIconType == ICON_TYPE_SUCCESS ||
                    mCurrentIconType == ICON_TYPE_FAIL ||
                    mCurrentIconType == ICON_TYPE_INFO) {
                ImageView imageView = new AppCompatImageView(dialogContext);

                builder.clear();
                Drawable drawable;
                if (mCurrentIconType == ICON_TYPE_SUCCESS) {
                    drawable = QMUIResHelper.getAttrDrawable(
                            dialogContext, R.attr.qmui_skin_support_tip_dialog_icon_success_src);
                    builder.src(R.attr.qmui_skin_support_tip_dialog_icon_success_src);
                } else if (mCurrentIconType == ICON_TYPE_FAIL) {
                    drawable = QMUIResHelper.getAttrDrawable(
                            dialogContext, R.attr.qmui_skin_support_tip_dialog_icon_error_src);
                    builder.src(R.attr.qmui_skin_support_tip_dialog_icon_error_src);
                } else {
                    drawable = QMUIResHelper.getAttrDrawable(
                            dialogContext, R.attr.qmui_skin_support_tip_dialog_icon_info_src);
                    builder.src(R.attr.qmui_skin_support_tip_dialog_icon_info_src);
                }
                imageView.setImageDrawable(drawable);
                QMUISkinHelper.setSkinValue(imageView, builder);
                dialogView.addView(imageView, onCreateIconOrLoadingLayoutParams(dialogContext));

            }

            if (mTipWord != null && mTipWord.length() > 0) {
                TextView tipView = new QMUISpanTouchFixTextView(dialogContext);
                tipView.setEllipsize(TextUtils.TruncateAt.END);
                tipView.setGravity(Gravity.CENTER);
                tipView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        QMUIResHelper.getAttrDimen(dialogContext, R.attr.qmui_tip_dialog_text_size));
                tipView.setTextColor(QMUIResHelper.getAttrColor(
                        dialogContext, R.attr.qmui_skin_support_tip_dialog_text_color));
                tipView.setText(mTipWord);

                builder.clear();
                builder.textColor(R.attr.qmui_skin_support_tip_dialog_text_color);
                QMUISkinHelper.setSkinValue(tipView, builder);
                dialogView.addView(tipView, onCreateTextLayoutParams(dialogContext, mCurrentIconType));
            }
            builder.release();
            dialog.setContentView(dialogView);
            return dialog;
        }

        protected LinearLayout.LayoutParams onCreateIconOrLoadingLayoutParams(Context context) {
            return new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }


        protected LinearLayout.LayoutParams onCreateTextLayoutParams(Context context, @QMUITipDialog.Builder.IconType int iconType) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (iconType != ICON_TYPE_NOTHING) {
                lp.topMargin = QMUIResHelper.getAttrDimen(context, R.attr.qmui_tip_dialog_text_margin_top);
            }
            return lp;
        }

    }

    @Override
    public void show() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        super.show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            this.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        }
        //在show之后取消禁止获取焦点属性，否则会导致dialog无法处理点击
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    /**
     * CustomBuilder with xml layout
     */
    public static class CustomBuilder {
        private Context mContext;
        private int mContentLayoutId;
        private QMUISkinManager mSkinManager;

        public CustomBuilder(Context context) {
            mContext = context;
        }

        public CustomBuilder setSkinManager(@Nullable QMUISkinManager skinManager) {
            mSkinManager = skinManager;
            return this;
        }

        public CustomBuilder setContent(@LayoutRes int layoutId) {
            mContentLayoutId = layoutId;
            return this;
        }

        public QMUITipDialogCustom create() {
            QMUITipDialogCustom dialog = new QMUITipDialogCustom(mContext);
            dialog.setSkinManager(mSkinManager);
            Context dialogContext = dialog.getContext();
            QMUITipDialogView tipDialogView = new QMUITipDialogView(dialogContext);
            LayoutInflater.from(dialogContext).inflate(mContentLayoutId, tipDialogView, true);
            dialog.setContentView(tipDialogView);
            return dialog;
        }
    }
}
