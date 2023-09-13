package com.fullstack2.webSite.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.dtos.QnABoardDTO;
import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.dtos.QnAPageResultDTO;
import com.fullstack2.webSite.entity.Member;
import com.fullstack2.webSite.entity.QnABoard;
import com.fullstack2.webSite.repository.QnABoardRepository;
import com.fullstack2.webSite.repository.ReplyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QnABoardServiceImple implements QnABoardService {

	private final QnABoardRepository qnaBoardRepository;
	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(QnABoardDTO dto) {
		QnABoard qnABoard = dtotoEntity(dto);
		qnaBoardRepository.save(qnABoard);
		return qnABoard.getBno();
	}

	@Override
	public QnAPageResultDTO<QnABoardDTO, Object[]> getList(QnAPageRequestDTO pageRequestDTO) {
		Function<Object[], QnABoardDTO> fn = (en -> entityToDTO((QnABoard)en[0], (Member)en[1], (Long)en[2]));
		
		Page<Object[]> result = qnaBoardRepository.getQnABoardWithReplyCount(pageRequestDTO.getPageable(Sort.by("bno").descending()));
		
		return new QnAPageResultDTO<>(result, fn);
	}

	@Override
	public QnABoardDTO get(Long bno) {
		Object result = qnaBoardRepository.getQnABoardByBno(bno);
		
		Object[] arr = (Object[])result;
		
		return entityToDTO((QnABoard)arr[0], (Member)arr[1], (Long)arr[2]);
	}
	

	//게시글 삭제 메서드 정의
	@Transactional //자식 삭제 후 부모 삭제를 수행하도록 선언 함. 안하면 하나 삭제 후 끝나버림.
	@Override
	public void removeWithReplyies(Long bno) {
		replyRepository.deleteByBno(bno);
		qnaBoardRepository.deleteById(bno);
	}
	
	@Transactional
	@Override
	public void modify(QnABoardDTO dto) {
		QnABoard board = qnaBoardRepository.getReferenceById(dto.getBno());
		
		board.changeContent(dto.getContent());
		board.changeTitle(dto.getTitle());
		
		qnaBoardRepository.save(board);
	}

	@Override
	public boolean checkPassword(Long bno, String password) {
		 // DB에서 해당 글의 비밀번호를 가져옵니다.
        String storedPassword = qnaBoardRepository.findPasswordByBno(bno);
        // 입력한 비밀번호와 DB의 비밀번호를 비교하여 일치 여부를 반환합니다.
        return password.equals(storedPassword);
	}
}
