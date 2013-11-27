package com.example.customerservice.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.neethi.Policy;
import org.w3c.dom.Element;

public class PolicyHelper {
	private PolicyHelper() {
	}

	public static Policy parsePolicy(SoapMessage msg, String policyPath) {
		
		try {
			// 1. Load policy as DOM
			Element policyElement = DOMUtils.readXml(
					PolicyHelper.class.getResourceAsStream(policyPath))
					.getDocumentElement();

			// 2. Parse policy
			org.apache.cxf.ws.policy.PolicyBuilder builder = msg.getExchange()
					.getBus()
					.getExtension(org.apache.cxf.ws.policy.PolicyBuilder.class);

			return builder.getPolicy(policyElement);
		} catch (Exception e) {
			throw new RuntimeException("Cannot parse policy: " + e.getMessage(), e);
		}
	}
}
