package com.mojang.minecraftpe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

/* loaded from: classes.dex */
public class Minecraft_Market extends MainActivity {
    private static final byte[] a = {120, 122, 99, -124, -18, 65, 29, -17, 51, -90, 89, 101, Byte.MIN_VALUE, -118, -45, 125, 74, -124, -3, 58};
    private volatile int d = -1;

    @Override // com.mojang.minecraftpe.MainActivity
    public void buyGame() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mojang.minecraftpe")));
    }

    @Override // com.mojang.minecraftpe.MainActivity
    public int checkLicense() {
        return this.d;
    }

    @Override // com.mojang.minecraftpe.MainActivity, android.app.NativeActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String string = Settings.Secure.getString(getContentResolver(), "android_id");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mojang.minecraftpe.MainActivity, android.app.NativeActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
