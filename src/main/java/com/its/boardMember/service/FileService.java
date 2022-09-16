package com.its.boardMember.service;

import com.its.boardMember.util.CommonUtil;
import com.its.boardMember.dto.FileDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {

	private static final String TEMP_DIR = "temp";
	private static final String UPLOAD_DIR = "D:/upload/gym";

	public List<FileDTO> saveFile(FileDTO param, Map<String, MultipartFile> fileMap) {
		String tempDir = this.createBaseSaveDir(param);
		List<FileDTO> fileList = new ArrayList<FileDTO>();
		FileDTO fileDTO = null;
		MultipartFile mpf = null;
		String localFileName = null;
		String fileExtsn = null;
		String originalFileName = null;

		for (Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
			mpf = entry.getValue();
			fileExtsn = FilenameUtils.getExtension(mpf.getOriginalFilename());
			localFileName = UUID.randomUUID().toString() + "." + fileExtsn;
			originalFileName = CommonUtil.nullToString(mpf.getOriginalFilename());

			fileDTO = new FileDTO(); //
			fileDTO.setName(CommonUtil.replace(CommonUtil.replace(originalFileName, "'", "`"), "\"'", "＂"));
			fileDTO.setFileName(localFileName);// 파일이름
			fileDTO.setType(mpf.getContentType()); // 이미지냐 동영상이냐
			fileDTO.setSize(mpf.getSize()); //파일크기
			fileDTO.setUrl(tempDir.replaceAll("\\\\", "/")); //경로
			fileDTO.setFileExtsn(fileExtsn); //확장자

			try {
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(this.getBaseDir() + tempDir + File.separator + localFileName));
				fileList.add(fileDTO);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileList;
	}

	private String createBaseSaveDir(FileDTO param){
		StringBuffer tempdirecotry = new StringBuffer();
		tempdirecotry.append(File.separator).append(TEMP_DIR);
		tempdirecotry.append(File.separator).append(param.getModule());
		tempdirecotry.append(File.separator).append(param.getUploadType());
		tempdirecotry.append(File.separator).append(CommonUtil.getCurrentYearAsString());
		tempdirecotry.append(File.separator).append(CommonUtil.getCurrentMonthAsString());
		tempdirecotry.append(File.separator).append(CommonUtil.getCurrentDayAsString());

		File dir = new File(this.getBaseDir()+tempdirecotry.toString());
		if(!dir.exists()){
			dir.mkdirs();
		}
		return tempdirecotry.toString();
	}

	public String moveFileToModuleDir(String fileUrl, String fileName) {
		if ("\\".equals(File.separator)) {
			fileUrl = fileUrl.replace("/", "\\");
		}
		String realFileDir = fileUrl.replaceAll("\\" + File.separator + TEMP_DIR, "");
		if (fileUrl.equals(realFileDir)) {
			return realFileDir.replace("\\", "/");
		}
		File dir = new File(this.getBaseDir() + realFileDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		move(this.getBaseDir() + fileUrl + File.separator + fileName, this.getBaseDir() + realFileDir + File.separator + fileName);

		return realFileDir.replace("\\", "/");
	}

	public static boolean move(String source, String target) {
        File sourceFile = new File(source);
        File targetFile = new File(target);
        return move(sourceFile, targetFile);
    }

    public static boolean move(File source, File target) {
        boolean sucess = false;
        if (!source.exists()) {
            return false;
        }
        if (target.exists() && target.isDirectory()) {
            if (copy(source, target)) {
                sucess = delete(source);
            }
        }
        else {
            if (target.exists()) {
                delete(target);
            }
            sucess = source.renameTo(target);
            if (!sucess) {
                copy(source, target);
                source.delete();
            }
        }
        return sucess;
    }

    public static boolean copy(String source, String target) {
    	File sourceFile = new File(source);
        File targetFile = new File(target);
        return copy(sourceFile, targetFile);
    }

    public static boolean copy(File source, File target) {
        boolean sucess = false;
        if (source.exists()) {
            if (source.isDirectory()) {
                target.mkdir();
                File[] list = source.listFiles();
                for (int i = 0; i < list.length; ++i) {
                    File tempFile = new File(String.valueOf(target.getPath()) + File.separator + list[i].getName());
                    sucess = copy(list[i], tempFile);
                    if (!sucess) {
                        break;
                    }
                }
                sucess = target.exists();
            }
            else {
                sucess = copyFile(source, target);
            }
        }
        return sucess;
    }

    public static boolean copyFile(File source, File target) {
        if (!source.exists() || !source.isFile()) {
            return false;
        }
        byte[] buffer = new byte[4096];
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(source));
            output = new BufferedOutputStream(new FileOutputStream(target));
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            if (input != null) {
                try {
                    input.close();
                }
                catch (Exception e2) {
                }
            }
            if (output != null) {
                try {
                    output.close();
                }
                catch (Exception e2) {
                }
                return target.exists();
            }
            return target.exists();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (Exception e2) {
                }
            }
            if (output != null) {
                try {
                    output.close();
                }
                catch (Exception e2) {
                }
            }
        }
        if (input != null) {
            try {
                input.close();
            }
            catch (Exception e2) {
            }
        }
        if (output != null) {
            try {
                output.close();
            }
            catch (Exception e2) {
            }
        }
        return target.exists();
    }

    public static boolean delete(String source) {
        File sourceFile = new File(UPLOAD_DIR + source);
        return delete(sourceFile);
    }

    public static boolean delete(File source) {
        boolean sucess = false;
        if (source.exists()) {
            if (source.isDirectory()) {
                File[] list = source.listFiles();
                for (int i = 0; i < list.length; ++i) {
                    File tempFile = new File(String.valueOf(source.getPath()) + File.separator + list[i].getName());
                    sucess = delete(tempFile);
                    if (!sucess) {
                        break;
                    }
                }
                sucess = source.delete();
            }
            else {
                sucess = source.delete();
            }
        }
        return sucess;
    }

	public String getBaseDir(){
		return CommonUtil.defaultString(UPLOAD_DIR, System.getProperty("java.io.tmpdir"));
	}
}
