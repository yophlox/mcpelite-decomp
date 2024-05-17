package com.mojang.minecraftpe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import com.android.vending.licensing.p;
import com.android.vending.licensing.t;

/* loaded from: classes.dex */
public class Minecraft_Market extends MainActivity {
    private static final byte[] a = {120, 122, 99, -124, -18, 65, 29, -17, 51, -90, 89, 101, Byte.MIN_VALUE, -118, -45, 125, 74, -124, -3, 58};
    private t b;
    private com.android.vending.licensing.b c;
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
        this.b = new k(this);
        this.c = new com.android.vending.licensing.b(this, new com.android.vending.licensing.m(this, new p(a, getPackageName(), string)), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqU9snkqLkTCYZQfQgNy9eabP/OcumQTgcoYhuBbmx2isicIX8mSUNJn64yxiA6acqnJzJxGbKW9H+gSWMuRtCtwI3Exb3sCq19EiVtjos4u5BJatzBKXXDDJeeYrejGk8FgT6ffokcilJFY4wgQQxDGFdfE/reAqm6+VKUtoqnjMpG0wVZn+o0bJfxNvE/ydJPlabDmoywEn9zEl0hXo0i+cimVlFZcTT6ed7U9celM2Ywg+7qVIu7fmBHPucTIoUAbipEAIvP2EntOqrhUG6GKJ219Qhdr43fvnyBailudNBiDRqA+x0rCH1JgmV/BvyAHTBylPzroeh9rWJNNPxQIDAQAB");
        this.c.a(this.b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mojang.minecraftpe.MainActivity, android.app.NativeActivity, android.app.Activity
    public void onDestroy() {
        this.c.b();
        this.c.a();
        super.onDestroy();
    }
}
