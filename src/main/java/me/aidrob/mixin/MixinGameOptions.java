package me.aidrob.mixin;

import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = GameOptions.class, priority = 2000)
public class MixinGameOptions {

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 30), expect = 0)
    private int customMin(int constant) {
        return 0; // FOV thấp nhất
    }

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 110), expect = 0)
    private int customMax(int constant) {
        return 200; // FOV cao nhất
    }
}
