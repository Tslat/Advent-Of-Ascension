package net.tslat.aoa3.content.item.tool.artifice;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.util.LocaleUtil;

public class TemporalAnvil extends ArtificeItem {
    public TemporalAnvil() {
        super(new Item.Properties().stacksTo(1));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide)
            player.openMenu(new SimpleMenuProvider((containerId, inventory, plInventory) -> new AnvilMenu(containerId, inventory) {
                @Override
                protected void onTake(Player player, ItemStack stack) {
                    super.onTake(player, stack);
                    SoundBuilder.at(SoundEvents.ANVIL_USE, level, player.position()).onlyFor(player).pitch(0.95f).varyPitch(0.05f).play();
                }
            }, Component.translatable(LocaleUtil.createContainerLocaleKey("temporal_anvil"))));

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide);
    }
}