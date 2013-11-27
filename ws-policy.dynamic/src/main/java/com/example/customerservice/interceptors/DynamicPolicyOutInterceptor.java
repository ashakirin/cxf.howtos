package com.example.customerservice.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.policy.PolicyConstants;
import org.apache.cxf.ws.policy.PolicyOutInterceptor;
import org.apache.neethi.Policy;

public class DynamicPolicyOutInterceptor extends
		AbstractPhaseInterceptor<SoapMessage> {
	
	public DynamicPolicyOutInterceptor() {
		super(Phase.SETUP);
		getBefore().add(PolicyOutInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		Policy wsaPolicy = PolicyHelper.parsePolicy(msg, "/wsa-policy.xml");
		msg.put(PolicyConstants.POLICY_OVERRIDE, wsaPolicy);
	}

}
