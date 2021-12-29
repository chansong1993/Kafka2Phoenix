package com.cmnit.gatherdata.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.sql.*;
import java.util.Properties;

public class PhoenixUtils implements Serializable {
    protected static final Logger logger = LoggerFactory.getLogger(PhoenixUtils.class);

    static {
        // 加载驱动
        try {
            Class.forName(ConfigurationManager.getProperty("phoenix.driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 根据默认配置创建连接
    public static Connection getconnection() throws SQLException {
        String jdbcurl = ConfigurationManager.getProperty("phoenix.url");
        Properties prop = new Properties();
        prop.setProperty("phoenix.schema.isNamespaceMappingEnabled", "true");
        System.out.println("创建Phoenix连接：" + jdbcurl);
        return DriverManager.getConnection(jdbcurl, prop);
    }

    // 根据指定url创建连接
    public static JdbcTemplate getPhoenixDs(String url) {
        logger.error("数据库连接url为：" + url);
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        try {
            basicDataSource.setDriverClassName(ConfigurationManager.getProperty("phoenix.driver"));
            basicDataSource.setMaxTotal(300);
            basicDataSource.setMinIdle(5);
            basicDataSource.setMaxWaitMillis(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new JdbcTemplate(basicDataSource);
    }

    /**
     * 查询数据
     *
     * @param sql         SQL语句
     * @param valuesField 注入字段数据
     * @param connect     连接
     * @return PreparedStatement
     */
    public static PreparedStatement queryHbaseByPhoenix(String sql, String valuesField, Connection connect) {
        String[] valuesFields = valuesField.split(",");
        PreparedStatement pstmt = null;
        String resultData;
        try {
            pstmt = connect.prepareStatement(sql);
            for (int i = 0; i < valuesFields.length; i++) {
                resultData = valuesFields[i];
                pstmt.setString(i + 1, resultData);
            }
            return pstmt;
        } catch (SQLException e) {
            e.printStackTrace();
            return pstmt;
        }
    }

    /**
     * 更新数据
     *
     * @param tableName   表名
     * @param updateField 更新字段
     * @param valuesField 更新字段数据
     */
    public static void replaceHbaseByPhoenix(String tableName, String updateField, String valuesField) {
        Connection connect = null;
        PreparedStatement pstmt = null;
        String[] updateFields = updateField.split(",");
        String[] valuesFields = valuesField.split(",");
        String resultData;
        try {
            // 获取连接对象
            connect = PhoenixUtils.getconnection();
            connect.setAutoCommit(false);
            String sql = "upsert into " + tableName + "(" + updateField + ") values(";
            for (int j = 1; j < updateFields.length; j++) {
                sql = sql + "?,";
            }
            sql = sql + "?)";
            // System.out.println(sql);
            System.out.println(valuesField);
            pstmt = connect.prepareStatement(sql);
            for (int k = 0; k < updateFields.length; k++) {
                resultData = valuesFields[k];
                pstmt.setString(k + 1, resultData);
                logger.info("值：" + resultData);
            }
            pstmt.executeUpdate();
            connect.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PhoenixUtils.close(connect, pstmt, null);
        }
    }

    // 关闭连接
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
