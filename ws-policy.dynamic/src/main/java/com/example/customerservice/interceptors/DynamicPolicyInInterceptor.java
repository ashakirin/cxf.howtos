package com.example.customerservice.interceptors;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.ws.policy.PolicyConstants;
import org.apache.cxf.ws.policy.PolicyInInterceptor;
import org.apache.neethi.Policy;

public class DynamicPolicyInInterceptor extends
		AbstractPhaseInterceptor<SoapMessage> {

	public DynamicPolicyInInterceptor() {
		super(Phase.RECEIVE);
		getBefore().add(PolicyInInterceptor.class.getName());
	}

	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		Policy wsaPolicy = PolicyHelper.parsePolicy(msg, "/wsa-policy.xml");
		msg.put(PolicyConstants.POLICY_OVERRIDE, wsaPolicy);
	}

}
