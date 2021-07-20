package org.apache.shardingsphere.sql.parser.binder.metadata.index;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @ClassName IndexMetaData
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:23
 **/

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class IndexMetaData {

    private final String name;
}
