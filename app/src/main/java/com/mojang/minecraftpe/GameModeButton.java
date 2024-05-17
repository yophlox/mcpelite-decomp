package com.mojang.minecraftpe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.mojang.android.StringValue;
import com.mojang.minecraftpe.demo.R;

/* loaded from: classes.dex */
public class GameModeButton extends ToggleButton implements View.OnClickListener, StringValue {
    private boolean a;
    private int b;

    public GameModeButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = false;
        this.b = 0;
        setOnClickListener(this);
    }

    private static int a(int i) {
        if (i > 1) {
            return 1;
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    private void b() {
        this.b = a(isChecked() ? 1 : 0);
        int i = R.string.gamemode_creative_summary;
        if (this.b == 1) {
            i = R.string.gamemode_survival_summary;
        }
        String string = getContext().getString(i);
        View findViewById = getRootView().findViewById(R.id.labelGameModeDesc);
        System.out.println("Mode: " + this.b + ", view? " + (findViewById != null));
        if (string == null || findViewById == null || !(findViewById instanceof TextView)) {
            return;
        }
        ((TextView) findViewById).setText(string);
    }

    @Override // com.mojang.android.StringValue
    public final String a() {
        return new String[]{"creative", "survival"}[a(this.b)];
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        if (this.a) {
            return;
        }
        b();
        this.a = true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b();
    }

    @Override // android.widget.ToggleButton, android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        b();
    }
}
