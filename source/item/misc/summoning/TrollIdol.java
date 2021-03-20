package net.tslat.aoa3.item.misc.summoning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.boss.SmashEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TrollIdol extends BossSpawningItem {
	public TrollIdol() {
		super(AoASounds.ENTITY_SMASH_AMBIENT, ParticleTypes.SMOKE, ParticleTypes.LARGE_SMOKE);
	}

	@Override
	public void spawnBoss(World world, ServerPlayerEntity summoner, double posX, double posY, double posZ) {
		SmashEntity smash = new SmashEntity(AoAEntities.Mobs.SMASH.get(), world);

		smash.moveTo(posX + 0.5d, posY + 0.5d, posZ + 0.5d, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(smash);

		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("entity.aoa3.smash.spawn", summoner.getDisplayName()), world, new BlockPos(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(World world, ServerPlayerEntity player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, "message.feedback.spawnBoss.difficultyFail", TextFormatting.RED);

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.OVERWORLD.key)) {
			PlayerUtil.notifyPlayer(player, "entity.aoa3.smash.wrongDimension", TextFormatting.RED);

			return false;
		}

		if (!world.noCollision(new AxisAlignedBB(posX - 0.5d, posY, posZ - 0.5d, posX + 0.5d, posY + 3, posZ + 0.5d))) {
			PlayerUtil.notifyPlayer(player, "message.feedback.spawnBoss.noSpace", TextFormatting.RED);

			return false;
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getLocaleMessage("items.description.boss_summon_item.unstable", TextFormatting.AQUA));
	}
}
