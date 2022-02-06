package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.WorldUtil;

public class FertiliseFarmland extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_INTERACT};

	public FertiliseFarmland(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FERTILISE_FARMLAND.get(), skill, data);
	}

	public FertiliseFarmland(AoASkill.Instance skill, CompoundNBT data) {
		super(AoAAbilities.FERTILISE_FARMLAND.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleBlockInteraction(PlayerInteractEvent.RightClickBlock ev) {
		ItemStack stack = ev.getItemStack();

		if (stack.getItem() == Items.BONE_MEAL) {
			World world = ev.getWorld();
			BlockState state = world.getBlockState(ev.getPos());

			if (state.getBlock() == Blocks.FARMLAND && WorldUtil.canModifyBlock(world, ev.getPos(), ev.getPlayer(), stack)) {
				world.setBlock(ev.getPos(), AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState().setValue(FarmlandBlock.MOISTURE, state.getValue(FarmlandBlock.MOISTURE)), Constants.BlockFlags.DEFAULT);

				if (!ev.getPlayer().isCreative())
					stack.shrink(1);
			}
		}
	}
}
