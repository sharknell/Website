package com.fullstack2.webSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack2.webSite.entity.Member;

import jakarta.transaction.Transactional;

public interface MemberQuery extends JpaRepository<Member, String> {
    
    
	@Query("SELECT m.email FROM Member m WHERE m.email = :email")
	void selectEmail(@Param("email") String email);
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET m.password = :newPassword WHERE m.password = :pw ")
	void updatePassword(@Param("newPassword") String newPassword ,@Param("pw") String pw);
	
	 // 회원탈퇴
	    @Transactional
	    @Modifying
	    @Query("DELETE FROM Member WHERE email = :email AND password = :pw")
	    void remove(@Param("pw") String pw, @Param("email") String email);
	    
	    
	    
	 // 배송지 변경
	    @Transactional
	    @Modifying
	    @Query("update Member m SET m.addressRest = :NewaddressRest WHERE password = :confirmPassword")
	    void updateAddressRest(@Param("NewaddressRest") String NewaddressRest,@Param("confirmPassword") String confirmPassword);
	    @Transactional
	    @Modifying
	    @Query("update Member m SET m.addressBasic = :NewaddressBasic  WHERE password = :confirmPassword")
	    void updateAddressBasic(@Param("NewaddressBasic") String NewaddressBasic,@Param("confirmPassword") String confirmPassword);
	    @Transactional
	    @Modifying
	    @Query("update Member m SET m.postalCode = :NewpostalCode WHERE password = :confirmPassword")
	    void updatepostalCode(@Param("NewpostalCode") String NewpostalCode,@Param("confirmPassword") String confirmPassword);
	    
	    
	 // 전화번호 변경
	    @Transactional
	    @Modifying
	    @Query("UPDATE Member m SET m.mobile = :newMobile WHERE m.password = :password")
	    void updateMobile(@Param("newMobile") String newMobile, @Param("password") String password);
	
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
  
    @Query("SELECT m.id FROM Member m WHERE m.email = :email")
    long selectId(@Param("email") String email);
}
