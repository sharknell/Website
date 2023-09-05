package com.fullstack2.website.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;

/*
 * BaseEntity : 얘 자체가 Entity 가 되는 목적이 아니라 특정 속성을 상속해주는 목적으로
 * 선언되는 엔티티 입니다.
 * 추상으로 선언만 한다고 entity 가 안되는게 아니고, 반드시 @MappedSuperClass 라는
 * 선언을 해야만 비엔티티화 되어집니다.
 * 얘가 하는 일은 상속도 있지만, 메모리에 빈즈로 머물다가 엔티티에 변화가 생기면 즉각
 * 이를 하위 엔티티가 영속계층(DB 쪽) 반영하도록 특정 컬럼이나, 메서드를 보유합니다.
 * 지금은 날짜값을 자동으로 insert, ipdate 만 하도록 컬럼 설정을 하고, 어노테이션 선언합니다.
 * 그럼 하위 엔티티는 날짜와 관련된 부분은 DB 에 따로 작업을 하지 않아도 됩니다.
 * 단 updatable, insertable 이라는 속성을 false 로 줘야만 합니다.
 * 이건 어노테이션이 따로 존재 하니 거기서 설명할게요.
 */

@MappedSuperclass //이렇게 선언하면 이 엔티티는 테이블화 되지 않습니다.
//엔티티를 관리하는 컨텍스트에 이 엔티티의 컬럼값이 변화가 생겼는지를 관리하는 리스너 등록
//이렇게 하면 변경시 즉각 엔티티에 통보를 하게되고, 이 값을 하위 엔티티를 통해 DB 에 전달하도록 하는 역할
//주의!! 이 어노테이션이 활설화 되려면, 프로젝트의 메인클래스에 이 리스너를 등록하는 어노테이션(EnableJpaAuditing) 을 반드시 선언해야합니다.
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@Data
public abstract class BaseEntity {

	//방명록에 작성되는 글들의 날짜값을 자동 지정하도록 컬럼 설정함
	@CreatedDate//해당 필드 즉 컬럼에 날짜값 자동 반영하도록 선언
	@Column(name = "regDate", updatable = false)//DB에 regdate 컬럼 생성하도록 선언 및,
	//값이 처음 insert 이후엔 자동 update 불가 하도록 설정
	private LocalDateTime regDate;
	
	
	@CreatedDate
	@Column(name="modDate")//수정일 컬럼 설정.. 자동으로 insert, update 되어짐
	private LocalDateTime modDate;
}
