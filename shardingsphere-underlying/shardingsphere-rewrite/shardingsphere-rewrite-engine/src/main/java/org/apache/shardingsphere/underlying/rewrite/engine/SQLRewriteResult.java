package org.apache.shardingsphere.underlying.rewrite.engine;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @ClassName SQ
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:11
 **/

@RequiredArgsConstructor
@Getter
public final class SQLRewriteResult {

    private final String sql;

    private final List<Object> parameters;
}
