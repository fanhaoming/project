package com.project.core.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

@Service("dictionaryTypeService")
@Slf4j
public class DictionaryTypeService {

	DataSource dataSource;
	JdbcTemplate jdbcTemplate;

	@Autowired
	public DictionaryTypeService(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
	}

	public DictionaryType getById(int id) {
		String sql = "select * from sys_dictionary_type where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new DictionaryTypeRowMapper());
	}

	public List<DictionaryType> listByDomainId(int domainId) {
		String sql = "select * from sys_dictionary_type where domain_id=?";
		return jdbcTemplate.query(sql, new Object[] { domainId }, new DictionaryTypeRowMapper());
	}

	public List<DictionaryType> listAll() {
		String sql = "select * from sys_dictionary_type";
		return jdbcTemplate.query(sql, new Object[] {}, new DictionaryTypeRowMapper());
	}

	
	public List<DictionaryType> searchByCondition(DictTypeSearchCondition condition) {
		String sql = "select * from sys_dictionary_type where domain_id=" + condition.getDomainId();

		if (StringUtils.isBlank(condition.getName())) {
			sql += " and name like '%" + condition.getName() + "%'";
		}
		if (StringUtils.isBlank(condition.getCode())) {
			sql += " and code like '%" + condition.getCode() + "%'";
		}

		log.info("sql={}",sql);
		return jdbcTemplate.query(sql, new Object[] {}, new DictionaryTypeRowMapper());
	}

	public int countByCondition(DictTypeSearchCondition condition) {
		String sql = "select count(*) from sys_dictionary_type where domain_id=" + condition.getDomainId();
		if (StringUtils.isBlank(condition.getName())) {
			sql += " and name like '%" + condition.getName() + "%'";
		}
		if (StringUtils.isBlank(condition.getCode())) {
			sql += " and code like '%" + condition.getCode() + "%'";
		}
		return jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);
	}

	public DictionaryType update(DictionaryType dictionaryType) {
		String updateSql = "update sys_dictionary_type set name=?,code=?,is_enable=? where id=?";
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS);
			ps.setObject(1, dictionaryType.getName());
			ps.setObject(2, dictionaryType.getCode());
			ps.setObject(3, dictionaryType.getIsEnable());
			ps.setObject(4, dictionaryType.getId());
			return ps;
		});

		DictionaryLoader.getInstance().clearCache();
		return dictionaryType;
	}

	public DictionaryType save(DictionaryType dictionaryType) {
		if (dictionaryType.getId() == null || dictionaryType.getId() == 0) {
			String insertSql = "insert into sys_dictionary_type (name,code,is_tree,domain_id,is_enable) values (?,?,?,?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(con -> {
				PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				ps.setObject(1, dictionaryType.getName());
				ps.setObject(2, dictionaryType.getCode());
				ps.setObject(3, dictionaryType.getIsTree());
				ps.setObject(4, dictionaryType.getDomainId());
				ps.setObject(5, dictionaryType.getIsEnable());
				return ps;
			}, keyHolder);
			dictionaryType.setId(keyHolder.getKey().intValue());
		} else {
			String insertSql = "insert into sys_dictionary_type (id,name,code,is_tree,domain_id,is_enable) values (?,?,?,?,?,?)";
			jdbcTemplate.update(insertSql, new Object[] { dictionaryType.getId(), dictionaryType.getName(), dictionaryType.getCode(),
					dictionaryType.getIsTree(), dictionaryType.getDomainId(), dictionaryType.getIsEnable(), });
		}

		DictionaryLoader.getInstance().clearCache();

		return dictionaryType;
	}

	class DictionaryTypeRowMapper implements RowMapper<DictionaryType> {

		@Override
		public DictionaryType mapRow(ResultSet rs, int num) throws SQLException {
			// 从结果集里把数据得到
			DictionaryType dictionaryType = new DictionaryType();
			dictionaryType.setId(rs.getInt("id"));
			dictionaryType.setName(rs.getString("name"));
			dictionaryType.setCode(rs.getString("code"));
			dictionaryType.setIsTree(rs.getBoolean("is_tree"));
			dictionaryType.setDomainId(rs.getInt("domain_id"));
			dictionaryType.setIsEnable(rs.getBoolean("is_enable"));
			return dictionaryType;
		}
	}
}
