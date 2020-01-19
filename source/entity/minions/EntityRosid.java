package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;

import javax.annotation.Nullable;

public class EntityRosid extends AoAMinion {
	public static final float entityWidth = 0.5f;

	public EntityRosid(final World world) {
		super(world, 200, entityWidth, 0.875f);
	}

	public EntityRosid(EntityLivingBase summoner) {
		this(summoner.world);

		if (summoner instanceof EntityPlayer)
			setTamedBy((EntityPlayer)summoner);

		setPosition(summoner.posX, summoner.posY, summoner.posZ);
	}

	@Override
	public float getEyeHeight() {
		return 0.75f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20.0d;
	}

	@Override
	protected double getBaseMoveSpeed() {
		return 0.3d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6.0d;
	}

	@Override
	protected boolean isHostile() {
		return true;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityRosid;
	}
}
