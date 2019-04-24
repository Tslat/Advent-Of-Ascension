package net.nevermine.item.functional;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.nevermine.container.PlayerContainer;
import net.nevermine.skill.anima.animaHelper;

import static net.nevermine.container.PlayerContainer.Skills.Anima;

public class FragmentedAnimaStone extends Item {
	public boolean onItemUse(final ItemStack stack, final EntityPlayer player, final World world, final int x, final int y, final int z, final int cnt, final float f1, final float f2, final float f3) {
		if (!player.canPlayerEdit(x, y, z, cnt, stack))
			return false;

		if (applyBonemeal(stack, world, x, y, z, player)) {
			PlayerContainer cont = PlayerContainer.getProperties(player);

			if (!world.isRemote) {
				cont.addExperience(cont.getExpRequired(Anima) / animaHelper.getExpDenominator(cont.getLevel(Anima)), Anima);
				world.playAuxSFX(2005, x, y, z, 0);
			}

			return true;
		}

		return false;
	}

	public static boolean applyBonemeal(final ItemStack stack, final World world, final int x, final int y, final int z, final EntityPlayer player) {
		final Block block = world.getBlock(x, y, z);
		final BonemealEvent event = new BonemealEvent(player, world, block, x, y, z);

		if (MinecraftForge.EVENT_BUS.post(event))
			return false;

		if (event.getResult() == Event.Result.ALLOW) {
			if (!world.isRemote)
				--stack.stackSize;

			return true;
		}

		if (block instanceof IGrowable) {
			final IGrowable igrowable = (IGrowable)block;

			if (igrowable.func_149851_a(world, x, y, z, world.isRemote)) {
				if (!world.isRemote) {
					if (igrowable.func_149852_a(world, world.rand, x, y, z)) {
						igrowable.func_149853_b(world, world.rand, x, y, z);
					}

					--stack.stackSize;
				}
				return true;
			}
		}
		return false;
	}
}
