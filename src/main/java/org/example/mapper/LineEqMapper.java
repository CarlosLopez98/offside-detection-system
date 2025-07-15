package org.example.mapper;

import org.example.controller.ViewportController;
import org.example.model.Point;
import org.example.model.drawable.DrawableLine;
import org.example.model.element.LineEq;

public class LineEqMapper implements Mapper<LineEq, DrawableLine> {
    private final ViewportController vp;

    public LineEqMapper(ViewportController vp) {
        this.vp = vp;
    }

    @Override
    public DrawableLine toDrawable(LineEq e) {
        Point topleft = vp.toWorld(0, 0);
        Point bottomright = vp.toWorld(vp.getWidth(), vp.getHeight());

        e.delimit(topleft.x(), bottomright.x(), topleft.y(), bottomright.y());
        if (e.getStart() != null && e.getEnd() != null) {
            return new DrawableLine(
                    vp.toScreen(e.getStart()),
                    vp.toScreen(e.getEnd()),
                    e.getColor()
            );
        }
        return null;
    }

    @Override
    public LineEq toElement(DrawableLine d) {
        return new LineEq(
                vp.toWorld(d.getStart()),
                vp.toWorld(d.getEnd())
        );
    }
}
