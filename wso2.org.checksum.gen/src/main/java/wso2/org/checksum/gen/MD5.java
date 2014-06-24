package wso2.org.checksum.gen;

import java.io.*;
import java.security.MessageDigest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * User: ayesha
 * Date: 6/17/14
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class MD5 {

    /**
     * generate checksum for Inputstream
     * return byte stream
     */

    public static byte[] createChecksum(InputStream stream) throws
            Exception
    {
        InputStream fis =  stream;

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        fis.close();
        return complete.digest();
    }

    /**
     * generate checksum for Inputstream
     * internal call to fn createChecksum
     * return a string value as checksum
     */
    // see this How-to for a faster way to convert
    // a byte array to a HEX string
    public static String getMD5Checksum(InputStream stream) throws Exception {

        byte[] b = createChecksum(stream);
        String result = "";
        for (int i=0; i < b.length; i++) {
            result +=
                    Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }



}
