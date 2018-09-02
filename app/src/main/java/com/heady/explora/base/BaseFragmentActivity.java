package com.heady.explora.base;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.orhanobut.logger.Logger;

/**
 * Created by yashThakur on 08/03/16.
 */


public abstract class BaseFragmentActivity extends BaseActivity implements BaseFragment.FragmentTransacListener, BaseFragment.FragmentAttachListener {

    protected BaseFragment currentFragment;
    private String TAG = "AbstractFragmentActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
            return;
        }

        if (currentFragment != null) {
            if (!currentFragment.onBackPressed()) {
                super.onBackPressed();
                return;
            }
        }

        super.onBackPressed();
    }

    private void transact(BaseFragment baseFragment, boolean shouldReplace) {
        if (getContainerId() == -1) {
            Logger.e("Specify container");
            return;
        }

        if (shouldReplace) {
            Logger.e("inside replace");
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(getContainerId(), baseFragment).addToBackStack(null);
        transaction.commit();
    }

    public void addFragment(BaseFragment baseFragment) {

        transact(baseFragment, false);
    }

    public void replaceFragment(BaseFragment baseFragment) {

        transact(baseFragment, true);
    }


    @Override
    public void requestTransaction(BaseFragment baseFragment, boolean shouldReplace) {

        transact(baseFragment, shouldReplace);
    }

    @Override
    public void onAttached(BaseFragment fragment) {
        currentFragment = fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                onBackPressed();
                return true;
            }
            getSupportFragmentManager().popBackStack();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    protected abstract int getContainerId();

    protected void animateFragment() {
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
