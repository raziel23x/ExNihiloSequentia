package novamachina.exnihilosequentia.common.item.mesh;

import novamachina.exnihilosequentia.common.init.ModInitialization;
import net.minecraft.item.Item;

public class MeshItem extends Item {

    private final EnumMesh mesh;

    public MeshItem(EnumMesh mesh) {
        // TODO Mesh damage can be disabled
        super(new Item.Properties().group(ModInitialization.ITEM_GROUP).maxDamage(mesh.getMaxDamage()));
        this.mesh = mesh;
    }

    public EnumMesh getMesh() {
        return mesh;
    }
}
