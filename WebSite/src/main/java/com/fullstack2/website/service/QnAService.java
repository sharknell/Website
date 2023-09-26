package com.fullstack2.webSite.service;

import com.fullstack2.webSite.dtos.QnADTO;
import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.dtos.QnAPageResultDTO;
import com.fullstack2.webSite.entity.QnA;

public interface QnAService {

	//신규 리뷰 등록
	Long qna(QnADTO dto);
	
	//리뷰란에서 페이지에 해당하는 글 목록 조회 리스트 get
	QnAPageResultDTO<QnADTO, QnA> getList(QnAPageRequestDTO pageRequestDTO);
	
	//위 Object[] Entity를 DTO로 변환
	default QnADTO entityToDTO(QnA qna) {
		
		QnADTO dto = QnADTO.builder()
				.qno(qna.getQno())
				.questioner(qna.getQuestioner())
				.text(qna.getText())
				.regDate(qna.getRegDate())
				.modDate(qna.getModDate())
				.build();
		
		return dto;
	}
	
	//DTO를 Entity로 변환
	default QnA dtoToEntity(QnADTO dto) {
		
		QnA qna = QnA.builder()
				.qno(dto.getQno())
				.questioner(dto.getQuestioner())
				.text(dto.getText())
				.build();
		return qna;
	}
}
