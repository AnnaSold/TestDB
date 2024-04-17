package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws SQLException {
        //example0();
        //example1();
        //example2();
        List<Triangle> triangles = getTriangles();
        int res = calculatePerTr(triangles);
        System.out.println(res);
    }

    private static void example2() throws SQLException {
        Connection conn = connectToDB();
        ArrayList<User> users = loadUsers(conn);
        System.out.println("получен список из "+users.size()+" юзеров");
        //users.forEach(System.out::println);

        conn.close();
    }

    public static ArrayList<User> loadUsers(Connection conn) throws SQLException {
        ArrayList<User> users=new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM public.user");
        while (rs.next()) {
            int x = rs.getInt("id");
            String fio = rs.getString("fio");
            double m = rs.getDouble("money");
            users.add(new User(x, fio, m));
        }
        rs.close();
        st.close();
        return users;
    }

    private static void example1() throws SQLException {
        Connection conn = connectToDB();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM public.user");
        while (rs.next()) {
            int x = rs.getInt("id");
            String fio = rs.getString("fio");
            double m = rs.getDouble("money");
            System.out.println("получена строка: "+x+" "+fio+" "+m);
        }
        rs.close();
        st.close();
        conn.close();
    }

    public static void example0(){
        String url = "jdbc:postgresql://10.10.104.136:5432/Geometry";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "123");
        props.setProperty("ssl", "false");
        try {
            Connection conn = DriverManager.getConnection(url, props);
            System.out.println("удалось подключиться к БД test4");
            conn.close();
        }
        catch (SQLException e){
            System.out.println("все пропало");
            e.printStackTrace();
        }
    }

    public static Connection connectToDB(){
        String url = "jdbc:postgresql://10.10.104.136:5432/test4";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "123");
        props.setProperty("ssl", "false");
        try {
            Connection conn = DriverManager.getConnection(url, props);
            return conn;
        }
        catch (SQLException e){
            System.out.println("все пропало");
            e.printStackTrace();
            return null;
        }
    }

    public static List<Triangle> getTriangles() throws SQLException {
        Connection conn = connectToDBTr();
        Statement st = conn.createStatement();
        List<Triangle> triangles = new ArrayList<>();
        ResultSet rs = st.executeQuery("SELECT * FROM public.\"Triangle\"");
        while (rs.next()) {
            int a = rs.getInt("a");
            int b = rs.getInt("b");
            int c = rs.getInt("c");
           // System.out.println("получены 3 стороны: "+a+" "+b+" "+c);
            triangles.add(new Triangle(a,b,c));
        }
        rs.close();
        st.close();
        conn.close();
        return triangles;
    }

public static Connection connectToDBTr(){
    String url = "jdbc:postgresql://10.10.104.136:5432/Geometry";
    Properties props = new Properties();
    props.setProperty("user", "postgres");
    props.setProperty("password", "123");
    props.setProperty("ssl", "false");
    try {
        Connection conn = DriverManager.getConnection(url, props);
        return conn;
    }
    catch (SQLException e){
        System.out.println("все пропало");
        e.printStackTrace();
        return null;
    }


}
public static int calculatePerTr(List<Triangle> triangles){
        int summa = 0;
        for (Triangle t:triangles){
            summa += t.a + t.b + t.c;
        }
        return summa;
}
}


