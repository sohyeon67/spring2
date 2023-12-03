package kr.or.ddit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.mapper.ITagMapper;
import kr.or.ddit.service.ITagService;
import kr.or.ddit.vo.test.TagBoardVO;
import kr.or.ddit.vo.test.TagVO;

@Service
public class TagServiceImpl implements ITagService {

	@Inject
	private ITagMapper mapper;
	
	@Override
	public ServiceResult insertTag(TagBoardVO tagBoardVO) {
		ServiceResult result = null;
		
		int status = mapper.createBoard(tagBoardVO);	// 게시글 등록
		
		String tags = tagBoardVO.getTag();	// 태그를 입력하지 않으면 ""가 들어감
		if(!tags.equals("") && tags.length() > 0) {	// 조건 중요★★
			String[] tagArray = tagBoardVO.getTag().split(" ");
			List<TagVO> tagList = new ArrayList<TagVO>();
			for(int i = 0; i < tagArray.length; i++) {
				TagVO tagVO = new TagVO();				// 태그 하나
				tagVO.setBoNo(tagBoardVO.getBoNo());	// 게시글 등록 후 만들어진 최신 게시글 번호
				tagVO.setTagName(tagArray[i]);			// 태그 하나를 가져와서 세팅
				
				tagList.add(tagVO);
			}
			
			if(tagList != null && tagList.size() > 0) {	// tagList 유무로 tag 테이블 조작
				for(int i = 0; i < tagList.size(); i++) {
					mapper.createTag(tagList.get(i));	// 태그를 하나씩 등록
				}
			}
		}
		
		if(status > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		
		return result;
	}

	@Override
	public TagBoardVO selectTagBoard(int boNo) {
		mapper.incrementHit(boNo);	// 조회수 증가
		TagBoardVO tagBoardVO = mapper.selectBoard(boNo);	// 게시글 정보 가져오기
		List<TagVO> tagList = mapper.selectTag(boNo);		// 해당 게시글의 태그 정보 가져오기 (여러 행이 될 수 있음)
		if(tagList != null && tagList.size() > 0) {
			String tags = "";
			for(int i = 0; i < tagList.size(); i++) {
				String tag = tagList.get(i).getTagName();
				tags += tag + " ";	// 끝에 띄어쓰기 있어도 어차피 split으로 무시된다.
			}
			tagBoardVO.setTag(tags);
			tagBoardVO.setTagList(tagList);					// tagBoardVO에 다 세팅해서 넘겨주기
		}
		
		return tagBoardVO;
	}

	@Override
	public ServiceResult deleteTag(int boNo) {
		ServiceResult result = null;
		
		// 태그가 있을 시 tag 테이블에서 먼저 삭제해야 함★★★
		int status1 = mapper.deleteTag(boNo);	// 자식 테이블 먼저 삭제
		int status2 = mapper.deleteBoard(boNo);	// 자식 테이블이 있으면 삭제 불가
		
		if(status2 > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		
		return result;
	}

	@Override
	public ServiceResult updateTagBoard(TagBoardVO tagBoardVO) {
		ServiceResult result = null;
		
		// 게시글 수정 시 태그가 없어질 수도, 추가될 수도 있기 때문에 우선 태그를 전부 삭제 후 추가한다. (수정이 없다)
		// 1) 게시글을 수정
		int status = mapper.updateBoard(tagBoardVO);	// 게시글 수정
		
		// 2) 태그를 수정
		// 태그 정보를 모두 삭제
		int boNo = tagBoardVO.getBoNo();
		mapper.deleteTag(boNo);
		
		// 새로운 태그들을 insert
		String tags = tagBoardVO.getTag();	// 태그를 입력하지 않으면 ""가 들어감
		if(!tags.equals("") && tags.length() > 0) {	// 조건 중요★★
			String[] tagArray = tagBoardVO.getTag().split(" ");
			List<TagVO> tagList = new ArrayList<TagVO>();
			for(int i = 0; i < tagArray.length; i++) {
				TagVO tagVO = new TagVO();				// 태그 하나
				tagVO.setBoNo(tagBoardVO.getBoNo());	// 게시글 번호
				tagVO.setTagName(tagArray[i]);			// 태그 하나를 가져와서 세팅
				
				tagList.add(tagVO);
			}
			
			if(tagList != null && tagList.size() > 0) {	// tagList 유무로 tag 테이블 조작
				for(int i = 0; i < tagList.size(); i++) {
					mapper.createTag(tagList.get(i));	// 태그를 하나씩 등록
				}
			}
		}
		
		if(status > 0)
			result = ServiceResult.OK;
		else
			result = ServiceResult.FAILED;
		
		return result;
	}
	
	@Override
	public List<TagBoardVO> selectBoardList(TagBoardVO tagBoardVO) {
		// 태그 말고 게시판 목록만 가져오면 됨
		return mapper.selectBoardList(tagBoardVO);
	}


}
