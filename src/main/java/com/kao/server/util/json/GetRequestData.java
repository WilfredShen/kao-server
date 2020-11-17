package com.kao.server.util.json;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class GetRequestData {

    public static String getJsonDataFromRequest(HttpServletRequest request) {

        int contentLength = request.getContentLength();

        if (contentLength < 0) {
            return null;
        }
        byte[] buffer = new byte[contentLength];
        int startIndex = 0, len = 0;
        while (true) {
            try {
                if ((request.getInputStream().read(buffer, startIndex, contentLength - startIndex) == -1)) {
                    break;
                } else {
                    startIndex += len;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        String encoding = request.getCharacterEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }
        try {
            System.err.println(new String(buffer, encoding));
            return new String(buffer, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;

    }
}
