package com.mojang.minecraftpe;

/* loaded from: classes.dex */
final class g implements Runnable {
    private /* synthetic */ MainActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.finish();
    }
}
