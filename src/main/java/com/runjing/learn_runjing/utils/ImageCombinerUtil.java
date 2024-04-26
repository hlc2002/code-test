package com.runjing.learn_runjing.utils;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.freewayso.image.combiner.ImageCombiner;
import com.freewayso.image.combiner.enums.OutputFormat;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author : spring
 * {@code @description: 图片合成与二维码工具类}
 * {@code @date:} 2024/4/26
 * {@code @modified:} spring
 * {@code @project:} learn_runjing
 */
public class ImageCombinerUtil {
    /**
     * @param qrCodeByteArray    二维码字节数组
     * @param image              图片
     * @param qrCodeLeftUpPointX 二维码左上角X坐标
     * @param qrCodeLeftUpPointY 二维码左上角Y坐标
     * @return java.io.InputStream
     * @description: 图片合成与二维码工具类
     * @author: spring
     * @date: 2024/4/26 16:04
     */
    public static InputStream combinerImageAndQrCodeStream(byte[] qrCodeByteArray, BufferedImage image,
                                                           Integer qrCodeLeftUpPointX, Integer qrCodeLeftUpPointY) {

        if (qrCodeByteArray == null || qrCodeByteArray.length == 0 || image == null || qrCodeLeftUpPointX == null || qrCodeLeftUpPointY == null) {
            throw new RuntimeException("usage this static method error: please check args !");
        }

        try {
            ByteArrayInputStream qrByteArrayInputStream = new ByteArrayInputStream(qrCodeByteArray);
            BufferedImage qrCodeImage = ImageIO.read(qrByteArrayInputStream);
            if (qrCodeImage == null) {
                throw new RuntimeException("generate qrCodeImage by byte array fail: qrCodeImage is null!");
            }

            ImageCombiner imageCombiner = new ImageCombiner(image, OutputFormat.PNG);
            if (qrCodeLeftUpPointX > image.getWidth() || qrCodeLeftUpPointY > image.getHeight()) {
                throw new RuntimeException("qrCodeLeftUpPointX or qrCodeLeftUpPointY is out of image size!");
            }
            imageCombiner.addImageElement(qrCodeImage, qrCodeLeftUpPointX, qrCodeLeftUpPointY);

            BufferedImage combine = imageCombiner.combine();
            if (combine == null) {
                throw new RuntimeException("image combine fail: combine is null!");
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(combine, "png", byteArrayOutputStream);

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("image combine error,error detail info:", e);
        }
    }

    /**
     * @param qrCodeShortUrl 二维码短链接
     * @param qrCodeWidth    二维码宽度
     * @param qrCodeHeight   二维码高度
     * @return byte[]
     * @description: 生成二维码字节数组
     * @modified: spring
     */
    private static byte[] generateQrCodeByteArray(String qrCodeShortUrl, Integer qrCodeWidth, Integer qrCodeHeight) {

        if (!StringUtils.hasLength(qrCodeShortUrl) || qrCodeWidth == null || qrCodeHeight == null) {
            throw new RuntimeException("usage this static method error: please check args !");
        }

        QrConfig qrConfig = new QrConfig();
        qrConfig.setBackColor(Color.white);
        qrConfig.setForeColor(Color.black);
        qrConfig.setHeight(qrCodeHeight);
        qrConfig.setWidth(qrCodeWidth);

        return QrCodeUtil.generatePng(qrCodeShortUrl, qrConfig);
    }

    public static void main(String[] args) {
        String qrCodeShortUrl = "www.baidu.com";
        Integer qrCodeWidth = 200;
        Integer qrCodeHeight = 200;
        byte[] qrCodeByteArray = generateQrCodeByteArray(qrCodeShortUrl, qrCodeWidth, qrCodeHeight);
        ByteArrayInputStream qrByteArrayInputStream = new ByteArrayInputStream(qrCodeByteArray);
        try {
            BufferedImage qrCodeImage = ImageIO.read(qrByteArrayInputStream);
            ImageIO.write(qrCodeImage, "png", new File("D:\\code_git_repo_forestSpringH\\learn_runjing\\src\\main\\resources\\test.png"));

            try{
                String link = "https://img.zcool.cn/community/01b06559eb808ba801202b0c285ee7.jpg@1280w_1l_2o_100sh.jpg";
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                try (InputStream in = connection.getInputStream()) {
                    BufferedImage image =  ImageIO.read(in);
                    InputStream inputStream = combinerImageAndQrCodeStream(qrCodeByteArray, image, 0, 0);
                    BufferedImage bufferedImage = ImageIO.read(inputStream);
                    ImageIO.write(bufferedImage, "png", new File("D:\\code_git_repo_forestSpringH\\learn_runjing\\src\\main\\resources\\test2.png"));
                } finally {
                    connection.disconnect();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
