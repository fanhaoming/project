package com.project.core.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service("dictionaryService")
public class DictionaryService {

	DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	@Autowired
	public DictionaryService(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
	}

	public DictionaryItem getById(int id) {
		String sql = "select * from sys_dictionary_item where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new DictionaryRowMapper());
	}

	public List<DictionaryItem> listByDomainId(int domainId) {
		String sql = "select * from sys_dictionary_item where domain_id=?";
		return jdbcTemplate.query(sql, new Object[] { domainId }, new DictionaryRowMapper());
	}

	public List<DictionaryItem> listByDictTypeId(int dictTypeId) {
		String sql = "select * from sys_dictionary_item where dictionary_type_id=?";
		return jdbcTemplate.query(sql, new Object[] { dictTypeId }, new DictionaryRowMapper());
	}

	public List<DictionaryItem> listAll() {
		String sql = "select * from sys_dictionary_item";
		return jdbcTemplate.query(sql, new Object[] {}, new DictionaryRowMapper());
	}
	
	

	public DictionaryItem update(DictionaryItem dictionaryItem) {
		String updateSql = "update sys_dictionary_item set name=?,code=?,is_enable=? where id=?";
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, dictionaryItem.getName());
			ps.setObject(2, dictionaryItem.getCode());
			ps.setObject(3, dictionaryItem.getIsEnable());
			ps.setObject(4, dictionaryItem.getId());
			return ps;
		});

		DictionaryLoader.getInstance().clearCache();
		return dictionaryItem;
	}

	public DictionaryItem save(DictionaryItem dictionaryItem) {
		if (dictionaryItem.getId() == null || dictionaryItem.getId() == 0) {
			String insertSql = "insert into sys_dictionary_item (name,code,parent_id,dictionary_type_id,domain_id,is_enable) values (?,?,?,?,?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				ps.setObject(1, dictionaryItem.getName());
				ps.setObject(2, dictionaryItem.getCode());
				ps.setObject(3, dictionaryItem.getParentId());
				ps.setObject(4, dictionaryItem.getDictionaryTypeId());
				ps.setObject(5, dictionaryItem.getDomainId());
				ps.setObject(6, dictionaryItem.getIsEnable());
				return ps;
			}, keyHolder);
			dictionaryItem.setId(keyHolder.getKey().intValue());
		} else {
			String insertSql = "insert into sys_dictionary_item (id,name,code,parent_id,dictionary_type_id,domain_id,is_enable) values (?,?,?,?,?,?,?)";
			jdbcTemplate.update(insertSql,
					new Object[] { dictionaryItem.getId(), dictionaryItem.getName(), dictionaryItem.getCode(), dictionaryItem.getParentId(), dictionaryItem.getDictionaryTypeId(),
							dictionaryItem.getDomainId(), dictionaryItem.getIsEnable(), });
		}

		DictionaryLoader.getInstance().clearCache();
		return dictionaryItem;
	}

	class DictionaryRowMapper implements RowMapper<DictionaryItem> {

		@Override
		public DictionaryItem mapRow(ResultSet rs, int num) throws SQLException {
			// 从结果集里把数据得到
			DictionaryItem dictionaryItem = new DictionaryItem();
			dictionaryItem.setId(rs.getInt("id"));
			dictionaryItem.setName(rs.getString("name"));
			dictionaryItem.setCode(rs.getString("code"));
			dictionaryItem.setParentId(rs.getInt("parent_id"));
			dictionaryItem.setDictionaryTypeId(rs.getInt("dictionary_type_id"));
			dictionaryItem.setDomainId(rs.getInt("domain_id"));
			dictionaryItem.setIsEnable(rs.getBoolean("is_enable"));
			return dictionaryItem;
		}
	}
}
