package com.fullstack2.webSite.service;

import com.fullstack2.webSite.dtos.NoticeDTO;
import com.fullstack2.webSite.dtos.NoticePageRequestDTO;
import com.fullstack2.webSite.dtos.NoticePageResultDTO;
import com.fullstack2.webSite.entity.Notice;

public interface NoticeService {

	
	Long notice(NoticeDTO dto);
	
	NoticePageResultDTO<NoticeDTO, Notice> getList(NoticePageRequestDTO pageRequestDTO);
	
	default Notice dtoToEntity(NoticeDTO dto) {
		
		Notice notice = Notice.builder()
				.nno(dto.getNno())
				.admin(dto.getAdmin())
				.title(dto.getTitle())
				.content(dto.getContent())
				.build();
		return notice;
	}
	
	default NoticeDTO entityToDTO(Notice notice) {
		
		NoticeDTO dto = NoticeDTO.builder()
				.nno(notice.getNno())
				.admin(notice.getAdmin())
				.title(notice.getTitle())
				.content(notice.getContent())
				.regDate(notice.getRegDate())
				.modDate(notice.getModDate())
				.build();
		
		return dto;
	}
}
