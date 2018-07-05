package com.jdj.movie.typesHandlers;

import com.jdj.movie.enums.StaticTypes;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by jiangdajun on 2018/7/5.
 */
public class MovieTypeHandler extends BaseTypeHandler<StaticTypes> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StaticTypes parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getCode());
    }

    @Override
    public StaticTypes getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return StaticTypes.valueOf(rs.getInt(columnName));
    }

    @Override
    public StaticTypes getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return StaticTypes.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public StaticTypes getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return StaticTypes.valueOf(cs.getInt(columnIndex));
    }
}
