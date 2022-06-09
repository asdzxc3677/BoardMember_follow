package com.its.boardMember.repository;

import com.its.boardMember.dto.CommentDTO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    private SqlSessionTemplate sql;

    public List<CommentDTO> findAll(Long boardId) {
        return sql.selectList("Comment.findAll",boardId);
    }

    public void save(CommentDTO commentDTO) {
        sql.insert("Comment.save",commentDTO);
    }

    public List<CommentDTO> delete(Long boardId) {
        return sql.selectList("Comment.delete",boardId);
    }
}
