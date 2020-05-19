package com.ja.freeboard.mapper;

import java.util.*;
import com.ja.freeboard.vo.*;

public interface UploadFileSQLMapper {

	public void insert(UploadFileVo uploadFileVo);
	
	public List<UploadFileVo> selectByBoardNo(int board_no);
	
	public List<UploadFileVo> selectByFileNo(int file_no);
}