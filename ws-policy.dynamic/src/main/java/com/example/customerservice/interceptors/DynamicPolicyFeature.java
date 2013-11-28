package com.example.customerservice.interceptors;

import org.apache.cxf.Bus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.interceptor.InterceptorProvider;

public class DynamicPolicyFeature extends AbstractFeature {
	
	private DynamicPolicyInInterceptor IN = new DynamicPolicyInInterceptor();
	private DynamicPolicyOutInterceptor OUT = new DynamicPolicyOutInterceptor();

    @Override
    protected void initializeProvider(InterceptorProvider provider, Bus bus) {
            provider.getInInterceptors().add(IN);
            provider.getInFaultInterceptors().add(IN);
            provider.getOutInterceptors().add(OUT);
            provider.getOutFaultInterceptors().add(OUT);
    }

}
