package com.vsu.visual.drawers;

import com.vsu.visual.VisualData;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public abstract class Drawer {
    VisualData data;

    public abstract void draw();
}
