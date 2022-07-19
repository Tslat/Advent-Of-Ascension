package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.event.level.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.RandomUtil;

public class DryadSpriteSpawn extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK};

	public DryadSpriteSpawn(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	public DryadSpriteSpawn(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockBreak(BlockEvent.BreakEvent ev) {
		if (testAsChance()) {
			if (!(ev.getState().getBlock() instanceof CropBlock) || !((CropBlock)ev.getState().getBlock()).isMaxAge(ev.getState()) || getPlayer().isCreative())
				return;

			LevelAccessor world = ev.getLevel();
			BlockPos pos = ev.getPos();

			if (world.getBlockState(pos.above()).isAir()) {
				DryadSpriteEntity dryad = new DryadSpriteEntity(ev.getPlayer());

				dryad.setPos(pos.getX() + 0.5d, pos.getY() + 0.1d, pos.getZ() + 0.5d);

				world.addFreshEntity(dryad);

				for(int i = 0; i < 20; ++i) {
					world.addParticle(ParticleTypes.HAPPY_VILLAGER, dryad.getRandomX(0.5f), dryad.getRandomY(), dryad.getRandomZ(0.5f), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d), RandomUtil.randomScaledGaussianValue(0.02d));
				}
			}
		}
	}
}
