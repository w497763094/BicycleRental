package com.wudebin.bicyclerental.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public abstract class TransactionSafeActivity extends ActionBarActivity {

    private boolean mIsSafeToCommitTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsSafeToCommitTransactions = true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mIsSafeToCommitTransactions = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsSafeToCommitTransactions = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mIsSafeToCommitTransactions = false;
    }

    /**
     * Returns true if it is safe to commit {@link android.support.v4.app.FragmentTransaction}s at this time, based on
     * whether {@link android.support.v7.app.ActionBarActivity#onSaveInstanceState} has been called or not.
     * <p/>
     * Make sure that the current activity calls into
     * {@link super.onSaveInstanceState( android.os.Bundle )} (if that method is overridden),
     * so the flag is properly set.
     */
    public boolean isSafeToCommitTransactions() {
        return mIsSafeToCommitTransactions;
    }
}
