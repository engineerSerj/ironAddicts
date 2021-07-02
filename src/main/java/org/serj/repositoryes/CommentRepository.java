package org.serj.repositoryes;

import org.serj.domains.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    Iterable<Comment> findAllById(Integer id);

}
