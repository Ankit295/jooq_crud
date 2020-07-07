package jooqcrud.dao;

import org.example.tables.Users;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    private static String url="jdbc:postgresql://localhost:5432/jooq_first",user="nomad",pas="postgres";
    private  Connection connection;
    DSLContext dslContext;
    private static Logger logger = LoggerFactory.getLogger(UserDao.class);
        public UserDao(){
            try {
                connection = DriverManager.getConnection(url);
                dslContext = DSL.using(connection, SQLDialect.POSTGRES);
            }
            catch (Exception e){
                logger.debug("Exception ",e);
                e.printStackTrace();
            }
        }
    public List<org.example.tables.pojos.Users> getUsers() {
        Users users =new org.example.tables.Users();
        List<org.example.tables.pojos.Users> usersList= new ArrayList<org.example.tables.pojos.Users>();
        for (Record record: dslContext.select(users.USERS.AGE,users.USERS.NAME,users.USERS.ID).from(users.USERS).fetch()){
            String name = (String) record.getValue("name");
            Integer age= (Integer) record.getValue("age");
            Integer id;
            id= (Integer) record.getValue("id");
            org.example.tables.pojos.Users u =new org.example.tables.pojos.Users();
            u.setAge(age);
            u.setName(name);
            u.setId(id);
            usersList.add(u);
        }
        return usersList;
    }
    public String insertUser(org.example.tables.pojos.Users usersVo) {
        Users users =new Users();
        List<org.example.tables.pojos.Users> usersList= new ArrayList<org.example.tables.pojos.Users>();
        dslContext.insertInto(new Users().USERS).columns(users.AGE,users.NAME).values(usersVo.getAge(),usersVo.getName()).execute();
        return "Success";
    }
}
