package novamachina.exnihilosequentia.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import novamachina.exnihilosequentia.common.init.ModTiles;
import novamachina.exnihilosequentia.common.tileentity.SieveTile;
import novamachina.exnihilosequentia.common.utility.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import novamachina.exnihilosequentia.common.utility.ExNihiloLogger;
import org.apache.logging.log4j.LogManager;

public class SieveRender extends AbstractModBlockRenderer<SieveTile> {
    private static final ExNihiloLogger logger = new ExNihiloLogger(LogManager.getLogger());

    public SieveRender(
        TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    public static void register() {
        logger.debug("Registering sieve renderer");
        ClientRegistry.bindTileEntityRenderer(ModTiles.SIEVE.get(), SieveRender::new);
    }

    @Override
    public void render(SieveTile tileEntity, float partialTicks, MatrixStack matrixStack,
                       IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

        ResourceLocation blockTexture = tileEntity.getTexture();
        if (blockTexture != null) {
            TextureAtlasSprite sprite = Minecraft.getInstance()
                .getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(
                    new ResourceLocation(blockTexture.getNamespace(),
                        "block/" + blockTexture.getPath()));
            IVertexBuilder builder = buffer.getBuffer(RenderType.getTranslucent());

            matrixStack.push();
            matrixStack.translate(.5, .5, .5);
            matrixStack.translate(-.5, -.5, -.5);

            double height = 0.99f - tileEntity.getProgress();
            float fillAmount = (float) (0.15625 * height + 0.84375);

            add(builder, matrixStack, 0, fillAmount, 0, sprite.getMinU(), sprite.getMinV(), Color.WHITE);
            add(builder, matrixStack, 1, fillAmount, 0, sprite.getMaxU(), sprite.getMinV(), Color.WHITE);
            add(builder, matrixStack, 1, fillAmount, 1, sprite.getMaxU(), sprite.getMaxV(), Color.WHITE);
            add(builder, matrixStack, 0, fillAmount, 1, sprite.getMinU(), sprite.getMaxV(), Color.WHITE);

            add(builder, matrixStack, 0, fillAmount, 1, sprite.getMinU(), sprite.getMaxV(), Color.WHITE);
            add(builder, matrixStack, 1, fillAmount, 1, sprite.getMaxU(), sprite.getMaxV(), Color.WHITE);
            add(builder, matrixStack, 1, fillAmount, 0, sprite.getMaxU(), sprite.getMinV(), Color.WHITE);
            add(builder, matrixStack, 0, fillAmount, 0, sprite.getMinU(), sprite.getMinV(), Color.WHITE);

            matrixStack.pop();
        }
    }
}