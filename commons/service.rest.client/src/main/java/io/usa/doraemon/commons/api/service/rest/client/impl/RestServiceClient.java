package io.usa.doraemon.commons.api.service.rest.client.impl;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import io.usa.doraemon.commons.api.service.rest.client.IRestServiceClient;

/**
 * 
 * @author Rambo
 * @date Jan 26, 2014
 */
public class RestServiceClient implements IRestServiceClient {
	public RestServiceClient() {

	}

	public RestServiceClient(String restServiceUrl) {
		this.restServiceUrl = restServiceUrl;
	}

	protected HttpRequestBase getRequetMethodByHttpMethod(String httpMethod,
			String restServiceUrl, String appId, String category, String name,
			String version, String params) throws Exception{
		if ("GET".equalsIgnoreCase(httpMethod)) {
			StringBuilder sb = new StringBuilder(256);
			sb.append(restServiceUrl);
			if (!restServiceUrl.endsWith("/")) {
				sb.append('/');
			}   			   
			sb.append(URLEncoder.encode(appId,"utf-8"));
			sb.append('/');
			sb.append(URLEncoder.encode(category,"utf-8"));
			sb.append('/');
			sb.append(URLEncoder.encode(name,"utf-8"));
			sb.append('/');
			sb.append(URLEncoder.encode(version,"utf-8"));
			sb.append('/');
			sb.append(URLEncoder.encode(params,"utf-8"));

			return new HttpGet(sb.toString());
		}
		if ("POST".equalsIgnoreCase(httpMethod)) {
			return new HttpPost(restServiceUrl);
		}
		return new HttpPost(restServiceUrl);
	}

	protected void buildParams(String appId, String category, String name,
			String version, String params, HttpRequestBase req,
			String httpMethod) {
		if ("POST".equalsIgnoreCase(httpMethod)) {
			List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
			httpParams.add(new BasicNameValuePair("appId", appId));
			httpParams.add(new BasicNameValuePair("category", category));
			httpParams.add(new BasicNameValuePair("name", name));
			httpParams.add(new BasicNameValuePair("version", version));
			httpParams.add(new BasicNameValuePair("params", params));

			HttpPost httpPost = (HttpPost) req;
			httpPost.setEntity(new UrlEncodedFormEntity(httpParams,Charset.forName("UTF-8")));
		}

	}

	protected void buildHeaders(HttpRequestBase req, Map<String, String> headers) {
		if (headers == null) {
			return;
		}
		for (Entry<String, String> entry : headers.entrySet()) {
			Header h = new BasicHeader(entry.getKey(), entry.getValue());
			req.addHeader(h);
		}
	}

	private String restServiceUrl;

	public void setRestServiceUrl(String restServiceUrl) {
		this.restServiceUrl = restServiceUrl;
	}
	
	
//	private Logger logger = LoggerFactory.getLogger(RestServiceClient.class);
	
	public String invokeByRest(String appId, String category, String name,
			String version, String params, Map<String, String> headers,
			String httpMethod)throws Exception  {
		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpRequestBase req = this.getRequetMethodByHttpMethod(httpMethod,
					restServiceUrl, appId, category, name, version, params);
			this.buildParams(appId, category, name, version, params, req,
					httpMethod);
			this.buildHeaders(req, headers);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return httpclient.execute(req, responseHandler);
//			logger.info("responseBody.getClass().getName()=="+responseBody.getClass().getName());		
////			HttpResponse response = httpclient.execute(req);
//			return String.valueOf(responseBody);
//		}catch(Exception e){
//			logger.error("error:",e);
//			HttpResponseException he = (HttpResponseException) e;
//			logger.info("he.getStatusCode()=========="+he.getStatusCode());
//			logger.info("he.getMessage()()=========="+he.getMessage());
//			throw new RuntimeException("url=="+this.restServiceUrl,e);
		}finally {
			httpclient.getConnectionManager().shutdown();
		}
	}

}
