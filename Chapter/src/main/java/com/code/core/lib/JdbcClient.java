package com.code.core.lib;


import com.alibaba.fastjson.JSONObject;
import com.code.core.tool.BigDecimalUtils;
import com.code.core.tool.DataMap;
import com.code.core.tool.FunctionResult;
import com.code.core.tool.QaException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Create with IntelliJ IDEA
 * Author: jianhua.zhou
 * Created_at: 2021/3/1 14:03
 */
public class JdbcClient {


    public BasicDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private BasicDataSource dataSource;

    public JdbcClient(String jdbcUrl, String user, String password) {
        this.dataSource = new BasicDataSource();
        this.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        this.dataSource.setUrl(jdbcUrl);
        this.dataSource.setUsername(user);
        this.dataSource.setPassword(password);
        this.dataSource.setMaxActive(8);
        this.dataSource.setMinIdle(1);
        this.dataSource.setLogAbandoned(true);
    }
    protected void finalize()
    {
        closeJdbc();
    }
    public void closeJdbc()
    {
        try
        {
            this.dataSource.close();
        }
        catch (Exception e)
        {

        }

    }

    public JdbcClient(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return this.dataSource.getConnection();
    }

    public void stopConnection() throws SQLException, ClassNotFoundException {
        DbUtils.closeQuietly(this.getConnection());

    }
    public JSONObject toJsonObj(Map<String, String> map, JSONObject resultJson) {
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            resultJson.put(key, map.get(key));
        }
        return resultJson;
    }

    public List<JSONObject> fetchRowsForJson(String sql) throws QaException, ClassNotFoundException {

        List<JSONObject> result = new ArrayList<JSONObject>();
        Connection con = null;
        Statement statement = null;
        System.out.println("进入sql-----"+sql);

        try {
            con = this.getConnection();
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            List<String> columnNames =new ArrayList<String>();

            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            while (rs.next()) {
                JSONObject recordMap = new JSONObject();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String r =  rs.getString(metaData.getColumnName(i));
                    if (metaData.getColumnType(i) == 93
                            || metaData.getColumnType(i) == 91
                    ){
                        Timestamp d = rs.getTimestamp(i);
                        if (d != null){
                            r = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .format(d);
                        }
                    }
                    else if (metaData.getColumnType(i) == 2){
                        r = BigDecimalUtils.toStringFlexibleScale(rs.getBigDecimal(metaData.getColumnName(i)));
                    }
                    recordMap.put(metaData.getColumnName(i),r);
                }

                result.add(recordMap);
            }
            statement.close();
            con.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement!=null){statement.close(); }
                if(con!=null){con.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    //查询接口
    public List<DataMap> fetchRows(String sql) throws QaException, ClassNotFoundException {

        List<DataMap> result = new ArrayList<DataMap>();
        Connection con = null;
        Statement statement = null;

        try {
            con = this.getConnection();
            statement = con.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            List<String> columnNames =new ArrayList<String>();

            ResultSetMetaData metaData = rs.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            while (rs.next()) {
                DataMap recordMap = new DataMap();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String r =  rs.getString(metaData.getColumnName(i));
                    if (metaData.getColumnType(i) == 93
                            || metaData.getColumnType(i) == 91
                    ){
                        Timestamp d = rs.getTimestamp(i);
                        if (d != null){
                            r = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .format(d);
                        }
                    }
                    else if (metaData.getColumnType(i) == 2){
                        r = BigDecimalUtils.toStringFlexibleScale(rs.getBigDecimal(metaData.getColumnName(i)));
                    }
                    recordMap.put(metaData.getColumnName(i),r);
                }
                result.add(recordMap);
            }
            statement.close();
            con.close();
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement!=null){statement.close(); }
                if(con!=null){con.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    //更新 update add delete
    public FunctionResult executeUpdate(String sql) throws ClassNotFoundException
    {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = this.getConnection();
            statement = con.prepareStatement(sql);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return new FunctionResult(-2,sql+"\n"+e.toString());
        } catch (QaException e){
            return new FunctionResult(-3,e.getMessage());
        }
        finally {
            try {
                if(statement!=null){statement.close(); }
                if(con!=null){con.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  new FunctionResult(0,"Success");
    }


    public static void main(String[] args) throws Exception {


        String jdbcUrl="jdbc:mysql://10.128.38.210:3306/db_bill?zeroDateTimeBehavior=convertToNull";
        String user="hll_tester";
        String password="qay8fXC5YgwRO4FmCKIf";
        JdbcClient u_test = new JdbcClient(jdbcUrl, user, password);
        for(DataMap te:u_test.fetchRows("select * from t_bill_appeal"))
        {
            System.out.println(te);
        }
//        String sql ="DELETE FROM `qamp`.`tb_test_api` WHERE (`id` = '37');";
//        System.out.println(u_test.executeUpdate(sql).getMsg());
//        Connection my=u_test.getConnection();
//        Statement st = my.createStatement();
//        //4、执行sql语句
//        String sql="select * from tb_test_api";//查询user表的所有信息
//        ResultSet rs = st.executeQuery(sql);//查询之后返回结果集
//        //5、打印出结果
//        while(rs.next()) {
//            System.out.println(rs.getString("api_name"));
//        }
//        u_test.stopConnection();


    }


}
