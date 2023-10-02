package com.fullstack2.webSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack2.webSite.entity.Member;

import jakarta.transaction.Transactional;

public interface MemberQuery extends JpaRepository<Member, String> {
    
    
	@Query("SELECT m.email FROM Member m WHERE m.email = :insertEmail")
	String selectEmail(@Param("insertEmail") String insertEmail);
	
	@Query("SELECT m.name FROM Member m WHERE m.name = :insertName")
	String selectNameByName(@Param("insertName") String insertName);
	
	@Query("SELECT m.mobile FROM Member m WHERE m.mobile= :insertMobile")
	String selectMobile(@Param("insertMobile") String insertMobile);
	
	@Query("SELECT m.email FROM Member m WHERE m.mobile= :insertMobile")
	String selectMobilebyEmail(@Param("insertMobile") String insertMobile);
	
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET m.password = :newPassword WHERE m.password = :pw ")
	void updatePassword(@Param("newPassword") String newPassword ,@Param("pw") String pw);
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET m.password = :newPassword WHERE m.email = :newEmail ")
	void updateUserPassword(@Param("newEmail") String newEmail ,@Param("newPassword") String newPassword);
	
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET m.mobile = :newMobile WHERE m.email = :Email ")
	void updateMobileByMobile(@Param("newMobile") String newMobile, @Param("Email") String Email);
	
	 // 회원탈퇴
	    @Transactional
	    @Modifying
	    @Query("DELETE FROM Member WHERE email = :email AND password = :pw")
	    void remove(@Param("pw") String pw, @Param("email") String email);
	    
	    
	    
	 // 배송지 변경
	    @Transactional
	    @Modifying
	    @Query("update Member m SET m.addressRest = :NewaddressRest where m.email= :email")
	    void updateAddressRest(@Param("NewaddressRest") String NewaddressRest,@Param("email") String email);
	    @Transactional
	    @Modifying
	    @Query("update Member m SET m.addressBasic = :NewaddressBasic  where m.email= :email")
	    void updateAddressBasic(@Param("NewaddressBasic") String NewaddressBasic,@Param("email") String email);
	    @Transactional
	    @Modifying
	    @Query("update Member m SET m.postalCode = :NewpostalCode  where m.email= :email")
	    void updatepostalCode(@Param("NewpostalCode") String NewpostalCode,@Param("email") String email);
	    
	    
	 // 전화번호 변경
	    @Transactional
	    @Modifying
	    @Query("UPDATE Member m SET m.mobile = :newMobile ")
	    void updateMobile(@Param("newMobile") String newMobile);
	
	// db 땡겨오기
	@Query("SELECT m.mobile FROM Member m WHERE m.email = :email ")
	String selectPh(@Param("email") String email);
	@Query("SELECT m.name FROM Member m WHERE m.email = :email" )
	String selectName (@Param("email") String email);
	@Query("SELECT m.birth FROM Member m WHERE m.email = :email ")
	String selectBirth(@Param("email") String email);
	@Query("SELECT m.password FROM Member m WHERE m.email = :email")
	String selectPw(@Param("email") String email);
	@Query("SELECT m.addressRest FROM Member m WHERE m.email = :email ")
	String selectaddressRest(@Param("email") String email);
	@Query("SELECT m.addressBasic FROM Member m WHERE m.email = :email ")
	String selectaddressBasic(@Param("email") String email);
	@Query("SELECT m.postalCode FROM Member m WHERE m.email = :email ")
	String selectpostalCode(@Param("email") String email);
	@Query("SELECT m.role FROM Member m WHERE m.email = :email ")
	String selectRole(@Param("email") String email);
	@Query("SELECT m.provider FROM Member m WHERE m.email = :email ")
	String selectProvider(@Param("email") String email);
  
    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    long selectId(@Param("email") String email);
}
