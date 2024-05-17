package com.mojang.minecraftpe;

import android.content.Intent;
import android.net.Uri;

/* loaded from: classes.dex */
public class Minecraft_Market_Demo extends MainActivity {
    @Override // com.mojang.minecraftpe.MainActivity
    public void buyGame() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.mojang.minecraftpe")));
    }

    @Override // com.mojang.minecraftpe.MainActivity
    public int checkLicense() {
        return 0;
    }
}
