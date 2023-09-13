package com.fullstack2.webSite.service;

import com.fullstack2.webSite.dtos.QnABoardDTO;
import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.dtos.QnAPageResultDTO;
import com.fullstack2.webSite.entity.Member;
import com.fullstack2.webSite.entity.QnABoard;

public interface QnABoardService {

	//신규 글 등록
	Long register(QnABoardDTO dto);
	
	//list 페이지에서 페이지에 해당하는 글 목록 조회 리스트 get메서드
	QnAPageResultDTO<QnABoardDTO, Object[]> getList(QnAPageRequestDTO pageRequestDTO);
	
	//QnA 특정 질문 게시물의 정보를 리턴
	QnABoardDTO get(Long bno);
	
	//특정 게시물 삭제 메서드 정의
	void removeWithReplyies(Long bno);
		
		
	//게시물 수정 메서드 선언
	void modify(QnABoardDTO dto);
	
	//위 Object[] Entity를 DTO 로 변환
	default QnABoardDTO entityToDTO(QnABoard qnaBoard, Member member, Long replyCount) {
		
		QnABoardDTO dto = QnABoardDTO.builder()
				.bno(qnaBoard.getBno())
				.title(qnaBoard.getTitle())
				.content(qnaBoard.getContent())
				.regDate(qnaBoard.getRegDate())
				.modDate(qnaBoard.getModDate())
				.password(qnaBoard.getPassword())
				.writerName(member.getName())
				.writerEmail(member.getEmail())
				.replyCount(replyCount.intValue())
				.build();
		
		return dto;
	}
	
	//DTO를 Entity로 변환
	default QnABoard dtotoEntity(QnABoardDTO dto) {
		Member member = Member.builder().email(dto.getWriterEmail()).name(dto.getWriterName()).build();
		
		QnABoard qnaBoard = QnABoard.builder()
				.bno(dto.getBno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.password(dto.getPassword())
				.writerEmail(member)
				.build();
		return qnaBoard;
	}

	boolean checkPassword(Long bno, String password);
}
