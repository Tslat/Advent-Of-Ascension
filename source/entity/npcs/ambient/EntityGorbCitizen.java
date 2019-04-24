package net.tslat.aoa3.entity.npcs.ambient;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityGorbCitizen extends AoAAmbientNPC {
	public static final float entityWidth = 0.5625f;

	public EntityGorbCitizen(World world) {
		super(world, entityWidth, 1.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.gorb_citizen." + rand.nextInt(5));
	}
}
