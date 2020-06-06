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
import net.tslat.aoa3.entity.boss.bane.EntityBane;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HauntedIdol extends BossSpawningItem {
	public HauntedIdol() {
		super("HauntedIdol", "haunted_idol", SoundsRegister.MOB_BANE_LIVING, EnumParticleTypes.PORTAL, EnumParticleTypes.SPELL_MOB);
	}

	@Override
	public void spawnBoss(World world, EntityPlayer summoner, double posX, double posY, double posZ) {
		EntityBane bane = new EntityBane(world);

		bane.setLocationAndAngles(posX, posY, posZ, itemRand.nextFloat() * 360f, 0f);
		world.spawnEntity(bane);
		StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.bane.spawn", summoner.getDisplayNameString()), bane, 50);
	}

	@Override
	public boolean canSpawnHere(World world, EntityPlayer player, double posX, double posY, double posZ) {
		if (world.provider.getDimension() != ConfigurationUtil.MainConfig.dimensionIds.greckon) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.mob.bane.incorrectDimension");

			return false;
		}

		if (world.collidesWithAnyBlock(new AxisAlignedBB(posX - 0.5d, posY, posZ - 0.5d, posX + 0.5d, posY + 2.5d, posZ + 0.5d))) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.noSpace");

			return false;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.HauntedIdol.desc.1", TextFormatting.DARK_PURPLE));
		tooltip.add(StringUtil.getColourLocaleString("items.description.bossSummonItem.unstable", TextFormatting.DARK_RED));
	}
}
