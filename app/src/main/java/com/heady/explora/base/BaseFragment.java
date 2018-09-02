package com.heady.explora.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 * Created by yashThakur on 08/03/16.
 */

public abstract class BaseFragment extends Fragment {

    protected Context context;
    protected BaseFragment currentFragment;
    protected FragmentTransacListener fragmentTransacListener;
    protected FragmentAttachListener fragmentAttachListener;

    public static final String RUPEES = "\u20B9 ";
    public static final int HIDE = -1;
    public static final int INVISIBLE = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = onCreateKnifeView(inflater, container, savedInstanceState);
        context = container.getContext();
        ButterKnife.bind(this, view);
//        view.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract View onCreateKnifeView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public boolean onBackPressed() {
        return false;
    }

    public interface FragmentTransacListener {
        void requestTransaction(BaseFragment baseFragment, boolean shouldReplace);
    }

    public interface FragmentAttachListener {
        void onAttached(BaseFragment fragment);
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        if (activity instanceof FragmentTransacListener) {
            fragmentTransacListener = (FragmentTransacListener) activity;
        }

        if (activity instanceof FragmentAttachListener) {
            fragmentAttachListener = (FragmentAttachListener) activity;
            fragmentAttachListener.onAttached(this);

        }
    }

    protected void replaceFragment(BaseFragment baseFragment) {
        if (fragmentTransacListener != null) {
            fragmentTransacListener.requestTransaction(baseFragment, true);
        }
    }


    public void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(View view) {
        if (view != null && getActivity() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            view.requestFocus();
            inputMethodManager.showSoftInput(view, 0);
        }

    }

    public int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
