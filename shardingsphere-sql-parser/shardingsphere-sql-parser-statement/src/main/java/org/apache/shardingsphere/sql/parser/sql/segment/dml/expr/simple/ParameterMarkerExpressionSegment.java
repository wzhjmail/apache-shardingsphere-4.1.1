package org.apache.shardingsphere.sql.parser.sql.segment.dml.expr.simple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @ClassName ParameterMarkerExpressionSegment
 * @Description
 * @Author wzj
 * @Date 2021/7/16 08:32
 **/

@RequiredArgsConstructor
@Getter
@ToString
public class ParameterMarkerExpressionSegment implements SimpleExpressionSegment {

    private final int startIndex;

    private final int stopIndex;

    private final int parameterMarkerIndex;
}

