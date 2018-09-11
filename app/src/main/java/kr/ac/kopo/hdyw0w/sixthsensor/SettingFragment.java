package kr.ac.kopo.hdyw0w.sixthsensor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class SettingFragment extends Fragment implements View.OnClickListener {
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
        view.findViewById(R.id.fis_logoutTv).setOnClickListener(this);
        view.findViewById(R.id.fis_passChTv).setOnClickListener(this);
        view.findViewById(R.id.fis_secessionTv).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.fis_logoutTv :
                Intent intent_act = new Intent(getContext(), LoginActivity.class);
                startActivity(intent_act);
                break;
            case R.id.fis_passChTv :
                Intent intent_act1 = new Intent(getContext(), ChangePassActivity.class);
                startActivity(intent_act1);
                break;
            case R.id.fis_secessionTv :
                EditText input = new EditText(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                new AlertDialog.Builder(getContext()).setIcon(R.mipmap.ic_launcher).setTitle("회원탈퇴")
                        .setView(input)
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "탈퇴 되었습니다, 안녕 :)", Toast.LENGTH_SHORT).show();
                        Intent intent_act2 = new Intent(getContext(), MainActivity.class);
                        startActivity(intent_act2);
                    }
                }).setNegativeButton("아니오", null).setMessage("\n회원탈퇴시, 저장된 모든 데이터가 삭제되어 복구가 불가능합니다.\n정말 탈퇴하시겠습니까 ?\n\n비밀번호 입력 ")
                        .show();
                break;
        }
    }
}
