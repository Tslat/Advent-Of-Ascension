package net.tslat.aoa3.entity.npcs.ambient;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public class EntityZalChild extends AoAAmbientNPC {
	public static final float entityWidth = 0.16875f;

	public EntityZalChild(World world) {
		super(world, entityWidth, 0.5625f);
	}

	@Override
	public float getEyeHeight() {
		return 0.20625f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Nullable
	@Override
	protected ITextComponent getInteractMessage() {
		return StringUtil.getLocale("message.dialogue.zal_child." + rand.nextInt(5));
	}
}
