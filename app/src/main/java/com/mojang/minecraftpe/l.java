package com.mojang.minecraftpe;

import android.preference.Preference;

/* loaded from: classes.dex */
final class l extends a {
    private /* synthetic */ MainMenuOptionsActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(MainMenuOptionsActivity mainMenuOptionsActivity) {
        this.a = mainMenuOptionsActivity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.mojang.minecraftpe.a
    public final void a(Preference preference) {
        MainMenuOptionsActivity.b(preference);
        this.a.a.a(preference);
    }
}
