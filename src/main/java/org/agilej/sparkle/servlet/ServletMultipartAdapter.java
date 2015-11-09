package org.agilej.sparkle.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.agilej.sparkle.Multipart;

public class ServletMultipartAdapter implements Multipart{

    private Part part;

    public ServletMultipartAdapter(Part part) {
        this.part = part;
    }
    
    
    public String getName(){
        return this.part.getName();
    }
    
    
    public InputStream getInputStream(){
        try {
            return this.part.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
