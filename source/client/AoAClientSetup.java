package net.tslat.aoa3.client;

import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.clientextension.fluid.CandiedWaterClientExtension;
import net.tslat.aoa3.client.clientextension.fluid.TarClientExtension;
import net.tslat.aoa3.client.clientextension.fluid.ToxicWasteClientExtension;
import net.tslat.aoa3.client.clientextension.item.AttuningBowlClientExtension;
import net.tslat.aoa3.client.clientextension.item.LargeGunClientExtension;
import net.tslat.aoa3.client.clientextension.item.SkillHelmetClientExtension;
import net.tslat.aoa3.client.event.ClientEventHandler;
import net.tslat.aoa3.client.gui.container.*;
import net.tslat.aoa3.client.gui.hud.AoACameraModifications;
import net.tslat.aoa3.client.gui.hud.BossBarRenderer;
import net.tslat.aoa3.client.gui.hud.HealthStatusRenderer;
import net.tslat.aoa3.client.gui.hud.XpParticlesRenderer;
import net.tslat.aoa3.client.gui.overlay.ScopeOverlayRenderer;
import net.tslat.aoa3.client.gui.overlay.ScreenEffectRenderer;
import net.tslat.aoa3.client.model.ModelProperties;
import net.tslat.aoa3.client.particle.EntityOrbitingParticle;
import net.tslat.aoa3.client.particle.FloatingItemFragmentParticle;
import net.tslat.aoa3.client.particle.GenericSpriteParticle;
import net.tslat.aoa3.client.particle.OrbParticle;
import net.tslat.aoa3.client.particle.entityaffecting.BurningFlameParticle;
import net.tslat.aoa3.client.particle.entityaffecting.EntityAffectingParticle;
import net.tslat.aoa3.client.particle.entityaffecting.FreezingSnowflakeParticle;
import net.tslat.aoa3.client.particle.entityaffecting.SandstormParticle;
import net.tslat.aoa3.client.render.AoAGuiElementRenderers;
import net.tslat.aoa3.client.render.dimension.AoADimensionRenderEffects;
import net.tslat.aoa3.client.render.entity.misc.OccultBlockRenderer;
import net.tslat.aoa3.client.render.shader.AoAPostProcessing;
import net.tslat.aoa3.common.menu.MendingTableMenu;
import net.tslat.aoa3.common.menu.WhitewashingTableMenu;
import net.tslat.aoa3.common.registration.AoAMenus;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.item.AoAArmour;
import net.tslat.aoa3.common.registration.item.AoATools;
import net.tslat.aoa3.content.item.weapon.blaster.AoABlaster;
import net.tslat.aoa3.content.item.weapon.cannon.AoACannon;
import net.tslat.aoa3.content.item.weapon.gun.AoAGun;
import net.tslat.aoa3.content.item.weapon.sniper.AoASniper;
import net.tslat.aoa3.data.client.AoAResourceReloadListeners;
import net.tslat.aoa3.integration.IntegrationManager;

import java.util.function.Consumer;

