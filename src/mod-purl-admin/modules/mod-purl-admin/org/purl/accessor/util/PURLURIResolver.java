package org.purl.accessor.util;

import org.ten60.netkernel.layer1.nkf.INKFConvenienceHelper;
import org.ten60.netkernel.layer1.nkf.NKFException;

public class PURLURIResolver extends URIResolver {

    @Override
    public String getURI(INKFConvenienceHelper context) {
        String retValue = null;

        try {
            String path = NKHelper.getArgument(context, "path");
            if(!path.startsWith("ffcpl:/purl/")) {
                retValue = getURI(path);
            } else {
                retValue = path;
            }
            
        } catch(NKFException nfe) {
            nfe.printStackTrace();
        }

        return retValue;
    }

    @Override
    public String getURI(String path) {
        String retValue = null;
        
        if(!path.startsWith("ffcpl:/purl")) {
        	String displayName = getDisplayName(path);
            path="ffcpl:/purl" + displayName;
        }
        
        retValue = path;

        return retValue;
    }

    @Override
    public String getDisplayName(String path) {
        String retValue = null;
        
        if(!path.startsWith("ffcpl:/purl")) {
        	if(path.startsWith("ffcpl:/")){
        		retValue = path.substring(6);
        	} else {
        		retValue = (!path.startsWith("/") ? ("/"+path) : path);
        	}
        } else {
            retValue = path.substring(11);
        }
        
        if(retValue.endsWith("/")) {
            retValue = retValue.substring(0, retValue.length()-1);
        }
        
        return retValue;
    }
    
    private String cleanseInput(String path) {
    	String retValue = path.replaceAll("&", "&amp;");
    	retValue = retValue.replaceAll("'", "&apos;");
    	return retValue;
    }
}
