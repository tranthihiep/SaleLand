package com.example.hiep.bds.utilts;
import com.example.hiep.bds.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.transition.Slide;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hiep.bds.view.rent.RentFragment;
import com.example.hiep.bds.view.sale.SaleFragment;

public class MainContainFragment extends Fragment
        implements GetChildFragment {
    private final int mLayoutMainContainer = R.id.main_container;
    private String nameFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable
                    Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contain_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (getFragment()) {
            case "Mua, Bán":
                //DiscoverFragment fragment = new DiscoverFragment();
                //fragment.setListener(this);
                addChildFragment(new SaleFragment(), false, false);
                break;
            case "Cho Thuê":
                addChildFragment(new RentFragment(), false, false);
                break;

        }

    }

    @Override
    public void addChildFragment(Fragment fragment, boolean addToBackStack, boolean isAnimation) {
        if (fragment instanceof GetFragment) {
            ((GetFragment) fragment).setFragmentMain(this);
        }
        if (isAnimation) {
            fragment.setEnterTransition(new Slide(Gravity.RIGHT));
            fragment.setExitTransition(new Slide(Gravity.LEFT));
        }
        try {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.getTag());
            }
            fragmentTransaction.add(mLayoutMainContainer, fragment);
            fragmentTransaction.commit();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public boolean isFragment() {
        int size = getChildFragmentManager().getBackStackEntryCount();
        if (size != 0) {
            getChildFragmentManager().popBackStack();
            return false;
        }
        return true;
    }

    public void setRoot(String name) {
        this.nameFragment = name;
    }

    public String getFragment() {
        return this.nameFragment;
    }

    public boolean callBack() {
        if (getContext() == null) {
            return true;
        }
        int size = getChildFragmentManager().getBackStackEntryCount();
        boolean backPop = true;

        if (backPop) {
            getChildFragmentManager().popBackStack();
            return size == 0;
        }

        return false;
    }

}