public final class AoAClientSetup {
    public static void init(ModContainer modContainer) {
        AoAEntityRendering.init();
        ClientEventHandler.init();
        AoAPostProcessing.init();
        AoAGuiElementRenderers.init();
        AoAKeybinds.init();
        AoAResourceReloadListeners.init();
        ScopeOverlayRenderer.init();
        XpParticlesRenderer.init();
        ScreenEffectRenderer.init();
        AoADimensionRenderEffects.init();
        AoATintHandling.init();
        BossBarRenderer.init();
        AoACameraModifications.init();
        HealthStatusRenderer.init();
        OccultBlockRenderer.init();
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterParticleProvidersEvent.class, AoAClientSetup::registerParticleFactories);
        AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterMenuScreensEvent.class, AoAClientSetup::registerMenuScreens);
        AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterClientExtensionsEvent.class, AoAClientSetup::registerClientExtensions);
    }

    public static void postInit(Consumer<Runnable> workQueue) {
        workQueue.accept(() -> {
            AoABlocks.getRegisteredLiquidsForRenderTypes().forEach(ClientOperations::applyFluidRenderType);
            ModelProperties.init();
            AoAGuiElementRenderers.lateInit();
            IntegrationManager.clientInit();
        });
    }

    private static void registerMenuScreens(RegisterMenuScreensEvent ev) {
        ev.register(AoAMenus.DIVINE_STATION.get(), DivineStationScreen::new);
        ev.register(AoAMenus.MENDING_TABLE.get(), Generic2SlotContainerScreen<MendingTableMenu>::new);
        ev.register(AoAMenus.WHITEWASHING_TABLE.get(), Generic2SlotContainerScreen<WhitewashingTableMenu>::new);
        ev.register(AoAMenus.FRAME_BENCH.get(), FrameBenchScreen::new);
        ev.register(AoAMenus.INFUSION_TABLE.get(), InfusionTableScreen::new);
        ev.register(AoAMenus.INFUSED_PRESS.get(), InfusedPressScreen::new);
        ev.register(AoAMenus.TINKERERS_TABLE.get(), TinkerersTableScreen::new);
        ev.register(AoAMenus.IMBUING_CHAMBER.get(), ImbuingChamberScreen::new);
        ev.register(AoAMenus.TRADER.get(), MerchantScreen::new);
        ev.register(AoAMenus.BANKER.get(), BankerScreen::new);
        ev.register(AoAMenus.CORRUPTED_TRAVELLER.get(), CorruptedTravellerScreen::new);
    }

    private static void registerParticleFactories(RegisterParticleProvidersEvent ev) {
        ev.registerSpriteSet(AoAParticleTypes.GENERIC_DUST.get(), GenericSpriteParticle.Provider::new);
        ev.registerSpecial(AoAParticleTypes.FLOATING_ITEM_FRAGMENT.get(), new FloatingItemFragmentParticle.Factory());
        ev.registerSprite(AoAParticleTypes.FREEZING_SNOWFLAKE.get(), new EntityAffectingParticle.SingleSpriteProvider<>(FreezingSnowflakeParticle::new));
        ev.registerSpriteSet(AoAParticleTypes.BURNING_FLAME.get(), sprites -> new EntityAffectingParticle.Provider<>(sprites, BurningFlameParticle::new));
        ev.registerSpriteSet(AoAParticleTypes.SANDSTORM.get(), sprites -> new EntityAffectingParticle.Provider<>(sprites, SandstormParticle::new));
        ev.registerSprite(AoAParticleTypes.ORB.get(), new OrbParticle.Provider());
        ev.registerSpriteSet(AoAParticleTypes.FIRE_AURA.get(), EntityOrbitingParticle.Provider::new);
    }

    private static void registerClientExtensions(RegisterClientExtensionsEvent ev) {
        ev.registerItem(new SkillHelmetClientExtension(), AoAArmour.HELM_OF_THE_DEXTROUS.get(), AoAArmour.HELM_OF_THE_DRYAD.get(), AoAArmour.HELM_OF_THE_RITUALIST.get(), AoAArmour.HELM_OF_THE_TRAWLER.get(), AoAArmour.HELM_OF_THE_TREASURER.get(), AoAArmour.HELM_OF_THE_WARRIOR.get());
        ev.registerItem(new AttuningBowlClientExtension(), AoATools.ATTUNING_BOWL.get());

        for (DeferredHolder<Item, ? extends Item> item : AoARegistries.ITEMS.getAllAoARegisteredObjects()) {
            if (item.get() instanceof AoABlaster || (item.get() instanceof AoAGun gun && (gun instanceof AoACannon || gun instanceof AoASniper)))
                ev.registerItem(new LargeGunClientExtension(), item);
        }

        ev.registerFluidType(new ToxicWasteClientExtension(), AoAFluidTypes.TOXIC_WASTE.get());
        ev.registerFluidType(new TarClientExtension(), AoAFluidTypes.TAR.get());
        ev.registerFluidType(new CandiedWaterClientExtension(), AoAFluidTypes.CANDIED_WATER.get());
    }
}
