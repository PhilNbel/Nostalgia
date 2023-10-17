import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

import javax.imageio.ImageIO;

class Texture{

    static BufferedImage blck, prs, obj, las,hac;
    static BufferedImage [] tex = new BufferedImage[300];
    BufferedImage i;

    Texture(BufferedImage img){ i=img; }

    BufferedImage grabImg(int x, int y, int w, int h){
        return i.getSubimage((x*w)-w,(y*h)-h,w,h);
    }

    static BufferedImage getImg(int i){ 
        return tex[i]; }

    void load(){
        try {
            blck=ImageIO.read(getClass().getResource("/img/blck.png"));
            prs=ImageIO.read(getClass().getResource("/img/prs.png"));
            obj=ImageIO.read(getClass().getResource("/img/obj.png"));
            las=ImageIO.read(getClass().getResource("/img/laser.png"));
            tex[299]=ImageIO.read(getClass().getResource("/img/bg.png"));
            hac=ImageIO.read(getClass().getResource("/img/hache.png"));

        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        Texture b = new Texture(blck);
        Texture p=new Texture(prs);
        Texture o=new Texture(obj);
        Texture l=new Texture(las);
        Texture h=new Texture(hac);
        int c=0;

        for(int i=1;i<8;i++)
            for(int j=1;j<8;j++){
                tex[c]=b.grabImg(j,i,32,32);
                c++;
            }

        for(int i=1;i<8;i++)
            for(int j=1;j<8;j++){
                tex[c]=p.grabImg(j,i,20,40);
                c++;
            }

        for(int i=1;i<8;i++)
            for(int j=1;j<8;j++){
                tex[c]=o.grabImg(j,i,16,16);
                c++;
            }
        for(int i=1;i<3;i++)
            for(int j=1;j<3;j++){
                tex[c]=l.grabImg(j,i,40,8);
                c++;
            }
        tex[297]=h.grabImg(1,1,10,18);
        tex[298]=h.grabImg(2,1,10,18);

    }

}