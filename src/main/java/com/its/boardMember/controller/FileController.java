package com.its.boardMember.controller;

import com.its.boardMember.util.CommonUtil;
import com.its.boardMember.dto.FileDTO;
import com.its.boardMember.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/{sitemesh}/file", method = { RequestMethod.GET, RequestMethod.POST})
public class FileController {

	public static final String thumbnailT = "t_";
	public static final String thumbnailTM = "tm_";
	public static final String thumbnailMT = "mt_";


	private static final int THUMBNAIL_WIDTH = 60;
	private static final int THUMBNAIL_HEIGHT = 71;

	private static final int THUMBNAIL_WIDTH_M = 110;
	private static final int THUMBNAIL_HEIGHT_M = 130;

	@Autowired
	private FileService fileService;

	@RequestMapping(value="/upload")
	@ResponseBody
	public List<FileDTO> upload(MultipartHttpServletRequest request, HttpServletResponse response, FileDTO fileDTO) {
		Map<String, MultipartFile> fileMap = request.getFileMap();
		List<FileDTO> fileList = this.fileService.saveFile(fileDTO, fileMap);
		return fileList;
	}

	@RequestMapping(value="/fileView", method = { RequestMethod.GET, RequestMethod.POST})
	public void fileView(HttpServletRequest request, HttpServletResponse response, FileDTO fileDTO){
		try{
			InputStream in = null;
			String root = fileService.getBaseDir();
			String filePath = null;
			if(fileDTO.getFileName() != null) {
				filePath = fileDTO.getFileUrl() + File.separator + fileDTO.getFileName();
			}else {
				filePath = fileDTO.getFileUrl();
			}
			try {
				File file = null;
				if(fileDTO.isThmbnailImg()) {
					String thumbnailTPath = root + fileDTO.getFileUrl() + File.separator + thumbnailT + fileDTO.getFileName();
					file = new File(thumbnailTPath);
					if(!file.exists()) {
						File orgImgFile = new File(root+filePath);
						if(orgImgFile.exists()) {
							CommonUtil.resizeImage(root+filePath, thumbnailTPath, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT, "auto");
						}
					}
				}
				if(fileDTO.isThmbnailImgTm()) {
					String thumbnailTMPath = root+fileDTO.getFileUrl() + File.separator + thumbnailTM + fileDTO.getFileName();
					file = new File(thumbnailTMPath);
					if(!file.exists()) {
						File orgImgFile = new File(root+fileDTO.getFileUrl() + File.separator + fileDTO.getFileName());
						if(orgImgFile.exists()) {
							CommonUtil.resizeImage(root+filePath, thumbnailTMPath, THUMBNAIL_WIDTH_M, THUMBNAIL_HEIGHT_M, "auto");
						}
					}
				}
				if(file == null || !file.exists()) {
					file = new File(root+filePath);
				}
				in = new FileInputStream(file);
				response.getOutputStream().write(IOUtils.toByteArray(in));
			} catch (FileNotFoundException e) {
				response.sendError(HttpStatus.NOT_FOUND.value());
			} catch (RuntimeException e) {
			} finally {
				if(in != null) {
					in.close();
				}
			}
		}catch (RuntimeException | IOException e){
			e.printStackTrace();
		}
	}
}
