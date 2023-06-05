package org._9636dev.autosmithingtablerewrite;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org._9636dev.autosmithingtablerewrite.client.screen.AutoSmithingTableScreen;
import org._9636dev.autosmithingtablerewrite.common.block.AutoBlocks;
import org._9636dev.autosmithingtablerewrite.common.blockenttiy.AutoBlockEntities;
import org._9636dev.autosmithingtablerewrite.common.config.AutoCommonConfig;
import org._9636dev.autosmithingtablerewrite.common.container.AutoContainers;
import org._9636dev.autosmithingtablerewrite.common.item.AutoItems;
import org._9636dev.autosmithingtablerewrite.common.recipe.AutoRecipes;
import org._9636dev.autosmithingtablerewrite.integration.AutoModHooks;
import org.slf4j.Logger;

@Mod(AutoSmithingTableMod.MODID)
public class AutoSmithingTableMod {

    public static final String MODID = "autosmithingtable";
    private static final Logger LOGGER = LogUtils.getLogger();

    private final AutoModHooks hooks;
    public AutoSmithingTableMod() {
        this.hooks = new AutoModHooks();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::doClientStuff);
        modEventBus.addListener(this::enqueueIMC);

        // Registries
        AutoBlocks.BLOCKS.register(modEventBus);
        AutoItems.ITEMS.register(modEventBus);
        AutoBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        AutoContainers.CONTAINERS.register(modEventBus);
        AutoRecipes.RECIPE_TYPES.register(modEventBus);
        AutoRecipes.RECIPE_SERIALIZERS.register(modEventBus);

        ForgeConfigSpec.Builder commonConfigBuilder = new ForgeConfigSpec.Builder();
        AutoCommonConfig.setInstance(new AutoCommonConfig(commonConfigBuilder));
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfigBuilder.build());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.debug("Initializing AutoSmithingTable for both");
        hooks.commonSetup(event);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        LOGGER.debug("Initializing AutoSmithingTable client");

        MenuScreens.register(AutoContainers.AUTO_SMITHING_TABLE.get(), AutoSmithingTableScreen::new);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        hooks.enqueueIMC(event);
    }
}
