package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.block.CropsBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.world.BlockEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.RandomUtil;

public class DryadSpriteSpawn extends ScalableModAbility {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_BREAK};

	public DryadSpriteSpawn(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	public DryadSpriteSpawn(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.DRYAD_SPRITE_SPAWN.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockBreak(BlockEvent.BreakEvent ev) {
		if (testAsChance()) {
			if (!(ev.getState().getBlock() instanceof CropsBlock) || !((CropsBlock)ev.getState().getBlock()).isMaxAge(ev.getState()) || getPlayer().isCreative())
				return;

			IWorld world = ev.getWorld();
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
