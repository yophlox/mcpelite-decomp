package com.mojang.minecraftpe;

import com.android.vending.licensing.t;

/* loaded from: classes.dex */
final class k implements t {
    private /* synthetic */ Minecraft_Market a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ k(Minecraft_Market minecraft_Market) {
        this(minecraft_Market, (byte) 0);
    }

    private k(Minecraft_Market minecraft_Market, byte b) {
        this.a = minecraft_Market;
    }

    @Override // com.android.vending.licensing.t
    public final void a() {
        if (this.a.isFinishing()) {
            return;
        }
        Minecraft_Market.a(this.a, 0);
    }

    @Override // com.android.vending.licensing.t
    public final void a(com.android.vending.licensing.j jVar) {
        if (this.a.isFinishing()) {
            return;
        }
        Minecraft_Market.a(this.a, jVar.ordinal() + 1000);
    }

    @Override // com.android.vending.licensing.t
    public final void b() {
        if (this.a.isFinishing()) {
            return;
        }
        Minecraft_Market.a(this.a, 106);
    }
}
