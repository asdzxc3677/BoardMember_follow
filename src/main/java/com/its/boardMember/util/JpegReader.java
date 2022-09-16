package com.its.boardMember.util;

import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.common.byteSources.ByteSourceFile;
import org.apache.sanselan.formats.jpeg.JpegImageParser;
import org.apache.sanselan.formats.jpeg.segments.UnknownSegment;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class JpegReader {
    public static final int COLOR_TYPE_RGB = 1;
    public static final int COLOR_TYPE_CMYK = 2;
    public static final int COLOR_TYPE_YCCK = 3;
    private int colorType;
    private boolean hasAdobeMarker;

    public JpegReader() {
        this.colorType = 1;
        this.hasAdobeMarker = false;
    }

    public BufferedImage readImage(final File file) throws IOException, ImageReadException {
        this.colorType = 1;
        this.hasAdobeMarker = false;
        ImageInputStream stream = ImageIO.createImageInputStream(file);
        Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
        if (iter.hasNext()) {
            ImageReader reader = iter.next();
            reader.setInput(stream);
            ICC_Profile profile = null;
            BufferedImage image;
            try {
                image = reader.read(0);
                reader.reset();
            }
            catch (IIOException e) {
                this.colorType = 2;
                this.checkAdobeMarker(file);
                profile = Sanselan.getICCProfile(file);
                WritableRaster raster = (WritableRaster)reader.readRaster(0, null);
                reader.reset();
                if (this.colorType == 3) {
                    convertYcckToCmyk(raster);
                }
                if (this.hasAdobeMarker) {
                    convertInvertedColors(raster);
                }
                image = convertCmykToRgb(raster, profile);
                image.flush();
                return image;
            }
            finally {
                stream.close();
            }
            stream.close();
            return image;
        }
        return null;
    }

    public void checkAdobeMarker(File file) throws IOException, ImageReadException {
        JpegImageParser parser = new JpegImageParser();
        ByteSource byteSource = (ByteSource)new ByteSourceFile(file);
        ArrayList segments = parser.readSegments(byteSource, new int[] { 65518 }, true);
        if (segments != null && segments.size() >= 1) {
            UnknownSegment app14Segment = (UnknownSegment) segments.get(0);
            byte[] data = app14Segment.bytes;
            if (data.length >= 12 && data[0] == 65 && data[1] == 100 && data[2] == 111 && data[3] == 98 && data[4] == 101) {
                this.hasAdobeMarker = true;
                final int transform = app14Segment.bytes[11] & 0xFF;
                if (transform == 2) {
                    this.colorType = 3;
                }
            }
        }
    }

    public static void convertYcckToCmyk(WritableRaster raster) {
        int height = raster.getHeight();
        int width = raster.getWidth();
        int stride = width * 4;
        int[] pixelRow = new int[stride];
        for (int h = 0; h < height; ++h) {
            raster.getPixels(0, h, width, 1, pixelRow);
            for (int x = 0; x < stride; x += 4) {
                int y = pixelRow[x];
                final int cb = pixelRow[x + 1];
                final int cr = pixelRow[x + 2];
                int c = (int)(y + 1.402 * cr - 178.956);
                int m = (int)(y - 0.34414 * cb - 0.71414 * cr + 135.95984);
                y = (int)(y + 1.772 * cb - 226.316);
                if (c < 0) {
                    c = 0;
                }
                else if (c > 255) {
                    c = 255;
                }
                if (m < 0) {
                    m = 0;
                }
                else if (m > 255) {
                    m = 255;
                }
                if (y < 0) {
                    y = 0;
                }
                else if (y > 255) {
                    y = 255;
                }
                pixelRow[x] = 255 - c;
                pixelRow[x + 1] = 255 - m;
                pixelRow[x + 2] = 255 - y;
            }
            raster.setPixels(0, h, width, 1, pixelRow);
        }
    }

    public static void convertInvertedColors(WritableRaster raster) {
        final int height = raster.getHeight();
        final int width = raster.getWidth();
        final int stride = width * 4;
        final int[] pixelRow = new int[stride];
        for (int h = 0; h < height; ++h) {
            raster.getPixels(0, h, width, 1, pixelRow);
            for (int x = 0; x < stride; ++x) {
                pixelRow[x] = 255 - pixelRow[x];
            }
            raster.setPixels(0, h, width, 1, pixelRow);
        }
    }

    public static BufferedImage convertCmykToRgb(Raster cmykRaster, ICC_Profile cmykProfile) throws IOException {
        if (cmykProfile == null) {
            cmykProfile = ICC_Profile.getInstance(JpegReader.class.getResourceAsStream("ISOcoated_v2_300_eci.icc"));
        }
        ICC_ColorSpace cmykCS = new ICC_ColorSpace(cmykProfile);
        BufferedImage rgbImage = new BufferedImage(cmykRaster.getWidth(), cmykRaster.getHeight(), 1);
        WritableRaster rgbRaster = rgbImage.getRaster();
        ColorSpace rgbCS = rgbImage.getColorModel().getColorSpace();
        ColorConvertOp cmykToRgb = new ColorConvertOp(cmykCS, rgbCS, null);
        cmykToRgb.filter(cmykRaster, rgbRaster);
        return rgbImage;
    }

    static void intToBigEndian(final int value, final byte[] array, final int index) {
        array[index] = (byte)(value >> 24);
        array[index + 1] = (byte)(value >> 16);
        array[index + 2] = (byte)(value >> 8);
        array[index + 3] = (byte)value;
    }
}