package org.purl.accessor;

import org.ten60.netkernel.layer1.nkf.INKFConvenienceHelper;
import org.ten60.netkernel.layer1.nkf.NKFException;

public class PURLURIResolver extends URIResolver {

    @Override
    public String getURI(INKFConvenienceHelper context) {
        String retValue = null;

        try {
            String path = NKHelper.getArgument(context, "path");
            retValue =  getURI((!path.startsWith("/") ? ("/"+path) : path));
        } catch(NKFException nfe) {
            nfe.printStackTrace();
        }

        return retValue;
    }

    @Override
    public String getURI(String id) {
        return "ffcpl:/storedpurls" + id;
    }


}
