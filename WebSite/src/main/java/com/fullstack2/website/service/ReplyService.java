package com.fullstack2.webSite.service;

import java.util.List;

import com.fullstack2.webSite.dtos.ReplyDTO;
import com.fullstack2.webSite.entity.QnABoard;
import com.fullstack2.webSite.entity.Reply;


public interface ReplyService {

	//댓글에 관련 필요한 메서드 일단 선언
	
	//댓글 등록
	Long register(ReplyDTO dto);
	
	//특정 게시글에 할당된 댓글 목록을 가져오는 메서드 선언
	List<ReplyDTO> getList(Long bno);
	
	//댓글 수정하기 기능
	void modify(ReplyDTO dto);
	
	//댓글 삭제
	void remove(Long rno);
	
	
	//DTO --> Entity
	default Reply dtoToEntity(ReplyDTO dto) {
		//글 번호가 필요하니 Board Entity 참조
		QnABoard board = QnABoard.builder()
				.bno(dto.getBno())
				.build();
		
		Reply reply = Reply.builder()
				.rno(dto.getRno())
				.text(dto.getText())
				.replyer(dto.getReplyer())
				.qnaBoard(board)
				.build();
		return reply;
	}
	
	//Entity --> DTO
	default ReplyDTO entityToDTO(Reply reply) {
		
		ReplyDTO dto = ReplyDTO.builder()
				.rno(reply.getRno())
				.text(reply.getText())
				.replyer(reply.getReplyer())
				.regDate(reply.getRegDate())
				.modDate(reply.getModDate())
				.build();
		return dto;
	}
}
