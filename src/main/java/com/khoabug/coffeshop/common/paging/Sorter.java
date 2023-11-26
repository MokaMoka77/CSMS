package com.khoabug.coffeshop.common.paging;

import com.khoabug.coffeshop.common.services.RequestMappingService;
import jakarta.servlet.http.HttpServletRequest;

public class Sorter {

    private String property;
    private String direction;

    public Sorter(String property, String direction) {
        this.property = property;
        this.direction = direction;
    }

    public static Sorter of(HttpServletRequest request) {
        Sorter sorter = RequestMappingService.toModel(Sorter.class, request);
        return sorter;
    }

    public static Sorter by(Direction direction, String property) {
        return new Sorter(direction.name(), property);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public static enum Direction {
        ASC, DESC;
    }
}
