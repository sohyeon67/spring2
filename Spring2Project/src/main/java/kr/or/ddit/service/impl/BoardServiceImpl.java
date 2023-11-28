package kr.or.ddit.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.IBoardMapper;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;

@Service
public class BoardServiceImpl implements IBoardService {

	@Inject
	private IBoardMapper mapper;
	
	@Override
	public void register(Board board) {
		mapper.create(board);
	}

}
