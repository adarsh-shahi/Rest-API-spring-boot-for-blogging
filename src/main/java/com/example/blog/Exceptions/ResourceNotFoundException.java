package com.example.blog.Exceptions;

public class ResourceNotFoundException extends RuntimeException{


    String resourceName;
    String fieldName;
    long filedValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long filedValue) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, filedValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.filedValue = filedValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getFiledValue() {
        return filedValue;
    }

    public void setFiledValue(Long filedValue) {
        this.filedValue = filedValue;
    }
}
