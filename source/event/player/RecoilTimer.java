package net.nevermine.event.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class RecoilTimer implements IExtendedEntityProperties {
	private final EntityPlayer player;
	private static final String NAME = "Recoil";
	private int ticks;
	private float recoil;

	public RecoilTimer(final EntityPlayer player) {
		this.player = player;
		ticks = 0;
		recoil = 0.0f;
	}

	public static void addProperties(final EntityPlayer player) {
		player.registerExtendedProperties("Recoil", new RecoilTimer(player));
	}

	public static RecoilTimer getProperties(final EntityPlayer player) {
		return (RecoilTimer)player.getExtendedProperties("Recoil");
	}

	public void saveNBTData(final NBTTagCompound n) {
		final NBTTagCompound entityData = player.getEntityData();
		final EntityPlayer player = this.player;
		final NBTTagCompound tag = entityData.getCompoundTag("PlayerPersisted");
		tag.setInteger("TickRec", ticks);
		tag.setFloat("Rec", recoil);
		n.setTag("Recoil", tag);
		final NBTTagCompound entityData2 = this.player.getEntityData();
		final EntityPlayer player2 = this.player;
		entityData2.setTag("PlayerPersisted", tag);
	}

	public void loadNBTData(final NBTTagCompound n) {
		final NBTTagCompound entityData = player.getEntityData();
		final EntityPlayer player = this.player;
		final NBTTagCompound tag = entityData.getCompoundTag("PlayerPersisted");
		if (!tag.hasKey("TickRec")) {
			return;
		}
		ticks = tag.getInteger("TickRec");
		recoil = tag.getFloat("Rec");
		final NBTTagCompound entityData2 = this.player.getEntityData();
		final EntityPlayer player2 = this.player;
		entityData2.setTag("PlayerPersisted", tag);
	}

	public void setRecoil(final float i) {
		recoil = i;
	}

	public float getRecoil() {
		return recoil;
	}

	public void subtractTick() {
		--ticks;
	}

	public void startCounter() {
		ticks = 25;
	}

	public void init(final Entity entity, final World world) {
	}
}
