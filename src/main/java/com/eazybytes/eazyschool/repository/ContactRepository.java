package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.rowmappers.ContactRowMapper;
import com.eazybytes.eazyschool.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
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
        String sql = "insert into contact_msg (name,mobile_num,email,subject,message,status,created_at,created_by) values " +
                "(?,?,?,?,?,?,?,?)";
        // return the number of records added (in this case 1);
        return jdbcTemplate.update(sql,contact.getName(),contact.getMobileNum(),
                contact.getEmail(),contact.getSubject(),contact.getMessage(),
                contact.getStatus(),contact.getCreatedAt(),contact.getCreatedBy());
    }

    public List<Contact> findMessagesWithOpenStatus(String status) {

        String sql = "select * from contact_msg where status = ?";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,status);
            }
        },new ContactRowMapper());
    }

    public int closeMessageById(int id, String closed) {

        String sql = "update contact_msg set status = ?, updated_by = ?,updated_at =? where contact_id = ?";
        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,closed);
                ps.setString(2, "Prabh");
                ps.setTimestamp(3,Timestamp.valueOf(LocalDateTime.now()));
                ps.setInt(4,id);
            }
        });
    }
}
