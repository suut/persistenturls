/*
 * Copyright (c) Zepheira LLC, Some rights reserved.
 * 
 * Source code developed for this project is licensed under the Apache
 * License, Version 2.0. See the file LICENSE.txt for details.
 */
package name.persistent.behaviours;

import name.persistent.concepts.TombstonedPURL;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.message.BasicHttpResponse;
import org.openrdf.repository.object.annotations.precedes;

/**
 * Issues a 410 response for tombstoned PURLs.
 * 
 * @author James Leigh
 */
@precedes(DisabledPURLSupport.class)
public abstract class TombstonedPURLSupport extends PURLSupport implements
		TombstonedPURL {
	private static final ProtocolVersion HTTP11 = new ProtocolVersion("HTTP",
			1, 1);

	@Override
	public HttpResponse resolvePURL(String source, String qs, String accept, String language, int max) {
		return new BasicHttpResponse(HTTP11, 410, "Permanently Gone");
	}

}