package kr.or.ddit.vo.crud;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeVO {
	private int boNo;
	private String boTitle;
	private String boContent;
	private String boWriter;
	private String boDate;
	private int boHit;
	
	private Integer[] delNoticeNo;
	private MultipartFile[] boFile;
	private List<NoticeFileVO> noticeFileList;
	
	public void setBoFile(MultipartFile[] boFile) {	// 여러 개의 파일 데이터가 boFile에 들어있다.
		this.boFile = boFile;
		if(boFile != null) {
			List<NoticeFileVO> noticeFileList = new ArrayList<NoticeFileVO>();
			for(MultipartFile item : boFile) {	// 넘긴 파일의 개수만큼 돌림
				if(StringUtils.isBlank(item.getOriginalFilename())) {
					continue;
				}
				
				NoticeFileVO noticeFileVO = new NoticeFileVO(item);	// 필드 재정의
				noticeFileList.add(noticeFileVO);
			}
			this.noticeFileList = noticeFileList;
		}
	}
}
