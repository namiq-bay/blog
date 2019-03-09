package com.myproject.blog.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.myproject.blog.dao.UserRepository;
import com.myproject.blog.model.User;

public class UserRepositoryJdbcImpl implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// RowMapper interface-indən bir anonim class yaradırıq
	// Bu class-dan yaradılan obyekti rowMapper tipində bir dəyişənə assign edirik
	// Bu anonim classın içində mapRow methodunu implement edirik
	// mapRow - un içində bir User obyekti yaradıb onu qaytarırıq.
	// User obyektinin atributlarıda resultSet-dən user ilə əlaqəli əldə etdiyimiz
	// sütun dəyərlərindən populate ediləcək. Daha sonra bu rowMaper-ı finder
	// method-larında istifadə edəcəyik

	private RowMapper<User> rowMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			return user;
		}
	};

	@Override
	public List<User> findAll() {
		String sql = "SELECT id, first_name, last_name FROM t_user";
		return jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public User findById(Long id) {
		String sql = "SELECT id, first_name, last_name FROM t_user WHERE id = ?";
		return DataAccessUtils.singleResult(jdbcTemplate.query(sql, rowMapper, id));

	}

	@Override
	public List<User> findByLastName(String lastName) {
		String sql = "SELECT id, first_name, last_name FROM t_user WHERE last_name LIKE ?";
		return jdbcTemplate.query(sql, rowMapper, "%" + lastName + "%");
	}

	@Override
	public void create(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM t_user WHERE id = ?";
		jdbcTemplate.update(sql, id);
		

	}

}
