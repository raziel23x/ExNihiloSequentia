package novamachina.exnihilosequentia.common.compat.waila;

import novamachina.exnihilosequentia.common.tileentity.InfestingLeavesTile;
import novamachina.exnihilosequentia.common.utility.StringUtils;
import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.List;

public class InfestingLeavesComponentProvider implements IComponentProvider {
    @Override
    public void appendBody(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        InfestingLeavesTile infestingLeavesTile = (InfestingLeavesTile) accessor.getTileEntity();

        tooltip.add(new TranslationTextComponent("waila.progress", StringUtils
            .formatPercent((float) infestingLeavesTile.getProgress() / 100)));
    }
}
