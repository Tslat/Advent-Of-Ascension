package net.tslat.aoa3.item.misc.summon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.boss.kingbambambam.EntityKingBamBamBam;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ExplosiveIdol extends BossSpawningItem {
	public ExplosiveIdol() {
		super("ExplosiveIdol", "explosive_idol", SoundsRegister.MOB_KING_BAMBAMBAM_LIVING, EnumParticleTypes.SMOKE_NORMAL, EnumParticleTypes.SMOKE_LARGE, EnumParticleTypes.EXPLOSION_NORMAL);
	}

	@Override
	public void spawnBoss(World world, EntityPlayer summoner, double posX, double posY, double posZ) {
		EntityKingBamBamBam kingBamBamBam = new EntityKingBamBamBam(world);

		kingBamBamBam.setLocationAndAngles(posX, posY, posZ, itemRand.nextFloat() * 360f, 0f);
		world.spawnEntity(kingBamBamBam);
		StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.kingBamBamBam.spawn", summoner.getDisplayNameString()), kingBamBamBam, 50);
	}

	@Override
	public boolean canSpawnHere(World world, EntityPlayer player, double posX, double posY, double posZ) {
		if (world.provider.getDimension() != -1) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.mob.kingBamBamBam.incorrectDimension");

			return false;
		}

		if (world.collidesWithAnyBlock(new AxisAlignedBB(posX - 1d, posY, posZ - 1d, posX + 1d, posY + 4d, posZ + 1d))) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.noSpace");

			return false;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.ExplosiveIdol.desc.1", TextFormatting.RED));
		tooltip.add(StringUtil.getColourLocaleString("item.ExplosiveIdol.desc.2", TextFormatting.RED));
		tooltip.add(StringUtil.getColourLocaleString("items.description.bossSummonItem.unstable", TextFormatting.DARK_RED));
	}
}
