//package io.usa.doraemon.bus.adaptor.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.jws.WebService;
//import javax.xml.ws.WebServiceContext;
//
//import org.apache.cxf.headers.Header;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.w3c.dom.Element;
//
//import io.usa.doraemon.bus.adaptor.AbstractServiceAdaptor;
//import io.usa.doraemon.bus.adaptor.AdaptorType;
//import io.usa.doraemon.bus.adaptor.ISoapServiceAdaptor;
//
//@WebService(endpointInterface = "io.usa.doraemon.service.adaptor.ISoapServiceAdaptor")
//public class SoapServiceAdaptor extends AbstractServiceAdaptor implements ISoapServiceAdaptor {
//
//	private  Logger logger = LoggerFactory.getLogger(SoapServiceAdaptor.class);
//
//
//	@Resource
//	private WebServiceContext webServiceContext;
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public String invoke(String appId, String category, String name,
//			String version, String params)  {
//		Map<String, Object> headers = webServiceContext.getMessageContext();
//		List<Header> headerList = (List<Header>)headers.get(Header.HEADER_LIST);
//
//		String appToken = null;
//		String securityLevel = null;
//		for(Header h:headerList){
//			Element e = (Element) h.getObject();
//			logger.info(".soaper...header....nodeName:="+e.getNodeName()+"..text:="+e.getTextContent());
//			if("appToken".equals(e.getNodeName())){
//				appToken = e.getTextContent();
//			}
//			if("securityLevel".equals(e.getNodeName())){
//				securityLevel = e.getTextContent();
//			}
// 		}
//		String result = this.invokeService(appId, appToken, category, name, version,params,securityLevel);
//		logger.info("result==========="+result);
//		return result;
//	}
//
//	@Override
//	protected AdaptorType getAdaptorType() {
//		return AdaptorType.SOAP;
//	}
//
//	@Override
//	protected List<String> getExtendsFiltersChain() {
//		return  this.getConfiguration().getSoapFiltersChain();
//	}
//
//}
