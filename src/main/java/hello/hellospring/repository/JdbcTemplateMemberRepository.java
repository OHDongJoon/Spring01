package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * class          : JdbcTemplateMemberRepository
 * author         : 오동준
 * date           : 2023/05/18
 * description    : JdbcTemplate을 사용한 회원 저장소
 */

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * method         : save
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 회원 저장
     */
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    /**
     * method         : findById
     * author         : 오동준
     * date           : 2023/05/18
     * description    : id로 회원 찾기
     */
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper() , id);
        return result.stream().findAny();
    }

    /**
     * method         : findByName
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 이름으로 회원 찾기
     */
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    /**
     * method         : findAll
     * author         : 오동준
     * date           : 2023/05/18
     * description    : 모든 회원 찾기
     */

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());    }

  /**
   * method         : memberRowMapper
   * author         : 오동준
   * date           : 2023/05/18
   * description    : 회원 매핑
   */

    private RowMapper<Member> memberRowMapper(){

        return (rs, rowNum) -> {

            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };

    }
}
