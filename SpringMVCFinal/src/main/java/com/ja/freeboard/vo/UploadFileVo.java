package com.ja.freeboard.vo;

import java.util.*;

public class UploadFileVo {
	private int file_no;
	private int board_no;
	private String file_link_path;
	private String file_real_path;
	private Date board_upload_date;
	
	public UploadFileVo() {
		super();
	}

	public UploadFileVo(int file_no, int board_no, String file_link_path, String file_real_path,
			Date board_upload_date) {
		super();
		this.file_no = file_no;
		this.board_no = board_no;
		this.file_link_path = file_link_path;
		this.file_real_path = file_real_path;
		this.board_upload_date = board_upload_date;
	}

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getFile_link_path() {
		return file_link_path;
	}

	public void setFile_link_path(String file_link_path) {
		this.file_link_path = file_link_path;
	}

	public String getFile_real_path() {
		return file_real_path;
	}

	public void setFile_real_path(String file_real_path) {
		this.file_real_path = file_real_path;
	}

	public Date getBoard_upload_date() {
		return board_upload_date;
	}

	public void setBoard_upload_date(Date board_upload_date) {
		this.board_upload_date = board_upload_date;
	}
}