package org.apache.shardingsphere.sql.parser.binder.segment.table;

import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.sql.parser.binder.metadata.schema.SchemaMetaData;
import org.apache.shardingsphere.sql.parser.binder.segment.select.projection.impl.ColumnProjection;
import org.apache.shardingsphere.sql.parser.sql.segment.dml.column.ColumnSegment;
import org.apache.shardingsphere.sql.parser.sql.segment.generic.table.SimpleTableSegment;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;

/**
 * @ClassName Bin
 * @Description
 * @Author wzj
 * @Date 2021/7/14 16:42
 **/

@RequiredArgsConstructor
public final class TablesContext {

    private final Collection<SimpleTableSegment> tables;

    public TablesContext(final SimpleTableSegment tableSegment) {
        this(null == tableSegment ? Collections.emptyList() : Collections.singletonList(tableSegment));
    }

    /**
     * Get table names.
     *
     * @return table names
     */
    public Collection<String> getTableNames() {
        Collection<String> result = new LinkedHashSet<>(tables.size(), 1);
        for (SimpleTableSegment each : tables) {
            result.add(each.getTableName().getIdentifier().getValue());
        }
        return result;
    }

    /**
     * Find table name.
     *
     * @param column column segment
     * @param schemaMetaData schema meta data
     * @return table name
     */
    public Optional<String> findTableName(final ColumnSegment column, final SchemaMetaData schemaMetaData) {
        if (1 == tables.size()) {
            return Optional.of(tables.iterator().next().getTableName().getIdentifier().getValue());
        }
        if (column.getOwner().isPresent()) {
            return Optional.of(findTableNameFromSQL(column.getOwner().get().getIdentifier().getValue()));
        }
        return findTableNameFromMetaData(column.getIdentifier().getValue(), schemaMetaData);
    }

    /**
     * Find table name.
     *
     * @param column column projection
     * @param schemaMetaData schema meta data
     * @return table name
     */
    public Optional<String> findTableName(final ColumnProjection column, final SchemaMetaData schemaMetaData) {
        if (1 == tables.size()) {
            return Optional.of(tables.iterator().next().getTableName().getIdentifier().getValue());
        }
        if (null != column.getOwner()) {
            return Optional.of(findTableNameFromSQL(column.getOwner()));
        }
        return findTableNameFromMetaData(column.getName(), schemaMetaData);
    }

    private String findTableNameFromSQL(final String tableNameOrAlias) {
        for (SimpleTableSegment each : tables) {
            if (tableNameOrAlias.equalsIgnoreCase(each.getTableName().getIdentifier().getValue()) || tableNameOrAlias.equals(each.getAlias().orElse(null))) {
                return each.getTableName().getIdentifier().getValue();
            }
        }
        throw new IllegalStateException("Can not find owner from table.");
    }

    private Optional<String> findTableNameFromMetaData(final String columnName, final SchemaMetaData schemaMetaData) {
        for (SimpleTableSegment each : tables) {
            if (schemaMetaData.containsColumn(each.getTableName().getIdentifier().getValue(), columnName)) {
                return Optional.of(each.getTableName().getIdentifier().getValue());
            }
        }
        return Optional.empty();
    }
}

