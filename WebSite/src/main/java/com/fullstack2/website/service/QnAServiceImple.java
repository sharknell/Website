package com.fullstack2.webSite.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.dtos.QnADTO;
import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.dtos.QnAPageResultDTO;
import com.fullstack2.webSite.entity.QnA;
import com.fullstack2.webSite.repository.QnARepository;
import com.fullstack2.webSite.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnAServiceImple implements QnAService {

	private final QnARepository qnaRepository;
	
	@Override
	public Long qna(QnADTO dto) {
		QnA qna = dtoToEntity(dto);
		qnaRepository.save(qna);
		return qna.getQno();
	}

	@Override
	public QnAPageResultDTO<QnADTO, QnA> getList(QnAPageRequestDTO pageRequestDTO) {
		// Page<Review> 타입으로 반환하도록 수정
        Page<QnA> result = qnaRepository.getQnAPaged(pageRequestDTO.getPageable(Sort.by("qno").descending()));
        return new QnAPageResultDTO<>(result, this::entityToDTO);
	}

}
