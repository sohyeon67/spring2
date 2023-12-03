package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.test.TagBoardVO;

public interface ITagService {

	public ServiceResult insertTag(TagBoardVO tagboardVO);
	public TagBoardVO selectTagBoard(int boNo);
	public ServiceResult deleteTag(int boNo);
	public ServiceResult updateTagBoard(TagBoardVO tagBoardVO);
	public List<TagBoardVO> selectBoardList(TagBoardVO tagBoardVO);

}
