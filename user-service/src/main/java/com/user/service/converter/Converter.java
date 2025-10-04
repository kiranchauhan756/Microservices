package com.user.service.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public Object convert(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
