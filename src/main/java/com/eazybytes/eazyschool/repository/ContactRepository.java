package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.ContactRowMapper;
import com.eazybytes.eazyschool.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactDetail(Contact contact)
    {
        String sql = "INSERT INTO CONTACT_MSG (NAME,MOBILE_NO,EMAIL,SUBJECT,MESSAGE,STATUS,CREATED_AT,CREATED_BY) VALUES " +
                "(?,?,?,?,?,?,?,?)";
        // return the number of records added (in this case 1);
        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),
                contact.getEmail(),contact.getSubject(),contact.getMessage(),
                contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
    }

    public List<Contact> findMessagesWithOpenStatus(String status) {

        String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
        String sql1 = "SELECT * FROM CONTACT_MSG";
        return jdbcTemplate.query(sql1, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,status);
            }
        },new ContactRowMapper());
    }

    public List<Contact> findMessagesWithOpenStatus1(String status) {

        String sql = "SELECT * FROM CONTACT_MSG WHERE STATUS = ?";
        String sql1 = "select * from contact_msg";
        return jdbcTemplate.query(sql1,new ContactRowMapper());
    }

    public int closeMessageById(int id, String closed) {

        String sql = "UPDATE CONTACT_MSG SET STATUS = ?, UPDATED_AT = ?, UPDATED_BY = ?, WHERE CONTACT_ID = ? ";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,closed);
                ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                ps.setString(3,"Prabh");
                ps.setInt(4,id);
            }
        });
    }
}
