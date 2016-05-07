package me.systembug.tv.model;

import android.content.Context;

import com.google.common.collect.Lists;

import java.util.List;

import me.systemb.tv.view.R;

/**
 * Created by systembug on 5/7/16.
 */
public class SettingValues {

    public static List<String> onOff(Context context) {

        List<String> options = Lists.newArrayList();

        options.add(context.getString(R.string.action_turn_on));
        options.add(context.getString(R.string.action_turn_off));

        return options;
    }

    public static List<String> enableDisable(Context context) {

        List<String> options = Lists.newArrayList();

        options.add(context.getString(R.string.action_enable));
        options.add(context.getString(R.string.action_disable));

        return options;
    }

    public static List<String> showHide(Context context) {

        List<String> options = Lists.newArrayList();

        options.add(context.getString(R.string.action_show));
        options.add(context.getString(R.string.action_hide));

        return options;
    }


    public static List<String> timers(Context context, Integer[] values) {
        List<String> options = Lists.newArrayList();

        for(Integer value : values) {
            options.add(String.format("%d %s", value, context.getString(R.string.second)));
        }
        return options;
    }
}
