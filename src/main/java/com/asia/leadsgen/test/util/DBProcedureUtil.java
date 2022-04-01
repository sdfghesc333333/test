package com.asia.leadsgen.test.util;

import oracle.jdbc.OracleTypes;
import oracle.sql.TIMESTAMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DBProcedureUtil {

    @Autowired
    DataSource dataSource;

    public Map execute(String procedureCallStr, Map<Integer, Object> inputParams,
                              Map<Integer, Integer> outputParamsTypes, Map<Integer, String> outputParamsName) throws SQLException {
        Map resultMap = new LinkedHashMap<>();
        try (
                Connection connection = dataSource.getConnection();
                CallableStatement callableStatement = connection.prepareCall(procedureCallStr);
        ){

            if (inputParams != null && !inputParams.isEmpty()) {

                for (Map.Entry<Integer, Object> entry : inputParams.entrySet()) {

                    int paramIndex = entry.getKey();

                    Object paramValue = entry.getValue();

                    if (paramValue instanceof Integer) {

                        callableStatement.setInt(paramIndex, GetterUtil.getInteger(paramValue));

                    } else if (paramValue instanceof Long) {

                        callableStatement.setLong(paramIndex, GetterUtil.getLong(paramValue.toString()));

                    } else if (paramValue instanceof Float) {

                        callableStatement.setFloat(paramIndex, GetterUtil.getFloat(paramValue));

                    } else if (paramValue instanceof Double) {

                        callableStatement.setDouble(paramIndex, GetterUtil.getDouble(paramValue.toString()));

                    } else if (paramValue instanceof Boolean) {

                        callableStatement.setBoolean(paramIndex, GetterUtil.getBoolean(paramValue.toString()));

                    } else if (paramValue instanceof String) {

                        //                    if (paramValue.toString().length() > 4000) {
                        //
                        //                        OracleClob oracleClob = (OracleClob) connection.createClob();
                        //
                        //                        oracleClob.setString(1, paramValue.toString());
                        //
                        //                        callableStatement.setClob(paramIndex, oracleClob);
                        //
                        //                    } else {
                        callableStatement.setString(paramIndex, paramValue.toString());
                        //                    }
                    } else if (paramValue instanceof Timestamp) {

                        callableStatement.setTimestamp(paramIndex, (Timestamp) paramValue);

                    } else if (paramValue instanceof java.util.Date) {

                        callableStatement.setDate(paramIndex, new Date(((java.util.Date) paramValue).getTime()));

                    } else {

                        callableStatement.setNull(paramIndex, OracleTypes.NULL);
                    }
                }
            }

            if (outputParamsTypes != null && outputParamsTypes.size() > 0) {

                for (Map.Entry<Integer, Integer> entry : outputParamsTypes.entrySet()) {

                    int paramIndex = entry.getKey();
                    int paramValue = entry.getValue();

                    callableStatement.registerOutParameter(paramIndex, paramValue);
                }
            }

            callableStatement.execute();

            for (Map.Entry<Integer, Integer> entry : outputParamsTypes.entrySet()) {

                int paramIndex = entry.getKey();

                String paramName = (outputParamsName != null && !outputParamsName.isEmpty() && outputParamsName.get(paramIndex) != null)
                        ? outputParamsName.get(paramIndex).toString() : String.valueOf(paramIndex);

                int paramType = entry.getValue();

                Object paramValue = callableStatement.getObject(paramIndex);

                if (paramType == OracleTypes.CURSOR) {

                    try(ResultSet resultSet = (ResultSet) callableStatement.getObject(paramIndex)){

                        if (resultSet != null) {

                            List<Map<String, Object>> modelList = new ArrayList<>();

                            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                            while (resultSet.next()) {

                                Map<String, Object> modelInfoMap = new LinkedHashMap<>();

                                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {

                                    Object value = resultSet.getObject(i);

                                    if (value instanceof TIMESTAMP) {
                                        value = new java.util.Date(((TIMESTAMP) value).timestampValue().getTime());
                                    }

                                    if (value instanceof Clob) {
                                        Clob clob = resultSet.getClob(i);
                                        value = clob.getSubString(1, (int) clob.length());
                                        clob.free();
                                    }

                                    modelInfoMap.put(resultSetMetaData.getColumnName(i), value);
                                }

                                modelList.add(modelInfoMap);
                            }

                            resultMap.put(paramName, modelList);
                        }
                    }

                } else {

                    resultMap.put(paramName, paramValue);
                }
            }
        }

        return resultMap;
    }
}
