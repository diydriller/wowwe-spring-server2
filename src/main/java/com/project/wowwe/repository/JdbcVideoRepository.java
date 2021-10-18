package com.project.wowwe.repository;

import com.project.wowwe.model.Video;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.project.wowwe.util.DButil.dateTimeOf;


@Repository
public class JdbcVideoRepository implements VideoRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcVideoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Video> findById(Long id) {
        List<Video> videoList=jdbcTemplate
                .query("SELECT * FROM video WHERE id=?",mapper,id);
        return Optional.ofNullable(videoList.isEmpty()?null:videoList.get(0));
    }

    @Override
    public int save(Video video) {
        return jdbcTemplate.update("INSERT INTO video(title, description, thumnail_img, duration) VALUES(?,?,?,?,?,?)",
                video.getTitle(),video.getDescription(),video.getThumnailImg(),video.getDuration());
    }



    static RowMapper<Video> mapper = (rs, rowNum) ->
            Video.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .description(rs.getString("description"))
                    .thumnailImg(rs.getString("thumnail_img"))
                    .duration(rs.getDouble("duration"))
                    .createdAt(dateTimeOf(rs.getTimestamp("created_at")))
                    .build();


}
