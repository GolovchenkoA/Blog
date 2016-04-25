package com.company.dao;


import com.company.model.User;
import com.company.utils.ConnectionUtil;

import java.sql.*;

public class UserDao implements IUserDao {

    private static UserDao userDao = new UserDao();
    /**
     * Статический метод для использования в классе PostDTO
     * @param  id  это user id
     * @return      обїект пользователя
     */
    public static User getUser(Long id){
        return userDao.get(id);
    }

    @Override
    public User get(Long id) {

        User user = new User(); //пустой пользователь. что бы метод возвращал пользователя даже если в БД ничего не нашел
        try (Connection con = ConnectionUtil.createConnection()) {

            PreparedStatement statement = con.prepareStatement("SELECT * FROM USERS WHERE user_id =?");
            //PreparedStatement statement = con.prepareStatement("SELECT * FROM ACCOUNTS WHERE customerid =?");
            statement.setLong(1, id);

            System.out.println("Find user by id: " + statement.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                user =  extractUser(resultSet); // если пользователь найден в БД.
            }
        } catch (SQLException e){
            System.out.println("Ошибка в классе UserDao. Method findByLogin ");
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByLogin(String login){

        User user = new User();
        // Если пользователя нет в базе - уходит в безсконечный цикл
        try(Connection con = ConnectionUtil.createConnection()){

                PreparedStatement statement = con.prepareStatement("SELECT * FROM USERS WHERE login =?");
                //PreparedStatement statement = con.prepareStatement("SELECT * FROM ACCOUNTS WHERE customerid =?");
                statement.setString(1, login);
                System.out.println("Find user by login: " + statement.toString());
                ResultSet resultSet = statement.executeQuery();

                if(resultSet.next()){
                    user = extractUser(resultSet);
                }
        }catch (SQLException e){
            System.out.println("Ошибка в классе UserDao. Method findByLogin ");
            e.printStackTrace();

        }

        return user;
    }

    @Override
    public Long save(User user){

        Long generatedID = 0L; // дефолтное значение если сохранение в БД произойдет с ошибкой
        try(Connection con = ConnectionUtil.createConnection()){

                String query = "INSERT INTO USERS (login, password, username) VALUES (?,?,?)";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                //PreparedStatement statement= con.prepareStatement(query), Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getUsername());

                System.out.println("Query by save in User Class: " + statement.toString());
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();

            while (resultSet.next()) {
                generatedID = resultSet.getLong(1);
            }

        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Исключение в классе User. Method save()");
        }

        return generatedID;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {

            User user = new User();

            user.setId(resultSet.getLong("user_id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setUsername(resultSet.getString("username"));

        return user;
    }



    /*    return new User(
                resultSet.getLong("id"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getString("username"));
    }*/
}
