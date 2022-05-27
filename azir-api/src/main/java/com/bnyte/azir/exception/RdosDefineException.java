package com.bnyte.azir.exception;

import com.bnyte.azir.common.web.response.Code;

/**
 * @author bnyte
 * @since 2022/5/27 17:47
 */
public class RdosDefineException extends RuntimeException {

    private Code code;

    public RdosDefineException(Code code) {
        super(code.getMessage());
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
