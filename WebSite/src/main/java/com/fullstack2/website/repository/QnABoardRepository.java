package com.fullstack2.webSite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack2.webSite.entity.QnABoard;

public interface QnABoardRepository extends JpaRepository<QnABoard, Long> {

	//DB로부터 QnABoard Entity를 얻어내는 쿼리
	@Query("SELECT b, w FROM QnABoard b INNER JOIN b.writerEmail w WHERE b.bno = :bno")
	QnABoard getQnABoardWithWriter(@Param("bno") long bno);
	
	//QnA질문에 따른 관리자의 답변댓글을 조인해서 가져옴
	@Query("SELECT b, r FROM QnABoard b LEFT JOIN Reply r ON r.qnaBoard = b WHERE b.bno = :bno")
	List<Object[]> getQnABoardWithReply(@Param("bno") long bno);
	
	@Query("SELECT b, w, COUNT(r) FROM QnABoard b LEFT JOIN b.writerEmail w LEFT JOIN Reply r ON r.qnaBoard = b GROUP BY b")
	Page<Object[]> getQnABoardWithReplyCount(Pageable pageable);
	
	//특정 게시글에 댓글이 몇 개 존재하는지의 여부 쿼리 작성
	@Query("SELECT b, w, COUNT(r) FROM QnABoard b LEFT JOIN b.writerEmail w LEFT JOIN Reply r ON r.qnaBoard = b WHERE b.bno = :bno")
	Object getQnABoardByBno(@Param("bno") long bno);
	
	@Query("SELECT b.password FROM QnABoard b WHERE b.bno = :bno")
	String findPasswordByBno(@Param("bno") long bno);
}
