package org.apache.shardingsphere.sql.parser.binder.metadata.column;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @ClassName ColumnMetaData
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:22
 **/

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ColumnMetaData {

    private final String name;

    private final int dataType;

    private final String dataTypeName;

    private final boolean primaryKey;

    private final boolean generated;

    private final boolean caseSensitive;
}

