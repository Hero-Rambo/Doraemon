//package io.usa.doraemon.commons.api.interceptor.impl;
//
//import java.util.List;
//
//import org.apache.cxf.binding.soap.SoapMessage;
//import org.apache.cxf.headers.Header;
//import org.apache.cxf.interceptor.Fault;
//import org.apache.cxf.phase.AbstractPhaseInterceptor;
//import org.apache.cxf.phase.Phase;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//
///**
// *
// * @author Rambo
// * @2013-12-26
// */
//@Component
//public class AuthenticateInterceptor extends
//		AbstractPhaseInterceptor<SoapMessage> {
//
//	private Logger logger = LoggerFactory.getLogger(AuthenticateInterceptor.class);
//
//	public AuthenticateInterceptor(){
//        super(Phase.PRE_INVOKE);
//    }
//
//	@Override
//	public void handleMessage(SoapMessage message) throws Fault {
//		logger.info(".........................."+message);
//		List<Header> headers = message.getHeaders();
//		for(Header h:headers){
//			logger.info(h.getObject().getClass().getName());
//			Element e = (Element) h.getObject();
//			logger.info("..........."+e.getNodeName()+"...."+e.getNodeValue()+"..."+e.getTextContent());
//
//			NodeList children = e.getChildNodes();
//			for(int i=0;i<children.getLength();i++){
//				Node n = children.item(i);
//				if(n.getNodeType()==Node.ELEMENT_NODE){
//					logger.info("..........."+n.getNodeName()+"...."+n.getNodeValue()+"..."+n.getTextContent());
//				}
//			}
// 		}
//
//
//	}
//}
