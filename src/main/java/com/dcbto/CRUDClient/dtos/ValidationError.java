package com.dcbto.CRUDClient.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private List<FieldMessage> errrors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrrors() {
        return errrors;
    }

    public void addErrors(String fieldName, String message) {
        errrors.add(new FieldMessage(fieldName, message));
    }
}
