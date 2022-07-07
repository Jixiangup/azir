package com.bnyte.azir.common.exception;

import com.bnyte.azir.common.web.response.Code;

/**
 * @author bnyte
 * @since 1.0.0
 */
public class RdosDefineException extends RuntimeException {

    private Code code;

    public RdosDefineException(Code code) {
        super(code.getMessage());
        this.code = code;
    }

    public RdosDefineException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
