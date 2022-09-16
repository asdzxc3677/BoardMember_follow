package com.its.boardMember.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {
	private String module;
	private String uploadType;
	private String realFileName;
	private String mimeType;
	private String fileUrl;
	private String fileName;
	private long size;
	private String limitedExtension;
	private boolean isThmbnailImg;
	private boolean isThmbnailImgTm;
	String clipboardImage;
	private String fileTmpId;
	private String dateTimeStr;

	private String name;
	private String type;
	private String url;
	private String error;
	private String fileExtsn;
}
