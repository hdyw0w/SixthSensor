package kr.ac.kopo.hdyw0w.sixthsensor;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

public class SettingFragment extends Fragment {
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_setting, container, false);
//        Switch soundSwitch = (Switch) view.findViewById(R.id.soundSwitch); // initiate Switch
//
//        soundSwitch.setTextOn("On");
//        soundSwitch.setTextOff("Off");

        return view;
    }
}
