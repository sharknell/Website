package com.fullstack2.webSite.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fullstack2.webSite.dtos.ReplyDTO;
import com.fullstack2.webSite.entity.QnABoard;
import com.fullstack2.webSite.entity.Reply;
import com.fullstack2.webSite.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private final ReplyRepository replyRepository;
	
	@Override
	public Long register(ReplyDTO dto) {
		Reply reply = dtoToEntity(dto);
		replyRepository.save(reply);
		return reply.getRno();
	}

	@Override
	public List<ReplyDTO> getList(Long bno) {
		List<Reply> result = replyRepository.
				getRepliesByQnaBoardOrderByRno(QnABoard.builder()
						.bno(bno)
						.build());
		return result
				.stream()
				.map(reply -> entityToDTO(reply))
				.collect(Collectors.toList());
	}

	@Override
	public void modify(ReplyDTO dto) {
		Reply reply = dtoToEntity(dto);
		replyRepository.save(reply);
	}

	@Override
	public void remove(Long rno) {
		replyRepository.deleteById(rno);		
	}
}
