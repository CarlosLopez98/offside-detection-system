package org.example.model.stage;

import org.example.mapper.Mapper;
import org.example.mapper.MapperRegistry;
import org.example.model.drawable.Drawable;
import org.example.model.element.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Stage {
    protected String name;
    protected String message;
    protected final List<Element> elements = new ArrayList<>();
    protected final List<Drawable> drawableElements = new ArrayList<>();
    protected boolean visible = true;
    protected boolean completed = false;

    public Stage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void addElement(Element e) {
        elements.add(e);
    }

    public void addElements(List<Element> e) {
        elements.addAll(e);
    }

    public List<Drawable> getDrawableElements() {
        drawableElements.clear();

        for (Element element: elements) {
            Mapper mapper = MapperRegistry.getMapper(element.getClass());
            Drawable drawable = (Drawable) mapper.toDrawable(element);
            if (drawable != null)
                drawableElements.add(drawable);
        }
        return drawableElements;
    }

    public void addDrawableElement(Drawable d) {
        drawableElements.add(d);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public abstract boolean isCompleted();

    public <T extends Element> List<T> getElementsByType(Class<T> type) {
        return elements.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }
}
