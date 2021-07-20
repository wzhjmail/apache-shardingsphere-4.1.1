package org.apache.shardingsphere.sql.parser.exception;

/**
 * @ClassName SQLParserException
 * @Description
 * @Author wzj
 * @Date 2021/7/14 10:57
 **/

public final class SQLParsingException extends RuntimeException {

    private static final long serialVersionUID = -6408790652103666096L;

    public SQLParsingException(final String message, final Object... args) {
        super(String.format(message, args));
    }
}