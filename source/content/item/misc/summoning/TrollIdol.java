package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TrollIdol extends BossSpawningItem {
	public TrollIdol() {
		super(AoASounds.ENTITY_SMASH_AMBIENT, ParticleTypes.SMOKE, ParticleTypes.LARGE_SMOKE);
	}

	@Override
	public void spawnBoss(Level world, ServerPlayer summoner, double posX, double posY, double posZ) {
		/*SmashEntity smash = new SmashEntity(AoAMobs.SMASH.get(), world);

		smash.moveTo(posX + 0.5d, posY + 0.5d, posZ + 0.5d, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(smash);

		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.SMASH.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, new BlockPos(posX, posY, posZ), 50);*/
	}

	@Override
	public boolean canSpawnHere(Level world, ServerPlayer player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, new TranslatableComponent("message.feedback.spawnBoss.difficultyFail").withStyle(ChatFormatting.RED));

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.OVERWORLD.key)) {
			/*PlayerUtil.notifyPlayer(player, new TranslatableComponent(AoAMobs.SMASH.get().getDescriptionId() + ".wrongDimension").withStyle(ChatFormatting.RED));*/

			return false;
		}

		if (!world.noCollision(new AABB(posX - 0.5d, posY, posZ - 0.5d, posX + 0.5d, posY + 3, posZ + 0.5d))) {
			PlayerUtil.notifyPlayer(player, new TranslatableComponent("message.feedback.spawnBoss.noSpace").withStyle(ChatFormatting.RED));

			return false;
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getLocaleMessage("items.description.boss_summon_item.unstable", ChatFormatting.AQUA));
	}
}
