package org.example.mapper;

import org.example.controller.ViewportController;
import org.example.model.drawable.DrawableLine;
import org.example.model.element.Line;

public class LineMapper implements Mapper<Line, DrawableLine> {
    private final ViewportController vp;

    public LineMapper(ViewportController vp) {
        this.vp = vp;
    }

    @Override
    public DrawableLine toDrawable(Line e) {
        return new DrawableLine(
                vp.toScreen(e.getStart()),
                vp.toScreen(e.getEnd()),
                e.getColor()
        );
    }

    @Override
    public Line toElement(DrawableLine d) {
        return new Line(
                vp.toWorld(d.getStart()),
                vp.toWorld(d.getEnd()),
                d.getColor()
        );
    }
}
