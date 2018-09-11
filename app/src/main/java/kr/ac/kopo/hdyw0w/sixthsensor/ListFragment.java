package kr.ac.kopo.hdyw0w.sixthsensor;

import android.animation.Animator;
import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.Objects;

public class ListFragment extends Fragment implements NavActivity.onKeyBackPressedListener {

    FloatingActionButton fil_fab, fil_fab1, fil_fab2, fil_fab3;
    LinearLayout fabLayout1, fabLayout2, fabLayout3;
    View fabBGLayout;
    private boolean isFABOpen = false;

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NavActivity) Objects.requireNonNull(getActivity())).setOnKeyBackPressedListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        fabLayout1 = view.findViewById(R.id.fabLayout1);
        fabLayout2 = view.findViewById(R.id.fabLayout2);
        fabLayout3 = view.findViewById(R.id.fabLayout3);
        fil_fab = view.findViewById(R.id.fil_fab);
        fil_fab1 = view.findViewById(R.id.fil_fab1);
        fil_fab2 = view.findViewById(R.id.fil_fab2);
        fil_fab3 = view.findViewById(R.id.fil_fab3);
        fabBGLayout = view.findViewById(R.id.fabBGLayout);

        fil_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        fabBGLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeFABMenu();
            }
        });

        return view;
    }

    private void showFABMenu() {
        isFABOpen = true;
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        fabLayout3.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);

        fil_fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
    }

    private void closeFABMenu(){
            isFABOpen = false;
            fabBGLayout.setVisibility(View.GONE);
            fil_fab.animate().rotationBy(-180);
            fabLayout1.animate().translationY(0);
            fabLayout2.animate().translationY(0);
            fabLayout3.animate().translationY(0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (!isFABOpen) {
                        fabLayout1.setVisibility(View.GONE);
                        fabLayout2.setVisibility(View.GONE);
                        fabLayout3.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }

    @Override
    public void onBack() {
        if (isFABOpen) {
            closeFABMenu();
        } else {
            NavActivity activity = (NavActivity) getActivity();
            Objects.requireNonNull(activity).setOnKeyBackPressedListener(null);
            activity.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ListFragment", "onDestroy");
        ((NavActivity) Objects.requireNonNull(getActivity())).setOnKeyBackPressedListener(null);
    }
}

