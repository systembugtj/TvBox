package me.systembug.tv.view;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

/**
 * Created by systembug on 5/7/16.
 */
public class TvBox {
    public static Iconify.IconifyInitializer initializeIconify() {
        return Iconify.with(new MaterialModule());
    }
}
