package org.apache.shardingsphere.sql.parser.binder.metadata.table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.shardingsphere.sql.parser.binder.metadata.column.ColumnMetaData;
import org.apache.shardingsphere.sql.parser.binder.metadata.index.IndexMetaData;

import java.util.*;

/**
 * @ClassName TableMetaData
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:21
 **/

@Getter
@EqualsAndHashCode
@ToString
public final class TableMetaData {

    private final Map<String, ColumnMetaData> columns;

    private final Map<String, IndexMetaData> indexes;

    @Getter(AccessLevel.NONE)
    private final List<String> columnNames = new ArrayList<>();

    private final List<String> primaryKeyColumns = new ArrayList<>();

    public TableMetaData(final Collection<ColumnMetaData> columnMetaDataList, final Collection<IndexMetaData> indexMetaDataList) {
        columns = getColumns(columnMetaDataList);
        indexes = getIndexes(indexMetaDataList);
    }

    private Map<String, ColumnMetaData> getColumns(final Collection<ColumnMetaData> columnMetaDataList) {
        Map<String, ColumnMetaData> result = new LinkedHashMap<>(columnMetaDataList.size(), 1);
        for (ColumnMetaData each : columnMetaDataList) {
            String lowerColumnName = each.getName().toLowerCase();
            columnNames.add(lowerColumnName);
            result.put(lowerColumnName, each);
            if (each.isPrimaryKey()) {
                primaryKeyColumns.add(lowerColumnName);
            }
        }
        return Collections.synchronizedMap(result);
    }

    private Map<String, IndexMetaData> getIndexes(final Collection<IndexMetaData> indexMetaDataList) {
        Map<String, IndexMetaData> result = new LinkedHashMap<>(indexMetaDataList.size(), 1);
        for (IndexMetaData each : indexMetaDataList) {
            result.put(each.getName().toLowerCase(), each);
        }
        return Collections.synchronizedMap(result);
    }

    /**
     * Get column meta data.
     *
     * @param columnIndex column index
     * @return column meta data
     */
    public ColumnMetaData getColumnMetaData(final int columnIndex) {
        return columns.get(columnNames.get(columnIndex));
    }

    /**
     * Find index of column.
     *
     * @param columnName column name
     * @return index of column if found, otherwise -1
     */
    public int findColumnIndex(final String columnName) {
        for (int i = 0; i < columnNames.size(); i++) {
            if (columnNames.get(i).equals(columnName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Judge column whether primary key.
     *
     * @param columnIndex column index
     * @return true if the column is primary key, otherwise false
     */
    public boolean isPrimaryKey(final int columnIndex) {
        if (columnIndex >= columnNames.size()) {
            return false;
        }
        return columns.get(columnNames.get(columnIndex)).isPrimaryKey();
    }
}

