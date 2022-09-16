package com.its.boardMember.util;

import org.apache.sanselan.ImageReadException;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

public class CommonUtil {

	public static final int INDEX_NOT_FOUND = -1;

	public static String getUniqueId(String text) {
		Random random = new Random();
		long temp = (long) (random.nextDouble() * 1000000000) + 999999999L;
		String retVal = text + String.valueOf(temp);
		return retVal;
    }

	public static String nullToString(String regex) {
		if (regex == null || "".equals(regex) || "null".equals(regex)) {
			return "";
		}
		return regex;
	}

    public static String defaultString(String str, String defaultStr) {
        return str == null ? defaultStr : str;
    }

    public static String replace(String text, String searchString, String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    public static String replace(String text, String searchString, String replacement, int max) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuilder buf = new StringBuilder(text.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String getCurrentYearAsString() {
        Calendar cd = new GregorianCalendar(Locale.KOREA);
        return lPad(Integer.toString(cd.get(Calendar.YEAR)), 4, '0');
    }

    public static String getCurrentMonthAsString() {
        Calendar cd = new GregorianCalendar(Locale.KOREA);
        return lPad(Integer.toString(cd.get(Calendar.MONTH) + 1), 2, '0');
    }

    public static String getCurrentDayAsString() {
        Calendar cd = new GregorianCalendar(Locale.KOREA);
        return lPad(Integer.toString(cd.get(Calendar.DAY_OF_MONTH)), 2, '0');
    }

    public static String lPad(String str, int len, char pad) {
        return lPad(str, len, pad, false);
    }

    public static String lPad(String str, int len, char pad, boolean isTrim) {
        if (isNull(str)) {
            return null;
        }
        if (isTrim) {
            str = str.trim();
        }
        for (int i = str.length(); i < len; i++) {
            str = pad + str;
        }
        return str;
    }

    public static boolean isNull(String str) {
        if (str != null) {
            str = str.trim();
        }
        return (str == null || "".equals(str));
    }

    public static int[] resizeImage(String orignalImage, String resizeImage, int width, int height, String resizeMode) throws IOException {
        Scalr.Mode mode = null;
        final int[] ret = new int[4];
        final File sourceImageFile = new File(orignalImage);
        BufferedImage image = null;
        try {
            image = ImageIO.read(sourceImageFile);
        }
        catch (IOException e) {
            try {
                JpegReader jpegReader = new JpegReader();
                image = jpegReader.readImage(sourceImageFile);
            }
            catch (ImageReadException e2) {
                throw new IOException("JPEG CMYK CONVERT EORROR");
            }
        }
        if ("auto".equals(resizeMode)) {
            mode = Scalr.Mode.AUTOMATIC;
        }
        else if ("width".equals(resizeMode)) {
            mode = Scalr.Mode.FIT_TO_WIDTH;
        }
        else if ("height".equals(resizeMode)) {
            mode = Scalr.Mode.FIT_TO_HEIGHT;
        }
        else if ("exact".equals(resizeMode)) {
            mode = Scalr.Mode.FIT_EXACT;
        }
        final BufferedImage thumbnail = Scalr.resize(image, Scalr.Method.BALANCED, mode, width, height, new BufferedImageOp[] { Scalr.OP_ANTIALIAS });
        ret[0] = thumbnail.getHeight();
        ret[1] = thumbnail.getWidth();
        ret[2] = image.getHeight();
        ret[3] = image.getWidth();
        thumbnail.createGraphics().drawImage(thumbnail, 0, 0, null);
        ImageIO.write(thumbnail, "png", new File(resizeImage));
        return ret;
    }

	public static String getURLPart(HttpServletRequest request) {
		StringBuilder urlString = new StringBuilder();
		try {
			Map pmap = request.getParameterMap();
			Iterator iterator = pmap.keySet().iterator();
			while(iterator.hasNext()) {
				String key = (String)iterator.next();
				String val = request.getParameter(key);
				String[] paramValues = request.getParameterValues(key);
				if(paramValues != null && paramValues.length > 1) {
					for (int i = 0; i < paramValues.length; i++) {
						if(!isEmpty(urlString.toString())){
							urlString.append("&");
						}
						urlString.append(key).append("=").append(URLEncoder.encode(paramValues[i],"utf-8"));
					}
				}else {
					if(!isEmpty(urlString.toString())){
						urlString.append("&");
					}
					urlString.append(key).append("=").append(URLEncoder.encode(val,"utf-8"));
				}
			}
			if(urlString.length() > 0) urlString.insert(0, "?");
		} catch (Exception e) {
			e.toString();
		}
		return urlString.toString();
	}//:
}
