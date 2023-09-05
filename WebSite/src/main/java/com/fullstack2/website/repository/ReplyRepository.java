package com.fullstack2.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack2.website.entity.Member;
import com.fullstack2.website.entity.Reply;



public interface ReplyRepository extends JpaRepository<Reply, Long> {

	//이건 Select이 아니야! Up, Del 둘 중의 하나를 수행할거야 라는 어노테이션을 반드시 선언해야 함.
	@Modifying
	@Query("Delete from Reply r where r.board.bno = :bno")
	public void deleteByBno(@Param("bno") Long bno);
	
	//List 페이지에 댓글들 가져오는 목록 처리하기
	//쿼리 메서드를 이용한 board 에 참여한 댓글을 get
	List<Reply> getRepliesByBoardOrderByRno(Member member);//컴파일 에러 없애려고 임시로 선언함
}
