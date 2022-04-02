package net.tslat.aoa3.player.ability;

import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

public class FertiliseFarmland extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.BLOCK_INTERACT};

	public FertiliseFarmland(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.FERTILISE_FARMLAND.get(), skill, data);
	}

	public FertiliseFarmland(AoASkill.Instance skill, CompoundTag data) {
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
			Level world = ev.getWorld();
			BlockState state = world.getBlockState(ev.getPos());

			if (state.getBlock() == Blocks.FARMLAND && WorldUtil.canModifyBlock(world, ev.getPos(), ev.getPlayer(), stack)) {
				world.setBlock(ev.getPos(), AoABlocks.FERTILISED_FARMLAND.get().defaultBlockState().setValue(FarmBlock.MOISTURE, state.getValue(FarmBlock.MOISTURE)), Block.UPDATE_ALL);

				PlayerUtil.giveXpToPlayer((ServerPlayer)ev.getPlayer(), AoASkills.FARMING.get(), PlayerUtil.getTimeBasedXpForLevel(PlayerUtil.getLevel(ev.getPlayer(), AoASkills.FARMING.get()), 1), false);

				if (!ev.getPlayer().isCreative())
					stack.shrink(1);
			}
		}
	}
}
