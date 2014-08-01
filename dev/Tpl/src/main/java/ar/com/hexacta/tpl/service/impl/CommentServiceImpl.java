package ar.com.hexacta.tpl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ar.com.hexacta.tpl.model.Comment;
import ar.com.hexacta.tpl.persistence.repository.CommentRepository;
import ar.com.hexacta.tpl.service.ICommentService;

public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    @Transactional
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

}
