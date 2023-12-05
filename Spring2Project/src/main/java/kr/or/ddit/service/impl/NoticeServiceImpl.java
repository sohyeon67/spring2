package kr.or.ddit.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mapper.ILoginMapper;
import kr.or.ddit.mapper.INoticeMapper;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeFileVO;
import kr.or.ddit.vo.crud.NoticeMemberVO;
import kr.or.ddit.vo.crud.NoticeVO;
import kr.or.ddit.vo.crud.PaginationInfoVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Inject
	private INoticeMapper noticeMapper;
	
	@Inject
	private ILoginMapper loginMapper;

	@Override
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeCount(pagingVO);
	}

	@Override
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO) {
		return noticeMapper.selectNoticeList(pagingVO);
	}

	// 파일 업로드를 위해 HttpServletRequest req 매개변수 추가
	@Override
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO noticeVO) {
		ServiceResult result = null;
		
		int status = noticeMapper.insertNotice(noticeVO);
		if(status > 0) {	// 게시글 등록이 성공
			List<NoticeFileVO> noticeFileList = noticeVO.getNoticeFileList();
			try {
				noticeFileUpload(noticeFileList, noticeVO.getBoNo(), req);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public NoticeVO selectNotice(int boNo) {
		noticeMapper.incrementHit(boNo);	// 게시글 조회수 증가
		return noticeMapper.selectNotice(boNo);	// 게시글 번호에 해당하는 게시글 정보 가져오기
	}

	@Override
	public ServiceResult updateNotice(NoticeVO noticeVO) {
		ServiceResult result = null;
		int status = noticeMapper.updateNotice(noticeVO);
		if(status > 0) {	
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteNotice(int boNo) {
		ServiceResult result = null;
		int status = noticeMapper.deleteNotice(boNo);
		if(status > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	// noticemember --------------------------------------------------
	@Override
	public NoticeMemberVO loginCheck(NoticeMemberVO member) {
		return loginMapper.loginCheck(member);
	}

	@Override
	public ServiceResult idCheck(String memId) {
		ServiceResult result = null;
		NoticeMemberVO member = loginMapper.idCheck(memId);
		if(member != null) {
			result = ServiceResult.EXIST;
		} else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

	@Override
	public ServiceResult signup(HttpServletRequest req, NoticeMemberVO memberVO) {
		ServiceResult result = null;
		
		// 회원가입 시, 프로필 이미지로 파일을 업로드 하는데 이때 업로드 할 서버 경로
		String uploadPath = req.getServletContext().getRealPath("/resources/profile");
		File file = new File(uploadPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		String proFileImg = "";	// 회원정보에 추가될 프로필 이미지 경로
		try {
			// 넘겨받은 회원정보에서 파일 데이터 가져오기
			MultipartFile proFileImgFile = memberVO.getImgFile();
			
			// 넘겨받은 파일 데이터가 존재할 때
			if(proFileImgFile.getOriginalFilename() != null && !proFileImgFile.getOriginalFilename().equals("")) {
				String fileName = UUID.randomUUID().toString();		// UUID 파일명 생성
				fileName += "_" + proFileImgFile.getOriginalFilename();	// UUID_원본파일명으로 파일명 생성
				uploadPath += "/" + fileName;	// /resources/profile/uuid_원본파일명
				
				proFileImgFile.transferTo(new File(uploadPath));	// 해당 위치에 파일복사
				proFileImg = "/resources/profile/" + fileName;		// 파일 복사가 일어난 파일의 위치로 접근하기 위한 URI 설정
			}
			
			memberVO.setMemProfileImg(proFileImg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int status = loginMapper.signup(memberVO);
		if(status > 0) {	// 등록 성공
			result = ServiceResult.OK;
		} else {			// 등록 실패
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
	private void noticeFileUpload(
			List<NoticeFileVO> noticeFileList,
			int boNo, HttpServletRequest req
			) throws IOException {
		String savePath = "/resources/notice/";
		
		if(noticeFileList != null) {	// 넘겨받은 파일 데이터가 존재할 때
			if(noticeFileList.size() > 0) {
				for(NoticeFileVO noticeFileVO : noticeFileList) {
					String saveName = UUID.randomUUID().toString();	// UUID의 랜덤 파일명 생성
					
					// 파일명을 설정할 때, 원본 파일명의 공백을 '_'로 변경한다.
					saveName = saveName + "_" + noticeFileVO.getFileName().replaceAll(" ", "_");
					// 디버깅 및 확장자 추출 참고
					String endFilename = noticeFileVO.getFileName().split("\\.")[1];
					
					String saveLocate = req.getServletContext().getRealPath(savePath + boNo);
					File file = new File(saveLocate);
					if(!file.exists()) {
						file.mkdirs();
					}
					saveLocate += "/" + saveName;
					
					noticeFileVO.setBoNo(boNo);					// 게시글 번호 설정
					noticeFileVO.setFileSavepath(saveLocate);	// 파일 업로드 경로 설정
					noticeMapper.insertNoticeFile(noticeFileVO);	// 게시글 파일 데이터 추가
					
					// 방법 1
					File saveFile = new File(saveLocate);
//					InputStream is = noticeFileVO.getItem().getInputStream();
//					FileUtils.copyInputStreamToFile(is, saveFile);
					
					// 방법 2
					noticeFileVO.getItem().transferTo(saveFile);	// 파일 복사
					
				}
			}
		}
	}

}
