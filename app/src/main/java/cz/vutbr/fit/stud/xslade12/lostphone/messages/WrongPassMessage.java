package cz.vutbr.fit.stud.xslade12.lostphone.messages;

import android.util.Base64;

import java.io.File;

/**
 * Zpráva o tom, že bylo špatně zadané heslo
 * @author Petr Sládek <xslade12@stud.fit.vutbr.cz>
 */
public class WrongPassMessage extends Message {

    public WrongPassMessage() {
        this.type = Message.TYPE_WRONGPASS;
    }

    /**
     * Soubor v FS s fotkou z přední kamery
     */
    private File frontPhoto;


    public File getFrontPhoto() {
        return frontPhoto;
    }

    public void setFrontPhoto(File frontPhoto) {
        this.frontPhoto = frontPhoto;
    }

    public void deleteFrontPhoto() {
        this.frontPhoto = null;
    }

}
