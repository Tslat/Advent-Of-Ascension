package net.nevermine.resource.boss;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLiving;

@SideOnly(Side.CLIENT)
public final class EternalBossStatus {
	public static float healthScale;
	public static int statusBarTime;
	public static int selected;
	public static String bossName;
	public static boolean hasColorModifier;

	public static void setBossStatus(final EntityLiving b, final boolean colour, final int image) {
		EternalBossStatus.healthScale = b.getHealth() / b.getMaxHealth();
		EternalBossStatus.statusBarTime = 100;
		EternalBossStatus.hasColorModifier = colour;
		EternalBossStatus.selected = image;
	}
}
