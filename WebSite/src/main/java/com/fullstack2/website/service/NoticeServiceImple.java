package com.fullstack2.webSite.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fullstack2.webSite.dtos.NoticeDTO;
import com.fullstack2.webSite.dtos.NoticePageRequestDTO;
import com.fullstack2.webSite.dtos.NoticePageResultDTO;
import com.fullstack2.webSite.entity.Notice;
import com.fullstack2.webSite.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImple implements NoticeService {

	private final NoticeRepository noticeRepository;
	
	@Override
	public Long notice(NoticeDTO dto) {
		Notice notice = dtoToEntity(dto);
		noticeRepository.save(notice);
		return notice.getNno();		
	}

	@Override
	public NoticePageResultDTO<NoticeDTO, Notice> getList(NoticePageRequestDTO pageRequestDTO) {
		
		Page<Notice> result = noticeRepository.getNoticePaged(pageRequestDTO.getPageable(Sort.by("nno").descending()));
		return new NoticePageResultDTO<>(result, this::entityToDTO);
	}

}
