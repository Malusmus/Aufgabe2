/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.dis2011.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author aossa
 */
public abstract class ADatabaseElement {

    public static <T extends ADatabaseElement> List<T> loadAll(Class<T> clazz, String tableName, Map<String, Object> parameters) {
        List<T> result = new ArrayList<T>();
        PreparedStatement pstmt = null;
        ResultSet res = null;

        try {
            Connection con = DB2ConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * "
                    + " FROM " + tableName;

            if (parameters != null && parameters.size() > 0) {
                selectSQL += " WHERE 1=1";

                for (Entry<String, Object> parameter : parameters.entrySet()) {
                    selectSQL += " AND " + parameter.getKey() + " = ?";
                }
            }

            pstmt = con.prepareStatement(selectSQL);

            if (parameters != null && parameters.size() > 0) {
                int index = 0;

                for (Entry<String, Object> parameter : parameters.entrySet()) {
                    pstmt.setObject(index, parameter.getValue());
                    index++;
                }
            }

            // Führe Anfrage aus
            res = pstmt.executeQuery();
            while (res.next()) {
                try {
                    T newElement = clazz.newInstance();
                    newElement.fill(res);
                    result.add(newElement);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (res != null) {
                    res.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public void create() {
        PreparedStatement pstmt = null;

        try {
            Connection con = DB2ConnectionManager.getInstance().getConnection();
            Map<String, Object> values = getIdsAndValues();

            String sql = "INSERT INTO " + getTableNameUpdate();
            String sqlValueNames = "";
            String sqlValues = "";

            for (Entry<String, Object> entry : values.entrySet()) {
                if (sqlValueNames.length() > 0) {
                    sqlValueNames += ", ";
                    sqlValues += ", ";
                }

                sqlValueNames += entry.getKey();
                sqlValues += "?";
            }

            pstmt = con.prepareStatement(sql + " (" + sqlValueNames + ") VALUES (" + sqlValues + ")");

            int index = 0;

            for (Entry<String, Object> value : values.entrySet()) {
                pstmt.setObject(index, value.getValue());
                index++;
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        PreparedStatement pstmt = null;

        try {
            Connection con = DB2ConnectionManager.getInstance().getConnection();
            Map<String, Object> ids = getIds();
            Map<String, Object> values = getValues();

            String sql = "UPDATE " + getTableNameUpdate();
            String sqlUpdate = "";
            String sqlWhere = "1=1";

            for (Entry<String, Object> entry : values.entrySet()) {
                if (sqlUpdate.length() > 0) {
                    sqlUpdate += ", ";
                }

                sqlUpdate += entry.getKey() + " = ?";
            }

            for (Entry<String, Object> entry : ids.entrySet()) {
                sqlWhere += "AND " + entry.getKey() + " = ?";
            }

            pstmt = con.prepareStatement(sql + " SET " + sqlUpdate + " WHERE " + sqlWhere);

            int index = 0;

            for (Entry<String, Object> value : values.entrySet()) {
                pstmt.setObject(index, value.getValue());
                index++;
            }

            for (Entry<String, Object> value : ids.entrySet()) {
                pstmt.setObject(index, value.getValue());
                index++;
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete() {
        PreparedStatement pstmt = null;

        try {
            Connection con = DB2ConnectionManager.getInstance().getConnection();
            Map<String, Object> ids = getIds();

            String sql = "DELETE FROM " + getTableNameUpdate()+ " WHERE 1=1";

            for (Entry<String, Object> entry : ids.entrySet()) {
                sql += "AND " + entry.getKey() + " = ?";
            }

            pstmt = con.prepareStatement(sql);

            int index = 0;

            for (Entry<String, Object> value : ids.entrySet()) {
                pstmt.setObject(index, value.getValue());
                index++;
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected Map<String, Object> getIdsAndValues() {
        Map<String, Object> result = getIds();

        if (result == null) {
            result = new HashMap<String, Object>();
        }

        result.putAll(getValues());

        return result;
    }

    public String getTableNameRead() {
        return getTableNameUpdate();
    }

    protected abstract Map<String, Object> getIds();

    protected abstract Map<String, Object> getValues();

    public abstract String getTableNameUpdate();

    protected abstract void fill(ResultSet res) throws SQLException;
}
