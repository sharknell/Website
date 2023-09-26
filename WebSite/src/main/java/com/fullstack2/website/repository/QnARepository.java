package com.fullstack2.webSite.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack2.webSite.entity.QnA;
import com.fullstack2.webSite.entity.Review;

public interface QnARepository extends JpaRepository<QnA, Long> {

	@Query("SELECT q.text, q.questioner FROM QnA q WHERE q.qno = :qno")
	List<Object[]> getQnAInfo(@Param("qno") Long qno);
	
	@Query("SELECT q FROM QnA q") // JPQL 쿼리 수정: 엔티티 QnA를 반환하도록 변경
	Page<QnA> getQnAPaged(Pageable pageable);

}
