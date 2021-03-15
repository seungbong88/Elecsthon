package com.bethejustice.elecchargingstation.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bethejustice.elecchargingstation.Activity.InitActivity;
import com.bethejustice.elecchargingstation.Activity.MainActivity;
import com.bethejustice.elecchargingstation.Helper.VisitChecker;
import com.bethejustice.elecchargingstation.R;

import static android.content.Context.MODE_PRIVATE;

public class InitDialog extends Dialog {

    private static final String TAG = "InitDialog";

    VisitChecker visitChecker;
    Button saveButton;

    EditText nickName;
    EditText safeDist;
    Spinner chargingType;
    Context context;

    public InitDialog(@NonNull Context context) {
        super(context);

        this.context = context;
    }

    public InitDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setting);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        nickName = findViewById(R.id.edit_nickname);
        safeDist = findViewById(R.id.edit_safe);
        chargingType = findViewById(R.id.spinner_type);

        visitChecker = VisitChecker.getInstance(context);

        saveButton = findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkNull()) {

                    SharedPreferences dataPref = getContext().getSharedPreferences("data", MODE_PRIVATE);
                    SharedPreferences.Editor dataEditor = dataPref.edit();

                    dataEditor.putString("nickname", nickName.getText().toString());
                    dataEditor.putInt("type", chargingType.getSelectedItemPosition());
                    dataEditor.putInt("safe", Integer.parseInt(safeDist.getText().toString()));
                    dataEditor.commit();

                    Log.d("initdata", nickName.getText().toString() + chargingType.getSelectedItemPosition() + safeDist.getText().toString());

                    Intent intent = new Intent(getContext(), MainActivity.class);
                    getContext().startActivity(intent);
                    visitChecker.setVisited();
                    dismiss();

                } else {
                    Toast.makeText(context, R.string.null_check, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean checkNull() {
        Log.d(TAG, "checkNull: ");
        if (nickName.getText().toString().length() == 0 || safeDist.getText().toString().length() == 0) return false;
        return true;
    }
}
