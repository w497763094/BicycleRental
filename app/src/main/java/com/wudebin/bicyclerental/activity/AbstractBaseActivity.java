package com.wudebin.bicyclerental.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

public abstract class AbstractBaseActivity extends TransactionSafeActivity {
    /**
     * Convenient version of {@link FragmentManager#findFragmentById(int)}, which throws
     * an exception if the fragment doesn't exist.
     */
    @SuppressWarnings("unchecked")
    public <T extends Fragment> T getFragment(int id) {
        T result = (T)getSupportFragmentManager().findFragmentById(id);
        if (result == null) {
            throw new IllegalArgumentException("fragment 0x" + Integer.toHexString(id)
                    + " doesn't exist");
        }
        return result;
    }

    /**
     * Convenient version of {@link #findViewById(int)}, which throws
     * an exception if the view doesn't exist.
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        T result = (T)findViewById(id);
        if (result == null) {
            throw new IllegalArgumentException("view 0x" + Integer.toHexString(id)
                    + " doesn't exist");
        }
        return result;
    }

    protected static void showFragment(FragmentTransaction ft, Fragment f) {
        if ((f != null) && f.isHidden()) ft.show(f);
    }

    protected static void hideFragment(FragmentTransaction ft, Fragment f) {
        if ((f != null) && !f.isHidden()) ft.hide(f);
    }
}
