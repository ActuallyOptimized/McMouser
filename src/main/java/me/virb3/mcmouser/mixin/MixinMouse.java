package me.virb3.mcmouser.mixin;

import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(MouseHandler.class)
public class MixinMouse {
    @Redirect(method = "onPress", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ON_OSX:Z"))
    private boolean onPress() {
        return false;
    }

    //Doubles and longs are counted twice, index 5 is vertical in onScroll.
	@ModifyVariable(method = "onScroll", at = @At(value = "HEAD", target = "Lnet/minecraft/client/Minecraft;ON_OSX:Z"), index = 5)
	private double scrollFix(double vertical1, long window, double horizontal, double vertical2) {
		return vertical1 == 0 ? horizontal : vertical1;
	}
}
