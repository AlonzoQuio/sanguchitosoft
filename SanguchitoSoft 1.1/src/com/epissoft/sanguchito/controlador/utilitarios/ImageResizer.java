/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epissoft.sanguchito.controlador.utilitarios;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author HP
 */
 
public class ImageResizer {
    //Ancho máximo
    public static int MAX_WIDTH = 60;
    //Alto máximo
    public static int MAX_HEIGHT = 60;
 
    /*Este método es el de la magia recibe la ruta al archivo original y la ruta donde vamos a guardar la copia
    copyImage("C:\\Users\\HP-HP\\Desktop\\test.png","C:\\Users\\HP-HP\\Desktop\\Copia\\test2.png");*/
 
    public static void copyImage(String filePath, String copyPath) {
        BufferedImage bimage = loadImage(filePath);
        //copyPath = "src/Imagenes/" + copyPath + ".png";
        copyPath = copyPath + ".png";
        bimage = resize(bimage, MAX_WIDTH, MAX_HEIGHT);
        saveImage(bimage, copyPath);
    }
    public static void copyImage(String filePath, String path,String copyPath, int width, int height) {
        BufferedImage bimage = loadImage(filePath);
        MAX_HEIGHT = height;
        MAX_WIDTH = width;
        copyPath = path + copyPath + ".png";
        bimage = resize(bimage, MAX_WIDTH, MAX_HEIGHT);
        saveImage(bimage, copyPath);
    }
    public static void copyImage(String filePath, String copyPath, int width, int height) {
        BufferedImage bimage = loadImage(filePath);
        MAX_HEIGHT = height;
        MAX_WIDTH = width;
        copyPath = "src/Imagenes/" + copyPath + ".png";
        bimage = resize(bimage, MAX_WIDTH, MAX_HEIGHT);
        saveImage(bimage, copyPath);
    } 
    /*
    Este método se utiliza para cargar la imagen de disco
    */
    public static BufferedImage loadImage(String pathName) {
        System.out.println("Path "+pathName);
        BufferedImage bimage = null;
        try {
            bimage = ImageIO.read(new File(pathName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimage;
    }
 
    /*
    Este método se utiliza para almacenar la imagen en disco
    */
    public static void saveImage(BufferedImage bufferedImage, String pathName) {
        try {
            String format = (pathName.endsWith(".png")) ? "png" : "jpg";
            File file =new File(pathName);
            file.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /*
    Este método se utiliza para redimensionar la imagen
    */
    public static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = bufim.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return bufim;
    }
}

