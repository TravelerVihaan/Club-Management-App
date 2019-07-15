package com.github.travelervihaan.clubmanagement.repository.workdiagram;

import com.github.travelervihaan.clubmanagement.model.workdiagram.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
