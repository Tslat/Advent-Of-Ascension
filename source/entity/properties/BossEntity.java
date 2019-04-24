package net.tslat.aoa3.entity.properties;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface BossEntity {
	public ResourceLocation getBossBarTexture();

	@SideOnly(Side.CLIENT)
	public void checkMusicStatus();
}
